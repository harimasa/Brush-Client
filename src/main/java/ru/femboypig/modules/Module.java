package ru.femboypig.modules;

import ru.femboypig.modules.combat.*;
import ru.femboypig.modules.hud.*;
import ru.femboypig.modules.misc.*;
import ru.femboypig.modules.render.*;

public class Module {

    private final String name;
    private String description;

    // Render
    public static NoHurtCam noHurtCam = new NoHurtCam();
    public static VisualRatio visualRatio = new VisualRatio();
    public static BlackPig blackPig = new BlackPig();
    public static FullBright fullBright = new FullBright();
    public static NoArmorRender noArmorRender = new NoArmorRender();
    public static TimeChanger timeChanger = new TimeChanger();
    public static RenderName renderName = new RenderName();
    public static Animations animations = new Animations();
    public static ViewModel viewModel = new ViewModel();
    public static LowFire lowFire = new LowFire();
    public static Cape cape = new Cape();

    // Misc
    public static ElytraSwap elytraSwap = new ElytraSwap();
    public static AntiRP antiRP = new AntiRP();
    public static HideScreens hideScreens = new HideScreens();
    public static HideName hideName = new HideName();

    // Hud
    public static EffectTime effectTime = new EffectTime();
    public static DiscordRPC discordRPC = new DiscordRPC();
    public static Fps fps = new Fps();
    public static Ping ping = new Ping();
    public static Coords coords = new Coords();
    public static serverIp serverIp = new serverIp();

    // Combat
    public static Pearl pearl = new Pearl();
    public static HitArmor hitArmor = new HitArmor();
    public static CrystalsOptimizer crystalsOptimizer = new CrystalsOptimizer();
    public static BlockOverlay blockOverlay = new BlockOverlay();

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }
}