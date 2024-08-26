package de.pnku.mjnv.mixin;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import net.fabricmc.fabric.mixin.entity.event.MobEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Allay.class)
public abstract class AllayMixin extends PathfinderMob {

    @Shadow
    @Nullable
    private BlockPos jukeboxPos;

    protected AllayMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "shouldStopDancing", at = @At("HEAD"), cancellable = true)
    private void injectedShouldStopDancing(CallbackInfoReturnable<Boolean> cir) {
        if (this.jukeboxPos == null || !this.jukeboxPos.closerToCenterThan(this.position(), (double)GameEvent.JUKEBOX_PLAY.value().notificationRadius()) || !(this.level().getBlockState(this.jukeboxPos).getBlock() instanceof MoreJukeboxVariantBlock)){
            cir.setReturnValue(true);
        }
    }
}
