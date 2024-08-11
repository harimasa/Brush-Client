package ru.femboypig.utils.events;

import net.minecraft.client.gui.screen.Screen;
import ru.femboypig.utils.interfaces.Cancellable;

import static ru.femboypig.BrushClient.mc;

public class SetScreenEvent extends Event implements Cancellable {

    private final Screen screen, previousScreen;
    private boolean cancelled;

    public SetScreenEvent(Screen screen) {
        this.previousScreen = mc.currentScreen;
        this.screen = screen;
        this.cancelled = false;
    }

    public Screen getScreen() {
        return screen;
    }

    public Screen getPreviousScreen() {
        return previousScreen;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}