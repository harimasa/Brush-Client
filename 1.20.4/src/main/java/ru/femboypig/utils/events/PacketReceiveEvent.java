package ru.femboypig.utils.events;

import net.minecraft.network.packet.Packet;
import ru.femboypig.utils.interfaces.Cancellable;

public class PacketReceiveEvent extends Event implements Cancellable {

    private final Packet<?> packet;
    private boolean cancelled;

    public PacketReceiveEvent(Packet<?> packet) {
        this.packet = packet;
        this.cancelled = false;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}