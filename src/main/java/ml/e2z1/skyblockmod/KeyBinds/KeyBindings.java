package ml.e2z1.skyblockmod.KeyBinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding autoFarm;
    public static KeyBinding autoRotate;
    public static void init() {
        autoFarm = new KeyBinding("key.automation.farming", Keyboard.KEY_COMMA, "key.esb.automation");
        ClientRegistry.registerKeyBinding(autoFarm);
    }
}
