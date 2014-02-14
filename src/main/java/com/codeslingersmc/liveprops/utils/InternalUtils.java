package com.codeslingersmc.liveprops.utils;

import org.bukkit.Bukkit;

/**
 * Helper class for getting CraftBukkit/NMS classes.
 */
public final class InternalUtils {

    private InternalUtils() { }

    public static String getBukkitVersion() {
        return Bukkit.getServer().getClass().getCanonicalName().split("\\.")[3];
    }

    public static String getNMSClassName(String name) {
        return "net.minecraft.server." + getBukkitVersion() + "." + name;
    }

    public static Class getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getBukkitVersion() + "." + name);
    }

    public static String getCBClassName(String name) {
        return "org.bukkit.craftbukkit." + getBukkitVersion() + "." + name;
    }

    public static Class getCBClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + getBukkitVersion() + "." + name);
    }

}