package ru.femboypig.modules.misc;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.Func;
import ru.femboypig.utils.events.EventHandler;
import ru.femboypig.utils.events.SetScreenEvent;
import ru.femboypig.utils.listeners.listener;

public class HideScreens extends Func implements listener {
    public HideScreens() {
        super("Hide Screens", "Prevents some loading screens from rendering");
    }

    @EventHandler
    private void onScreenChange(SetScreenEvent e) {
        if (BrushCC.CONFIG.instance().hideScreens) {
            if (e.getScreen() instanceof DownloadingTerrainScreen) {
                e.setCancelled(true);
            }
        }
    }
}