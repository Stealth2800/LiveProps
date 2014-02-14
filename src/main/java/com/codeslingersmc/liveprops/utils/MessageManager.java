package com.codeslingersmc.liveprops.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

    private JavaPlugin plugin;
    private YamlFileManager messageFile;

    private String tag;
    private Map<String, Map<String, String>> messages = new HashMap<>();

    public MessageManager(JavaPlugin plugin) {
        this(plugin, "messages.yml");
    }

    public MessageManager(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        messageFile = new YamlFileManager(plugin.getDataFolder() + File.separator + fileName);
        if (messageFile.isEmpty()) {
            plugin.saveResource(fileName, true);
        } else {
            messageFile.copyDefaults(YamlConfiguration.loadConfiguration(plugin.getResource(fileName)));
        }
        reloadMessages();
    }

    public void reloadMessages() {
        messageFile.reloadConfig();
        messages.clear();

        FileConfiguration config = messageFile.getConfig();
        for (String key : config.getKeys(false)) {
            if (key.equals("tag") || !(config.get(key) instanceof List)) continue;

            Map<String, String> secMessages = new HashMap<>();
            for (String messageName : config.getConfigurationSection(key).getKeys(false)) {
                secMessages.put(messageName, config.getString(messageName));
            }
            messages.put(key, secMessages);
        }
        tag = config.getString("tag", "&6[{PLUGINNAME}] ");
    }

    public String getTag() {
        return getTag(false);
    }

    public String getTag(boolean raw) {
        return raw ? tag : ChatColor.translateAlternateColorCodes('&', tag.replace("{PLUGINNAME}", plugin.getName()));
    }

    public String getMessage(String name) {
        String[] nameSplit = name.split(".");
        String message;
        try {
            message = messages.get(nameSplit[0]).get(nameSplit[1]);
        } catch (NullPointerException ex) {
            message = null;
        }
        return message == null ? ChatColor.RED + "Undefined message '" + name + "'" : ChatColor.translateAlternateColorCodes('&', message).replace("{TAG}", getTag());
    }

    public String getMessage(String name, String... replacements) {
        String[] nameSplit = name.split(".");
        String message;
        try {
            message = messages.get(nameSplit[0]).get(nameSplit[1]);
        } catch (NullPointerException ex) {
            message = null;
        }
        return message == null ? ChatColor.RED + "Undefined message '" + name + "'" : ChatColor.translateAlternateColorCodes('&', String.format(message, replacements)).replace("{TAG}", getTag());
    }

}