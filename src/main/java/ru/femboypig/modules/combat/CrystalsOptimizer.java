package ru.femboypig.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.Func;
import ru.femboypig.utils.events.EventHandler;
import ru.femboypig.utils.events.PlayerAttackEntityEvent;
import ru.femboypig.utils.PlayerUtils;
import ru.femboypig.utils.InteractionUtils;

public class CrystalsOptimizer extends Func {
    public CrystalsOptimizer() {
        super("Crystals Optimizer", "Makes crystals disappear before the server knows about it.");
    }

    @EventHandler
    private void onPacketSend(PlayerAttackEntityEvent e) {
        if (BrushCC.CONFIG.instance().coEnabled) {
            final Entity entity = e.getEntity();

            if (entity == null) return;
            if (PlayerUtils.invalid()) return;
            if (mc.isInSingleplayer()) return;
            if (!InteractionUtils.canBreakCrystals()) return;

            if (entity instanceof EndCrystalEntity crystal) {
                crystal.kill();
                crystal.remove(Entity.RemovalReason.KILLED);
                crystal.onRemoved();
            }
        }
    }
}