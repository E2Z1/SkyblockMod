package ml.e2z1.skyblockmod.autoaccept;

import ml.e2z1.skyblockmod.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class autoAccept {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onChatMessage(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();
        if (Settings.autoAccept && msg.contains("has invited you to join their party!") && !msg.contains(":")) {
            String invite;
            if (msg.indexOf(']') == -1) {
                invite = msg.substring(msg.indexOf('\n') + 1, msg.indexOf("has invited you to join their party!")-1);
            } else {
                invite = msg.substring(msg.indexOf(']') + 2, msg.indexOf("has invited you to join their party!") - 1);
            }
            if (Arrays.asList(Settings.acceptPlayers.toLowerCase().split(" ")).contains(invite.toLowerCase())) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party accept " + invite);
            }
        }
    }
}