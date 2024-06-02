package ru.femboypig.mixins.elytraSwap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.misc.ElytraSwap;

@Mixin(ElytraItem.class)
public abstract class ElytraItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        if (BrushCC.CONFIG.instance().elytraswap) {
            info.setReturnValue(ElytraSwap.equipAndSwap((ElytraItem) (Object) this, world, user, hand));
        }
    }
}