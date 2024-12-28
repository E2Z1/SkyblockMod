package ml.e2z1.skyblockmod.autoaccept;

import ml.e2z1.skyblockmod.config.Settings;
import ml.e2z1.skyblockmod.utils.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class autoWarp {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onChatMessage(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();
        if (Settings.autoWarp && msg.startsWith("§9Party §8>") && msg.contains("warp")) {
            String sender;
            msg = TextUtils.stripColor(msg);
            if (msg.indexOf(']') == -1) {
                sender = msg.substring(msg.indexOf('>') + 2, msg.indexOf(":"));
            } else {
                sender = msg.substring(msg.indexOf(']') + 2, msg.indexOf(":"));
            }
            if (Arrays.asList(Settings.acceptPlayers.toLowerCase().split(" ")).contains(sender.toLowerCase())) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party warp");
            }
        }
    }
}
