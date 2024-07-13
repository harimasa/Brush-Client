package ru.femboypig.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.awt.*;

public class SEConfigs {
    // No hurt Cam
    @SerialEntry
    public boolean hurtCamEnabled = false;

    // Visual Ratio
    @SerialEntry
    public boolean visualRatioEnabled = false;

    @SerialEntry
    public float visualRatio = 1.78f;

    // Black Pig
    @SerialEntry
    public boolean blackPigEnabled = false;

    // FullBright
    @SerialEntry
    public boolean fullBrightEnabled = false;

    // No Armor Render
    @SerialEntry
    public boolean NAREnabled = false;

    // Time Changer
    @SerialEntry
    public boolean timeChangerEnabled = false;

    @SerialEntry
    public int timeChanger = 0;

    // Effect Time
    @SerialEntry
    public boolean effectTimeEnabled = false;

    // Render Name
    @SerialEntry
    public boolean renderNameEnabled = false;

    // Animations
    @SerialEntry
    public boolean animationsEnabled = false;

    @SerialEntry
    public boolean slowAnimations = false;

    @SerialEntry
    public int slowValue = 12;

    @SerialEntry
    public boolean animationsMain = false;

    @SerialEntry
    public boolean animationsOff = false;

    // View Model
    @SerialEntry
    public boolean affectItems = false;

    @SerialEntry
    public float scale = 1f;

    @SerialEntry
    public float scaleoff = 1f;

    @SerialEntry
    public float posX = 0f;

    @SerialEntry
    public float posY = 0f;

    @SerialEntry
    public float posZ = 0f;

    @SerialEntry
    public float rotMainX = 0f;

    @SerialEntry
    public float rotMainY = 0f;

    @SerialEntry
    public float rotMainZ = 0f;

    @SerialEntry
    public float rotOffX = 0f;

    @SerialEntry
    public float rotOffY = 0f;

    @SerialEntry
    public float rotOffZ = 0f;

    @SerialEntry
    public boolean affectArm = false;

    @SerialEntry
    public float posArmX = 0f;

    @SerialEntry
    public float posArmY = 0f;

    @SerialEntry
    public float posArmZ = 0f;

    @SerialEntry
    public float rotArmX = 0f;

    @SerialEntry
    public float rotArmY = 0f;

    @SerialEntry
    public float rotArmZ = 0f;

    // Low Fire
    @SerialEntry
    public boolean lowFireEnabled = false;

    // Low Shield
    @SerialEntry
    public boolean lowShield = false;

    @SerialEntry
    public float lowShieldValue = -0.3f;

    // Cape
    @SerialEntry
    public boolean capeEnabled = true;

    // Elytra Swap
    @SerialEntry
    public boolean elytraswap = false;

    // Hit Armor
    @SerialEntry
    public boolean hitArmor = false;

    // AntiRP
    @SerialEntry
    public boolean antiRP = false;

    // Hide Screens
    @SerialEntry
    public boolean hideScreens = false;

    // Hide Name
    @SerialEntry
    public boolean hideName = false;

    @SerialEntry
    public String fakeName = "Dalboeb";

    // Discord RPC
    @SerialEntry
    public boolean rpc = true;

    // FPS
    @SerialEntry
    public boolean fps = false;

    @SerialEntry
    public boolean textShadow = false;

    @SerialEntry
    public Color fpsColor = new Color(0,0,0);

    @SerialEntry
    public float textSize = 1f;

    @SerialEntry
    public int left = 4;

    @SerialEntry
    public int top = 4;

    @SerialEntry
    public String nameFps = "Fps";

    // Ping
    @SerialEntry
    public boolean ping = false;

    @SerialEntry
    public boolean pingShadow = false;

    @SerialEntry
    public Color pingColor = new Color(0,0,0);

    @SerialEntry
    public float pingSize = 1f;

    @SerialEntry
    public int leftPing = 4;

    @SerialEntry
    public int topPing = 4;

    @SerialEntry
    public String name = "Ping";

    // Coords
    @SerialEntry
    public boolean coords = false;

    @SerialEntry
    public boolean coordsShadow = false;

    @SerialEntry
    public Color coordsColor = new Color(0, 0, 0);

    @SerialEntry
    public float coordsSize = 1f;

    @SerialEntry
    public int leftCoords = 4;

    @SerialEntry
    public int topCoords = 4;

    // Server Ip
    @SerialEntry
    public boolean ip = false;

    @SerialEntry
    public boolean ipShadow = false;

    @SerialEntry
    public Color ipColor = new Color(0, 0, 0);

    @SerialEntry
    public float ipSize = 1f;

    @SerialEntry
    public int leftIp = 4;

    @SerialEntry
    public int topIp = 4;

    // Pearl
    @SerialEntry
    public boolean pearl = false;

    @SerialEntry
    public boolean pearlSound = false;

    @SerialEntry
    public String pearlSoundId = "ENTITY_ENDERMAN_TELEPORT";

    @SerialEntry
    public float pearlSize = 1f;

    // Block Overlay
    @SerialEntry
    public boolean bo = false;

    @SerialEntry
    public Color boColor = new Color(255,0,0);

    // Hit Color
    @SerialEntry
    public boolean hitcolor = false;

    @SerialEntry
    public Color colorHit = new Color(255,0,0);

    // BCM
    @SerialEntry
    public boolean bcm = false;

    // No Totem Particles
    @SerialEntry
    public boolean nototempart = false;

    // Totem pop scale
    @SerialEntry
    public boolean totemPop = false;

    @SerialEntry
    public float totemPopValue = 0f;

    // Bad Model
    @SerialEntry
    public boolean badmodel = false;

    // Fake Tier
    @SerialEntry
    public boolean fakeTier = false;

    @SerialEntry
    public String tier = "HT1";

    @SerialEntry
    public Color tierColor = new Color(0xFF0000);

    // Sound When Hit
    @SerialEntry
    public boolean swh = false;

    // No Totem
    @SerialEntry
    public boolean nototem = false;

    @SerialEntry
    public Color nototemColor = new Color(255,0,0,100);

    // HP indicator
    @SerialEntry
    public boolean hp = false;

    @SerialEntry
    public Color hpColor = new Color(0xE25656);

    // PingMe
    @SerialEntry
    public boolean pingMe = false;

    @SerialEntry
    public Color pingMeColor = new Color(0xE53B3B);

    // Sky Color
    @SerialEntry
    public boolean sky = false;

    @SerialEntry
    public Color skyColor = new Color(0x4D4DCA);

    // Fog Color
    @SerialEntry
    public boolean fog = false;

    @SerialEntry
    public Color fogColor = new Color(0x4D4DCA);

    // AutoSprint
    @SerialEntry
    public boolean sprint = false;

    // Tnt tag
    @SerialEntry
    public boolean tnt = false;

    // No Weather
    @SerialEntry
    public boolean noWeather = false;

    // Name Tags
    @SerialEntry
    public boolean nametags = false;

    @SerialEntry
    public boolean nametagsShadow = false;

    @SerialEntry
    public float nametagsTransparent = 0;
}