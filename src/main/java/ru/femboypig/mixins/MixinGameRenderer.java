package ru.femboypig.mixins;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.femboypig.config.BrushCC;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

	@Shadow
	private float zoom;

	@Shadow
	private float zoomX;

	@Shadow
	private float zoomY;

	@Shadow
	private float viewDistance;

	@Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
	public void onHurtViewTilt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
		if (BrushCC.CONFIG.instance().hurtCamEnabled) {
			ci.cancel();
		}
	}

	@Inject(method = "getBasicProjectionMatrix", at = @At("TAIL"), cancellable = true)
	public void getBasicProjectionMatrixHook(double fov, CallbackInfoReturnable<Matrix4f> cir) {
		if (BrushCC.CONFIG.instance().visualRatioEnabled) {
			MatrixStack matrixStack = new MatrixStack();
			matrixStack.peek().getPositionMatrix().identity();
			if (zoom != 1.0f) {
				matrixStack.translate(zoomX, -zoomY, 0.0f);
				matrixStack.scale(zoom, zoom, 1.0f);
			}
			matrixStack.peek().getPositionMatrix().mul(new Matrix4f().setPerspective((float) (fov * 0.01745329238474369), BrushCC.CONFIG.instance().visualRatio, 0.05f, viewDistance * 4.0f));
			cir.setReturnValue(matrixStack.peek().getPositionMatrix());
		}
	}

	@ModifyArgs(method = "renderFloatingItem", at = @At(target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", value = "INVOKE"))
	public void renderFloatingItemScaled(Args args) {
		if (BrushCC.CONFIG.instance().totemPop) {
			float scale = BrushCC.CONFIG.instance().totemPopValue;
			args.set(0, (float) args.get(0) * scale);
			args.set(1, (float) args.get(1) * scale);
			args.set(2, (float) args.get(2) * scale);
		}
	}
}