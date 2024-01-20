package ml.e2z1.skyblockmod.KeyBinds;

import ml.e2z1.skyblockmod.qol.joinDungeon;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.joinDungeon.isPressed()) {
            FMLClientHandler.instance().showGuiScreen(new joinDungeon());
        }
    }
}
