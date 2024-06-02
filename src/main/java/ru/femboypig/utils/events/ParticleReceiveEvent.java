package ru.femboypig.utils.events;

import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Vec3d;
import ru.femboypig.utils.interfaces.Cancellable;

public class ParticleReceiveEvent extends Event implements Cancellable {

    private final ParticleType<?> type;
    private final Vec3d pos;
    private final Vec3d velocity;
    private boolean cancelled;

    public ParticleReceiveEvent(ParticleType<?> type, double x, double y, double z, double velX, double velY, double velZ) {
        this.type = type;
        this.pos = new Vec3d(x, y, z);
        this.velocity = new Vec3d(velX, velY, velZ);
        this.cancelled = false;
    }

    public ParticleType<?> getType() {
        return type;
    }

    public Vec3d getPos() {
        return pos;
    }

    public Vec3d getVelocity() {
        return velocity;
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
