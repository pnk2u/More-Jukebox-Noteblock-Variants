package de.pnku.mjnv.mixin;

import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.allay.AllayAi;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AllayAi.class)
public class AllayAiMixin {

    @Inject(method = "shouldDepositItemsAtLikedNoteblock", at = @At("HEAD"), cancellable = true)
    private static void injectedShouldDepositItemsAtLikedNoteblock(LivingEntity entity, Brain<?> brain, GlobalPos pos, CallbackInfoReturnable<Boolean> cir) {
        Optional<Integer> optional = brain.getMemory(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS);
        Level level = entity.level();
        if (level.dimension() == pos.dimension() && level.getBlockState(pos.pos()).getBlock() instanceof MoreNoteblockVariantBlock && optional.isPresent()) {
            cir.setReturnValue(true);
        }
    }
}
