package de.pnku.mjnv.block;

import com.google.common.annotations.VisibleForTesting;
import com.vinurl.VinURL;
import com.vinurl.items.VinURLDiscItem;
import de.pnku.mjnv.init.MjnvBlockInit;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static de.pnku.mjnv.MoreJukeboxNoteblockVariants.isVinURLLoaded;

public class MoreJukeboxVariantBlockEntity extends BlockEntity implements Clearable, ContainerSingleItem {
    private static final int SONG_END_PADDING = 20;
    private ItemStack item;
    private int ticksSinceLastEvent;
    private long tickCount;
    private long recordStartedTick;
    private boolean isPlaying;

    public MoreJukeboxVariantBlockEntity(BlockPos pos, BlockState blockState) {
        super(MjnvBlockInit.MORE_JUKEBOX_VARIANT_BLOCK_ENTITY, pos, blockState);
        this.item = ItemStack.EMPTY;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("RecordItem", 10)) {
            this.item = ItemStack.of(tag.getCompound("RecordItem"));
        }

        this.isPlaying = tag.getBoolean("IsPlaying");
        this.recordStartedTick = tag.getLong("RecordStartTick");
        this.tickCount = tag.getLong("TickCount");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.getTheItem().isEmpty()) {
            tag.put("RecordItem", this.getTheItem().save(new CompoundTag()));
        }

        tag.putBoolean("IsPlaying", this.isPlaying);
        tag.putLong("RecordStartTick", this.recordStartedTick);
        tag.putLong("TickCount", this.tickCount);
    }

    public boolean isRecordPlaying()  {
        return !this.getTheItem().isEmpty() && this.isPlaying;
    }

    private void setHasRecordBlockState(@Nullable Entity entity, boolean hasRecord) {
        if (this.level.getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), (BlockState)this.getBlockState().setValue(MoreJukeboxVariantBlock.HAS_RECORD, hasRecord), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
        }

    }

    @VisibleForTesting
    public void startPlaying() {
        ItemStack recordStack = this.getTheItem();
       if (isVinURLLoaded) {
        if (recordStack.getItem() instanceof VinURLDiscItem && !level.isClientSide) {
            String musicUrl = recordStack.getOrCreateTag().getString("music_url");

            if (musicUrl != null && !musicUrl.isEmpty()) {
                FriendlyByteBuf bufInfo = PacketByteBufs.create();
                bufInfo.writeBlockPos(worldPosition);
                bufInfo.writeUtf(musicUrl);

                level.players().forEach(
                        playerEntity -> ServerPlayNetworking.send(
                                (ServerPlayer) playerEntity,
                                VinURL.CUSTOM_RECORD_PACKET_ID, bufInfo
                        )
                );
            }
        }
       }
        this.recordStartedTick = this.tickCount;
        this.isPlaying = true;
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent((Player)null, 1010, this.getBlockPos(), Item.getId(this.getTheItem().getItem()));
        this.setChanged();
    }

    private void stopPlaying() {
        this.isPlaying = false;
        this.level.gameEvent(GameEvent.JUKEBOX_STOP_PLAY, this.getBlockPos(), GameEvent.Context.of(this.getBlockState()));
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent(1011, this.getBlockPos(), 0);
        this.setChanged();
    }

    private void tick(Level level, BlockPos pos, BlockState state) {
        ++this.ticksSinceLastEvent;
        if (this.isRecordPlaying()) {
            Item var5 = this.getTheItem().getItem();
            if (var5 instanceof RecordItem) {
                RecordItem recordItem = (RecordItem)var5;
                if (this.shouldRecordStopPlaying(recordItem)) {
                    this.stopPlaying();
                } else if (this.shouldSendJukeboxPlayingEvent()) {
                    this.ticksSinceLastEvent = 0;
                    level.gameEvent(GameEvent.JUKEBOX_PLAY, pos, GameEvent.Context.of(state));
                    this.spawnMusicParticles(level, pos);
                }
            }
        }

        ++this.tickCount;
    }

    private boolean shouldRecordStopPlaying(RecordItem record) {
        return this.tickCount >= this.recordStartedTick + (long)record.getLengthInTicks() + 20L;
    }

    private boolean shouldSendJukeboxPlayingEvent() {
        return this.ticksSinceLastEvent >= 20;
    }

    @Override
    public @NotNull ItemStack getTheItem() {
        return this.item;
    }

    @Override
    public ItemStack splitTheItem(int amount) {
        ItemStack itemStack = this.item;
        this.item = ItemStack.EMPTY;
        if (!itemStack.isEmpty()) {
            this.setHasRecordBlockState((Entity)null, false);
            this.stopPlaying();
        }

        return itemStack;
    }

    @Override
    public void setTheItem(ItemStack item) {
        if (item.is(ItemTags.MUSIC_DISCS) && this.level != null) {
            this.item = item;
            this.setHasRecordBlockState((Entity)null, true);
            this.startPlaying();
        } else if (item.isEmpty()) {
            this.splitTheItem(1);
        }

    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.is(ItemTags.MUSIC_DISCS) && this.getItem(slot).isEmpty();
    }

    @Override
    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return target.hasAnyMatching(ItemStack::isEmpty);
    }

    private void spawnMusicParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 vec3 = Vec3.atBottomCenterOf(pos).add(0.0, 1.2000000476837158, 0.0);
            float f = (float)level.getRandom().nextInt(4) / 24.0F;
            serverLevel.sendParticles(ParticleTypes.NOTE, vec3.x(), vec3.y(), vec3.z(), 0, (double)f, 0.0, 0.0, 1.0);
        }

    }

    public void popOutRecord() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos blockPos = this.getBlockPos();
            ItemStack itemStack = this.getTheItem();
            if (!itemStack.isEmpty()) {
                this.removeTheItem();
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5, 1.01, 0.5).offsetRandom(this.level.random, 0.7F);
                ItemStack itemStack2 = itemStack.copy();
                ItemEntity itemEntity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), itemStack2);
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }

           if (isVinURLLoaded) {
            FriendlyByteBuf bufInfo = PacketByteBufs.create();
            bufInfo.writeBlockPos(worldPosition);
            bufInfo.writeUtf("");

            this.level.players().forEach(playerEntity -> {
                ServerPlayNetworking.send((ServerPlayer) playerEntity, VinURL.CUSTOM_RECORD_PACKET_ID, bufInfo);
            });
          }
        }
    }

    public static void playRecordTick(Level level, BlockPos pos, BlockState state, MoreJukeboxVariantBlockEntity jukebox) {
        jukebox.tick(level, pos, state);
    }

    @VisibleForTesting
    public void setRecordWithoutPlaying(ItemStack stack) {
        this.item = stack;
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }
}