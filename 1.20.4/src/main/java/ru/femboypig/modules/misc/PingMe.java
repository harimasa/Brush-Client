package ru.femboypig.modules.misc;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import ru.femboypig.modules.Func;

import static ru.femboypig.BrushClient.mc;

public class PingMe extends Func {
    public PingMe() {
        super("Ping Me!", "Plays a sound when you are mentioned in chat.");
    }

    public static void playSound(){
        PositionedSoundInstance sound = new PositionedSoundInstance(SoundEvents.ENTITY_ARROW_HIT_PLAYER.getId(), SoundCategory.PLAYERS, 0.6f, 0.3f, SoundInstance.createRandom(), false, 0, SoundInstance.AttenuationType.NONE, 0, 0, 0, true);
        mc.getSoundManager().play(sound);
    }
}