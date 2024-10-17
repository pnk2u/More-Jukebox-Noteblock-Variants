package de.pnku.mjnv.mixin;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreJukeboxVariantBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxPlayable.class)
public class JukeboxPlayableMixin {

    @Inject(method = "tryInsertIntoJukebox", at = @At("HEAD"), cancellable = true)
    private static void injectedTryInsertIntoJukebox(Level level, BlockPos pos, ItemStack stack, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        JukeboxPlayable jukeboxPlayable = (JukeboxPlayable)stack.get(DataComponents.JUKEBOX_PLAYABLE);
        if (jukeboxPlayable == null) {
            cir.setReturnValue(InteractionResult.TRY_WITH_EMPTY_HAND);
        } else {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock() instanceof MoreJukeboxVariantBlock && !(Boolean)blockState.getValue(MoreJukeboxVariantBlock.HAS_RECORD)) {
                if (!level.isClientSide) {
                    ItemStack itemStack = stack.consumeAndReturn(1, player);
                    if (level.getBlockEntity(pos) instanceof MoreJukeboxVariantBlockEntity moreJukeboxVariantBlockEntity) {
                        moreJukeboxVariantBlockEntity.setTheItem(itemStack);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                    }

                    player.awardStat(Stats.PLAY_RECORD);
                }

                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
