package ml.e2z1.skyblockmod.automation;

import ml.e2z1.skyblockmod.SkyBlockMod;
import ml.e2z1.skyblockmod.config.Settings;
import ml.e2z1.skyblockmod.utils.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.TimeUnit;

public class joinSkyblock {
    private boolean worldJustLoaded = false;
    @SubscribeEvent()
    public void onWorldLoad(WorldEvent.Load event) {
        this.worldJustLoaded = true;
    }


    @SubscribeEvent()
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (!this.worldJustLoaded) return;
        worldJustLoaded = false;
        if(Settings.skyblockOnJoin && Minecraft.getMinecraft().theWorld.getScoreboard() != null) {
            ScoreObjective title = Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            if (title!=null) {
                String strippedScoreboardTitle = TextUtils.stripColor(title.getDisplayName());
                if (strippedScoreboardTitle.equals("PROTOTYPE")) {

                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock");
                }
            }
        }
    }


}
