package ml.e2z1.skyblockmod.utils;

import net.minecraft.client.Minecraft;

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
}
