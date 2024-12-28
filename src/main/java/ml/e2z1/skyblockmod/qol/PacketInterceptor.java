package ml.e2z1.skyblockmod.qol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ml.e2z1.skyblockmod.config.Settings;
import ml.e2z1.skyblockmod.utils.TextUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PacketInterceptor extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }
        SnakeTracer.active = false;
        if (Settings.snakeTracing && Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getWorldScoreboard() != null) {
            Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();

            ScoreObjective sidebarObjective = scoreboard.getObjectiveInDisplaySlot(1);

            if (sidebarObjective != null) {
                List<Score> scores = new ArrayList<>(scoreboard.getSortedScores(sidebarObjective));

                String riftLocation = "";
                for (int i = scores.size() - 1; i >= 0; i--) {
                    Score score = scores.get(i);
                    ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score.getPlayerName());
                    String line = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score.getPlayerName());
                    line = TextUtils.stripColor(line);
                    if (line.contains("ф")) {
                        riftLocation = line.replaceAll("[^A-Za-z0-9() ]", "").trim();
                        break;
                    }
                }
                if (riftLocation.equals("Living Cave")) {
                    SnakeTracer.active = true;
                    // Intercept single block changes
                    if (msg instanceof S23PacketBlockChange) {
                        handleSingleBlockChange((S23PacketBlockChange) msg);
                    }
                    // Intercept multi-block changes
                    else if (msg instanceof S22PacketMultiBlockChange) {
                        handleMultiBlockChange((S22PacketMultiBlockChange) msg);
                    }
                }
            }
        }

        // Pass the packet along to its normal processing
        super.channelRead(ctx, msg);
    }

    private void handleSingleBlockChange(S23PacketBlockChange packet) {
        BlockPos pos = packet.getBlockPosition();
        World world = Minecraft.getMinecraft().theWorld;

        if (world != null) {
            Block block = world.getBlockState(pos).getBlock();
            if (block == Blocks.lapis_block) {
                SnakeTracer.LapisPlaced(pos);
            } else if (block != Blocks.stained_glass) {
                SnakeTracer.SnakeGone(pos);
            }
            // Execute custom logic here
        }
    }

    private void handleMultiBlockChange(S22PacketMultiBlockChange packet) {
        World world = Minecraft.getMinecraft().theWorld;

        if (world != null) {
            for (S22PacketMultiBlockChange.BlockUpdateData data : packet.getChangedBlocks()) {
                BlockPos pos = data.getPos();

                Block block = world.getBlockState(pos).getBlock();
                if (block == Blocks.lapis_block) {
                    SnakeTracer.LapisPlaced(pos);
                } else if (block != Blocks.stained_glass) {
                    SnakeTracer.SnakeGone(pos);
                }
                // Execute custom logic here

            }
        }
    }
}
