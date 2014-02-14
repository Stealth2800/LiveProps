package com.codeslingersmc.liveprops;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class BukkitYAML {

    private File BukkitYAMLFile;
    private FileConfiguration BukkitYML;


    public void reloadBukkitYML(){
        if(BukkitYAMLFile == null){
            BukkitYAMLFile = new File("bukkit.yml");
        }
        BukkitYML = YamlConfiguration.loadConfiguration(BukkitYAMLFile);
    }

    public FileConfiguration getBukkitYML(){
        if (BukkitYML == null){
            reloadBukkitYML();
        }
        return BukkitYML;
    }

    public void saveBukkitYML(){
        if(BukkitYAMLFile == null || BukkitYML == null){
            return;
        }
        try{
            getBukkitYML().save(BukkitYAMLFile);
        }catch(IOException e){
            Bukkit.getLogger().log(Level.SEVERE, "Could not save bukkit.yml!", e);
        }
    }

    public void setMonsterSpawn(int i){
        getBukkitYML().set("spawn-limits.monsters", i);
        //Insert CraftServerAccessor Code Here
        saveBukkitYML();
    }

    public void setAnimalSpawn(int i){
        getBukkitYML().set("spawn-limits.animals", i);
        //Insert CraftServerAccessor Code Here
        saveBukkitYML();
    }

    public void setWaterAnimalSpawn(int i){
        getBukkitYML().set("spawn-limits.water-animals", i);
        //Insert CraftServerAccessor Code Here
        saveBukkitYML();
    }

    public void setAmbientSpawn(int i){
        getBukkitYML().set("spawn-limits.ambient", i);
        //Insert CraftServerAccessor Code Here
        saveBukkitYML();
    }

    public void setWarningState(String s){
        getBukkitYML().set("settings.deprecated-verbose", s);
        //Insert CraftServerAccessor Code Here
        saveBukkitYML();
    }



}
