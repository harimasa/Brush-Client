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

    // Cape
    @SerialEntry
    public boolean capeEnabled = true;

    // Crystals Optimizer
    @SerialEntry
    public boolean coEnabled = false;

    // Elytra Swap
    @SerialEntry
    public boolean elytraswap = false;

    // Hit Armor
    @SerialEntry
    public boolean hitArmor = false;

    // AntiRP
    @SerialEntry
    public boolean antiRP = false;

    // Hide Particles
    @SerialEntry
    public boolean hideParticles = false;

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
}