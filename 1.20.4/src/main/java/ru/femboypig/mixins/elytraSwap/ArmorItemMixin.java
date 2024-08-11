package ru.femboypig.mixins.elytraSwap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.misc.ElytraSwap;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        if (BrushCC.CONFIG.instance().elytraswap) {
            info.setReturnValue(ElytraSwap.equipAndSwap((ArmorItem) (Object) this, world, user, hand));
        }
    }
}