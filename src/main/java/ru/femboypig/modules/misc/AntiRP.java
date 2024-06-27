package ru.femboypig.modules.misc;

import net.minecraft.network.packet.c2s.play.ResourcePackStatusC2SPacket;
import net.minecraft.network.packet.s2c.play.ResourcePackSendS2CPacket;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.Func;
import ru.femboypig.utils.events.EventHandler;
import ru.femboypig.utils.events.PacketReceiveEvent;
import ru.femboypig.utils.events.PacketSendEvent;
import ru.femboypig.utils.listeners.Listener;

public class AntiRP extends Func implements Listener {
    public AntiRP() {
        super("Anti Server RP", "Disables server resourcespacks");
    }

    @EventHandler
    private void onResourceReceive(PacketReceiveEvent e) {
        if (BrushCC.CONFIG.instance().antiRP) {
            eventBus.addListener(this);
            if (e.getPacket() instanceof ResourcePackSendS2CPacket packet) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onResourceSend(PacketSendEvent e) {
        if (BrushCC.CONFIG.instance().antiRP) {
            eventBus.addListener(this);
            if (e.getPacket() instanceof ResourcePackStatusC2SPacket packet) {
                e.setCancelled(true);
            }
        }
    }
}