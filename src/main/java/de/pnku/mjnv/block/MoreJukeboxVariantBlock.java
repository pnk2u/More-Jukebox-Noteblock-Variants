package de.pnku.mjnv.block;

import com.mojang.serialization.MapCodec;
import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import de.pnku.mjnv.init.MjnvBlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoreJukeboxVariantBlock extends JukeboxBlock {
    public static final BooleanProperty HAS_RECORD;
    public final String jukeboxWoodType;

    public MoreJukeboxVariantBlock(MapColor colour, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(jukeboxWoodType + "_noteblock"))));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }

    public MoreJukeboxVariantBlock(MapColor colour, SoundType sound, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(jukeboxWoodType + "_noteblock"))).sound(sound));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }
    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
            if ((Boolean)state.getValue(HAS_RECORD) && level.getBlockEntity(pos) instanceof MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity) {
                moreJukeboxVariantBlockEntity.popOutTheItem();
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if ((Boolean)blockState.getValue(HAS_RECORD)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else {
            ItemStack itemStack2 = player.getItemInHand(interactionHand);
            InteractionResult interactionResult = JukeboxPlayable.tryInsertIntoJukebox(level, blockPos, itemStack2, player);
            return (InteractionResult)(!interactionResult.consumesAction() ? InteractionResult.TRY_WITH_EMPTY_HAND : interactionResult);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity var7 = level.getBlockEntity(pos);
            if (var7 instanceof MoreJukeboxVariantBlockEntity) {
                MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity = (MoreJukeboxVariantBlockEntity)var7;
                moreJukeboxVariantBlockEntity.popOutTheItem();
            }

            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MoreJukeboxVariantBlockEntity(pos, state);
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        BlockEntity var6 = level.getBlockEntity(pos);
        if (var6 instanceof MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity) {
            if (moreJukeboxVariantBlockEntity.getSongPlayer().isPlaying()) {
                return 15;
            }
        }

        return 0;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity var5 = level.getBlockEntity(pos);
        if (var5 instanceof MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity) {
            return moreJukeboxVariantBlockEntity.getComparatorOutput();
        } else {
            return 0;
        }
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return (Boolean)state.getValue(HAS_RECORD) ? createTickerHelper(blockEntityType, MjnvBlockInit.MORE_JUKEBOX_VARIANT_BLOCK_ENTITY, MoreJukeboxVariantBlockEntity::tick) : null;
    }

    public Item getPlanksItem(String planksWood) {
        switch (planksWood) {
            case "acacia" -> {
                return Items.ACACIA_PLANKS;
            }
            case "bamboo" -> {
                return Items.BAMBOO_PLANKS;
            }
            case "birch" -> {
                return Items.BIRCH_PLANKS;
            }
            case "cherry" -> {
                return Items.CHERRY_PLANKS;
            }
            case "crimson" -> {
                return Items.CRIMSON_PLANKS;
            }
            case "dark_oak" -> {
                return Items.DARK_OAK_PLANKS;
            }
            case "oak" -> {
                return Items.OAK_PLANKS;
            }
            case "jungle" -> {
                return Items.JUNGLE_PLANKS;
            }
            case "mangrove" -> {
                return Items.MANGROVE_PLANKS;
            }
            case "spruce" -> {
                return Items.SPRUCE_PLANKS;
            }
            case "warped" -> {
                return Items.WARPED_PLANKS;
            }

        }
        return null;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_RECORD);
    }

    static {
        HAS_RECORD = BlockStateProperties.HAS_RECORD;
    }
}