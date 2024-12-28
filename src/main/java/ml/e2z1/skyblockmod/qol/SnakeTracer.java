package ml.e2z1.skyblockmod.qol;

import ml.e2z1.skyblockmod.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class SnakeTracer {
    static boolean active = false;
    static List<List<BlockPos>> snakes = new ArrayList<>();
    public static void LapisPlaced(BlockPos pos) {
        for (List<BlockPos> snake : snakes) {
            if (Utils.areAdjacent(snake.get(snake.size()-1), pos)) {
                snake.add(pos);
                return;
            }
        }
        List<BlockPos> newSnake = new ArrayList<>();
        newSnake.add(pos);
        snakes.add(newSnake);
    }
    public static void SnakeGone(BlockPos pos) {    //not necessarily a destroyed snake
        /*
        Iterator<List<BlockPos>> iterator = snakes.iterator();
        while (iterator.hasNext()) {
            List<BlockPos> snake = iterator.next();
            if (snake.get(0).equals(pos)) {
                if (snake.size() == 1) {
                    iterator.remove();
                } else {
                    snake.remove(0);
                }
                System.out.println("rem");
            }
        }*/
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (active) {
            List<List<BlockPos>> copy = new ArrayList<>(snakes);
            for (List<BlockPos> snake : copy) {
                Block block = Minecraft.getMinecraft().theWorld.getBlockState(snake.get(0)).getBlock();
                if (!block.equals(Blocks.lapis_block) && !block.equals(Blocks.stained_glass)) {
                    if (snake.size() == 1) {
                        snakes.remove(snake);
                    } else {
                        snake.remove(0);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (!active) return;
        List<List<BlockPos>> copy = new ArrayList<>(snakes);

        for (List<BlockPos> snake : copy) {
            for (int i = 0; i < snake.size() - 1; i++) {
                double[] pos1 = {snake.get(i).getX() + 0.5, snake.get(i).getY() + 0.5, snake.get(i).getZ() + 0.5};
                double[] pos2 = {snake.get(i + 1).getX() + 0.5, snake.get(i + 1).getY() + 0.5, snake.get(i + 1).getZ() + 0.5};
                renderLineBetweenBlocks(pos1, pos2, event.partialTicks, 4, new float[]{0.01F, 0.2F, 0.6F}); //nice blue
            }
            renderBlockOutline(snake.get(0), event.partialTicks);

        }
    }

    private void renderLineBetweenBlocks(double[] pos1, double[] pos2, float partialTicks, float thickness, float[] color) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        double playerX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double playerY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double playerZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

        // Translate to player view
        GL11.glPushMatrix();
        GL11.glTranslated(-playerX, -playerY, -playerZ);

        // Draw the line
        GL11.glLineWidth(thickness); // Set line width
        GL11.glDisable(GL11.GL_TEXTURE_2D); // Disable textures
        GL11.glEnable(GL11.GL_BLEND); // Enable blending
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST); // Disable depth testing so the line is always visible

        GL11.glBegin(GL11.GL_LINES);
        GL11.glColor4f(color[0], color[1], color[2], 1.0F); // Set line color (red)
        GL11.glVertex3d(pos1[0], pos1[1], pos1[2]); // Start point
        GL11.glVertex3d(pos2[0], pos2[1], pos2[2]); // End point
        GL11.glEnd();

        // Reset OpenGL states
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glPopMatrix();
    }

    private void renderBlockOutline(BlockPos pos, float partialTicks) {
        // Get the player's current position
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        Vec3 playerPos = new Vec3(player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks,
                player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks,
                player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks);

        // Translate to the block's position relative to the player
        double x = pos.getX() - playerPos.xCoord;
        double y = pos.getY() - playerPos.yCoord;
        double z = pos.getZ() - playerPos.zCoord;

        // Prepare to draw lines
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GL11.glLineWidth(2.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        // Draw the outline
        drawOutlinedBoundingBox();

        // Reset OpenGL states
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private void drawOutlinedBoundingBox() {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        worldRenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

        double minX = 0.0;
        double minY = 0.0;
        double minZ = 0.0;
        double maxX = 1.0;
        double maxY = 1.0;
        double maxZ = 1.0;

        // Bottom face
        drawLine(worldRenderer, minX, minY, minZ, maxX, minY, minZ);
        drawLine(worldRenderer, maxX, minY, minZ, maxX, minY, maxZ);
        drawLine(worldRenderer, maxX, minY, maxZ, minX, minY, maxZ);
        drawLine(worldRenderer, minX, minY, maxZ, minX, minY, minZ);

        // Top face
        drawLine(worldRenderer, minX, maxY, minZ, maxX, maxY, minZ);
        drawLine(worldRenderer, maxX, maxY, minZ, maxX, maxY, maxZ);
        drawLine(worldRenderer, maxX, maxY, maxZ, minX, maxY, maxZ);
        drawLine(worldRenderer, minX, maxY, maxZ, minX, maxY, minZ);

        // Vertical edges
        drawLine(worldRenderer, minX, minY, minZ, minX, maxY, minZ);
        drawLine(worldRenderer, maxX, minY, minZ, maxX, maxY, minZ);
        drawLine(worldRenderer, maxX, minY, maxZ, maxX, maxY, maxZ);
        drawLine(worldRenderer, minX, minY, maxZ, minX, maxY, maxZ);

        tessellator.draw();
    }

    private void drawLine(WorldRenderer worldRenderer, double x1, double y1, double z1, double x2, double y2, double z2) {
        worldRenderer.pos(x1, y1, z1).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // Start point
        worldRenderer.pos(x2, y2, z2).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // End point
    }
}
