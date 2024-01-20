package ml.e2z1.skyblockmod.KeyBinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding joinDungeon;
    public static void init() {
        joinDungeon = new KeyBinding("key.qol.dungeons", Keyboard.KEY_NUMPAD5, "key.esb.joinDungeon");
        ClientRegistry.registerKeyBinding(joinDungeon);
    }
}
