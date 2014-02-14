package com.codeslingersmc.liveprops.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlFileManager {

    private File file;
    private FileConfiguration config;

    public YamlFileManager(String filePath) {
        Validate.notNull(filePath, "FilePath cannot be null");

        file = new File(filePath);
        reloadConfig();
    }

    public YamlFileManager(File file) {
        Validate.notNull(file, "File cannot be null");

        this.file = file;
        reloadConfig();
    }

    public void reloadConfig() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                saveFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveFile() {
        if (file == null || config == null) {
            reloadConfig();
        } else {
            try {
                config.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    public boolean isEmpty() {
        return config.getKeys(false).size() == 0;
    }

    public void copyDefaults(FileConfiguration otherConfig) {
        Validate.notNull(otherConfig, "OtherConfig cannot be null");

        config.addDefaults(otherConfig);
        config.options().copyDefaults(true);
        saveFile();
    }

}