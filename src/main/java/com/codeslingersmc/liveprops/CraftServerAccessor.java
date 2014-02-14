package com.codeslingersmc.liveprops;

import com.codeslingersmc.liveprops.utils.InternalUtils;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class CraftServerAccessor {

    private LiveProps plugin;

    public CraftServerAccessor(LiveProps plugin) {
        this.plugin = plugin;
    }

    private void setBoolean(String name, Object state) {
        try {
            Class classCraftServer = InternalUtils.getCBClass("CraftServer");
            Object curCraftServer = classCraftServer.cast(Bukkit.getServer());

            Field curField = classCraftServer.getDeclaredField(name);
            curField.setAccessible(true);

            curField.set(curCraftServer, state);
        } catch (Exception ex) {
            plugin.getLogger().severe("Unable to modify boolean '" + name + "'");
            ex.printStackTrace();
        }
    }

    public void setOnlineMode(boolean state) {
        try {
            Class classCraftServer = InternalUtils.getCBClass("CraftServer");
            Object curCraftServer = classCraftServer.cast(Bukkit.getServer());

            Field fieldOnline = classCraftServer.getDeclaredField("online");
            fieldOnline.setAccessible(true);
            Object curOnline = fieldOnline.get(curCraftServer);

            String booleanWrapperName = InternalUtils.getCBClassName("CraftServer.BooleanWrapper");
            Class classBooleanWrapper = null;
            for (Class clazz : classCraftServer.getDeclaredClasses()) {
                if (clazz.getCanonicalName() != null && clazz.getCanonicalName().equals(booleanWrapperName)) {
                    classBooleanWrapper = clazz;
                }
            }
            if (classBooleanWrapper == null)
                throw new ClassNotFoundException("Unable to get boolean wrapper class");

            Field fieldValue = classBooleanWrapper.getDeclaredField("value");
            fieldValue.setAccessible(true);

            fieldValue.set(curOnline, state);
        } catch (Exception ex) {
            plugin.getLogger().severe("Unable to modify online mode.");
            ex.printStackTrace();
        }
    }

}