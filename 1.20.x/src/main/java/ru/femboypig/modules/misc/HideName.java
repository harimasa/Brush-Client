package ru.femboypig.modules.misc;

import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.Func;
import ru.femboypig.utils.interfaces.instance;

public class HideName extends Func implements instance {
    public HideName() {
        super("Hide Name", "");
    }
    public static String getCustomName() {
        return BrushCC.CONFIG.instance().hideName ? BrushCC.CONFIG.instance().fakeName.replaceAll("&", "\u00a7") : mc.getName();
    }
}