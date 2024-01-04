package ml.e2z1.skyblockmod.automation;

import ml.e2z1.skyblockmod.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Farm {
    public static boolean on = false;
    Utils utils = new Utils();
    public static double[][][] positions = {{{144,67.875,-240},{100,240,90},{143.3,-238.7},{143.3,238.51},{140.3,238.51},{140.3,-238.7},{136.3,-238.7},{136.3,238.7},{133.3,238.3},{133.3,-238.7},{130.3,-238.7},{130.3,238.7},{126.3,238.7},{126.3,-238.7},{123.3,-238.7},{123.3,238.7},{120.3,238.7},{120.3,-238.7},{116.3,-238.7},{116.3,238.7},{113.3,238.7},{113.3,-238.7},{110.3,-238.7},{110.3,238.7},{106.3,238.7},{106.3,-238.7}},
            {{-134,67,-141},{-72,144,-90},{-133.3,-140.7},{-133.3,141.7},{-128.3,141.7},{-128.3,-140.7},{-123.3,-140.7},{-123.3,141.7},{-118.3,141.7},{-118.3,-140.7},{-113.3,-140.7},{-113.3,141.7},{-108.3,141.7},{-108.3,-140.7},{-103.3,-140.7},{-103.3,141.7},{-98.3,141.7},{-98.3,-140.7},{-93.3,-140.7},{-93.3,141.7},{-88.3,141.7},{-88.3,141.7},{-83.3,-140.7},{-83.3,141.7},{-78.3,141.7},{-78.3,141.7},{-73.3,141.7},{-73.3,141.7}}};
    //first two are the box where the macro is active; y is only in the first one so it has to stay same; [1][2] is yaw
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (on) {
            EntityPlayer p = Minecraft.getMinecraft().thePlayer;
            for (int j = 0; j<positions.length; j++) {
                if (positions[j].length < 4) return;
                if (p.posY == positions[j][0][1] && (((int) p.posX >= positions[j][0][0] && (int) p.posX <= positions[j][1][0]) || ((int) p.posX <= positions[j][0][0] && (int) p.posX >= positions[j][1][0])) && (((int) p.posZ >= positions[j][0][2] && (int) p.posZ <= positions[j][1][1]) || ((int) p.posZ <= positions[j][0][2] && (int) p.posZ >= positions[j][1][1]))) {
                    if (utils.roundDecimals(Minecraft.getMinecraft().thePlayer.getRotationYawHead(),1) == positions[j][1][2]) {
                        for (int i = 2; i < positions[j].length - 1; i++) {
                            if (utils.roundDecimals(p.posX, 2) == positions[j][i][0] && utils.roundDecimals(p.posX, 2) == positions[j][i + 1][0]) {
                                if (utils.roundDecimals(p.posZ, 2) >= positions[j][i][1] && utils.roundDecimals(p.posZ, 2) < positions[j][i + 1][1]) {
                                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
                                    walk(2);
                                    return;
                                }
                                if (utils.roundDecimals(p.posZ, 2) <= positions[j][i][1] && utils.roundDecimals(p.posZ, 2) > positions[j][i + 1][1]) {
                                    walk(0);
                                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
                                    return;
                                }
                            }
                            if (utils.roundDecimals(p.posZ, 2) == positions[j][i][1] && utils.roundDecimals(p.posZ, 2) == positions[j][i + 1][1]) {
                                if (utils.roundDecimals(p.posX, 2) >= positions[j][i][0] && utils.roundDecimals(p.posX, 2) < positions[j][i + 1][0]) {
                                    walk(1);
                                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
                                    return;
                                }
                                if (utils.roundDecimals(p.posX, 2) <= positions[j][i][0] && utils.roundDecimals(p.posX, 2) > positions[j][i + 1][0]) {
                                    walk(3);
                                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
                                    return;
                                }
                            }
                        }
                        //Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp garden");
                    }// else if (KeyBindings.autoRotate.isPressed()) Minecraft.getMinecraft().thePlayer.setRotationYawHead((float) positions[j][1][2]);
                    return;
                }
            }
        }
    }
    public void walk(int direction) { //0 Z+; 1 X-; 2 Z-; 3 X+
        direction = (Math.round(Minecraft.getMinecraft().thePlayer.getRotationYawHead()/90)+direction)%4;
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(),false);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode(),false);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(),false);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(),false);
        switch (direction) {
            case 0:
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(),true);
                break;
            case 1:
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(),true);
                break;
            case 2:
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode(),true);
                break;
            case 3:
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(),true);
                break;
        }
    }


}
