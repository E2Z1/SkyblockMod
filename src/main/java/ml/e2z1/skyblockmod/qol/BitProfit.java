package ml.e2z1.skyblockmod.qol;

import ml.e2z1.skyblockmod.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ContainerChest;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BitProfit {
    private boolean InShop = false;

    @SubscribeEvent
    public void onTick(GuiOpenEvent event) {
        if(Settings.bitProfit) {
            if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
                GuiChest eventGui = (GuiChest) Minecraft.getMinecraft().currentScreen;
                ContainerChest cc = (ContainerChest) eventGui.inventorySlots;
                String containerName = cc.getLowerChestInventory().getDisplayName().getUnformattedText();
                System.out.println(containerName);
            }
        }
    }
}
