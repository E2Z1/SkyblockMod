package ml.e2z1.skyblockmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

import java.lang.reflect.Method;

public class Utils {
    public boolean IsOnSkyblock() {
        if (Minecraft.getMinecraft().theWorld.getScoreboard() != null) return TextUtils.stripColor(Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName()).equals("SKYBLOCK");
        return false;
    }
    public double roundDecimals(double number, int decimals) {
        return Math.round(number*Math.pow(10,decimals))/Math.pow(10,decimals);
    }
    public static boolean invoke(Object object, String methodName) {
        try {
            final Method method = object.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean areAdjacent(BlockPos pos1, BlockPos pos2) {
        // Calculate the absolute difference in the X, Y, and Z coordinates
        int xDiff = Math.abs(pos1.getX() - pos2.getX());
        int yDiff = Math.abs(pos1.getY() - pos2.getY());
        int zDiff = Math.abs(pos1.getZ() - pos2.getZ());

        // Two positions are adjacent or diagonal if the difference in X, Y, and Z is either 0 or 1
        return (xDiff <= 1 && yDiff <= 1 && zDiff <= 1);
    }

}
