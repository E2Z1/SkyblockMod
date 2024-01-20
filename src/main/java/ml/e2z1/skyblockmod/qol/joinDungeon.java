package ml.e2z1.skyblockmod.qol;

import ml.e2z1.skyblockmod.KeyBinds.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class joinDungeon extends GuiScreen {
    private boolean masterModeSelected = false;
    private GuiButton entranceButton;
    private GuiButton floorOneButton;
    private GuiButton floorTwoButton;
    private GuiButton floorThreeButton;
    private GuiButton floorFourButton;
    private GuiButton floorFiveButton;
    private GuiButton floorSixButton;
    private GuiButton floorSevenButton;

    private GuiButton masterModeOneButton;
    private GuiButton masterModeTwoButton;
    private GuiButton masterModeThreeButton;
    private GuiButton masterModeFourButton;
    private GuiButton masterModeFiveButton;
    private GuiButton masterModeSixButton;
    private GuiButton masterModeSevenButton;


    @Override
    public void initGui() {
        super.initGui();
        entranceButton = new GuiButton(1, width/2 - 4 * 30 - 10, height/2 - 30, 20, 20, "E");
        floorOneButton = new GuiButton(2, width/2 - 3 * 30 - 10, height/2 - 30, 20, 20, "F1");
        floorTwoButton = new GuiButton(3, width/2 - 2 * 30 - 10, height/2 - 30, 20, 20, "F2");
        floorThreeButton = new GuiButton(4, width/2 - 30 - 10, height/2 - 30, 20, 20, "F3");
        floorFourButton = new GuiButton(5, width/2 - 10, height/2 - 30, 20, 20, "F4");
        floorFiveButton = new GuiButton(6, width/2 + 20, height/2 - 30, 20, 20, "F5");
        floorSixButton = new GuiButton(7, width/2 + 50, height/2 - 30, 20, 20, "F6");
        floorSevenButton = new GuiButton(8, width/2 + 80, height/2 - 30, 20, 20, "F7");

        masterModeOneButton = new GuiButton(9, width/2 - 3 * 30 - 10, height/2 + 10, 20, 20, "M1");
        masterModeTwoButton = new GuiButton(10, width/2 - 2 * 30 - 10, height/2 + 10, 20, 20, "M2");
        masterModeThreeButton = new GuiButton(11, width/2 - 30 - 10, height/2 + 10, 20, 20, "M3");
        masterModeFourButton = new GuiButton(12, width/2 - 10, height/2 + 10, 20, 20, "M4");
        masterModeSixButton = new GuiButton(13, width/2 + 50, height/2 + 10, 20, 20, "M6");
        masterModeFiveButton = new GuiButton(14, width/2 + 20, height/2 + 10, 20, 20, "M5");
        masterModeSevenButton = new GuiButton(15, width/2 + 80, height/2 + 10, 20, 20, "M7");

        buttonList.add(entranceButton);
        buttonList.add(floorOneButton);
        buttonList.add(floorTwoButton);
        buttonList.add(floorThreeButton);
        buttonList.add(floorFourButton);
        buttonList.add(floorFiveButton);
        buttonList.add(floorSixButton);
        buttonList.add(floorSevenButton);

        buttonList.add(masterModeOneButton);
        buttonList.add(masterModeTwoButton);
        buttonList.add(masterModeThreeButton);
        buttonList.add(masterModeFourButton);
        buttonList.add(masterModeFiveButton);
        buttonList.add(masterModeSixButton);
        buttonList.add(masterModeSevenButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(width/2 - 4 * 30 - 20, height/2 + (masterModeSelected ? 0 : -40), width/2 - 4 * 30 - 20 + 280, height/2 + (masterModeSelected ? 0 : -40) + 40, masterModeSelected ? 0x60FF0000 : 0x6000FF00);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == entranceButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_ENTRANCE");
            return;
        } else if (button == floorOneButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_ONE");
            return;
        } else if (button == floorTwoButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_TWO");
            return;
        } else if (button == floorThreeButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_THREE");
            return;
        } else if (button == floorFourButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_FOUR");
            return;
        } else if (button == floorFiveButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_FIVE");
            return;
        } else if (button == floorSixButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_SIX");
            return;
        } else if (button == floorSevenButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance CATACOMBS_FLOOR_SEVEN");
            return;
        } else if (button == masterModeOneButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_ONE");
            return;
        } else if (button == masterModeTwoButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_TWO");
            return;
        } else if (button == masterModeThreeButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_THREE");
            return;
        } else if (button == masterModeFourButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_FOUR");
            return;
        } else if (button == masterModeFiveButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_FIVE");
            return;
        } else if (button == masterModeSixButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_SIX");
            return;
        } else if (button == masterModeSevenButton) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_SEVEN");
            return;
        }
    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
        if (masterModeSelected) {
            if (Keyboard.isKeyDown(Keyboard.KEY_1) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) actionPerformed(masterModeOneButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_2) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) actionPerformed(masterModeTwoButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_3) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) actionPerformed(masterModeThreeButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_4) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) actionPerformed(masterModeFourButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_5) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) actionPerformed(masterModeFiveButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_6) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) actionPerformed(masterModeSixButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_7) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) actionPerformed(masterModeSevenButton);
        } else {
            if (Keyboard.isKeyDown(Keyboard.KEY_E))                                             actionPerformed(entranceButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_1) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) actionPerformed(floorOneButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_2) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) actionPerformed(floorTwoButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_3) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) actionPerformed(floorThreeButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_4) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) actionPerformed(floorFourButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_5) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) actionPerformed(floorFiveButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_6) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) actionPerformed(floorSixButton);
            if (Keyboard.isKeyDown(Keyboard.KEY_7) || Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) actionPerformed(floorSevenButton);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            masterModeSelected = !masterModeSelected;
        }
        if (Keyboard.isKeyDown(KeyBindings.joinDungeon.getKeyCodeDefault())) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }
}
