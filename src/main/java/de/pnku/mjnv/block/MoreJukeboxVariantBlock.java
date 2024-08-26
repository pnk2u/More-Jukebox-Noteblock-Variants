package de.pnku.mjnv.block;

import com.mojang.serialization.MapCodec;
import de.pnku.mjnv.init.MjnvBlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour));
        this.jukeboxWoodType = jukeboxWoodType;
    }

    public MoreJukeboxVariantBlock(MapColor colour, SoundType sound, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).sound(sound));
        this.jukeboxWoodType = jukeboxWoodType;
    }
    @Override
    public @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if ((Boolean)state.getValue(HAS_RECORD)) {
            BlockEntity var7 = level.getBlockEntity(pos);
            if (var7 instanceof MoreJukeboxVariantBlockEntity) {
                MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity = (MoreJukeboxVariantBlockEntity)var7;
                moreJukeboxVariantBlockEntity.popOutRecord();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity var7 = level.getBlockEntity(pos);
            if (var7 instanceof MoreJukeboxVariantBlockEntity) {
                MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity = (MoreJukeboxVariantBlockEntity)var7;
                moreJukeboxVariantBlockEntity.popOutRecord();
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
            if (moreJukeboxVariantBlockEntity.isRecordPlaying()) {
                return 15;
            }
        }

        return 0;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity var6 = level.getBlockEntity(pos);
        if (var6 instanceof MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity) {
            Item var7 = moreJukeboxVariantBlockEntity.getTheItem().getItem();
            if (var7 instanceof RecordItem recordItem) {
                return recordItem.getAnalogOutput();
            }
        }

        return 0;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return (Boolean)state.getValue(HAS_RECORD) ? createTickerHelper(blockEntityType, MjnvBlockInit.MORE_JUKEBOX_VARIANT_BLOCK_ENTITY, MoreJukeboxVariantBlockEntity::playRecordTick) : null;
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
        builder.add(new Property[]{HAS_RECORD});
    }

    static {
        HAS_RECORD = BlockStateProperties.HAS_RECORD;
    }
}