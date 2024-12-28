package ml.e2z1.skyblockmod;


import gg.essential.api.EssentialAPI;
import ml.e2z1.skyblockmod.KeyBinds.KeyBindings;
import ml.e2z1.skyblockmod.KeyBinds.KeyInputHandler;
import ml.e2z1.skyblockmod.commands.EsbCommand;
import ml.e2z1.skyblockmod.config.Settings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;


@Mod(modid = SkyBlockMod.MOD_ID, name = SkyBlockMod.MOD_NAME, version = SkyBlockMod.MOD_VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class SkyBlockMod {

    @Mod.Instance("esb")
    public static SkyBlockMod instance;
    public static final String MOD_NAME = "E2Z1' Skyblock Mod";
    public static final String MOD_ID = "esb";
    public static final String MOD_VERSION = "2.5";
    public Settings config;


    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new Settings();
        config.preload();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.automation.joinSkyblock());
        //MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.qol.BitProfit());
        MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.autoaccept.autoAccept());
        MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.autoaccept.autoWarp());
        MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.qol.NetworkHandler());
        MinecraftForge.EVENT_BUS.register(new ml.e2z1.skyblockmod.qol.SnakeTracer());
        EssentialAPI.getCommandRegistry().registerCommand(new EsbCommand());
        KeyBindings.init();
    }

    public Settings getConfig() {
        return config;
    }

}