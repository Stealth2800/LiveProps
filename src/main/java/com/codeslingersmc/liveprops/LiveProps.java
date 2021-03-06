package com.codeslingersmc.liveprops;

import com.codeslingersmc.liveprops.commands.CmdLiveProps;
import com.codeslingersmc.liveprops.utils.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LiveProps extends JavaPlugin {

    private static LiveProps instance;

    public static LiveProps getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        getDataFolder().mkdir();
    }

    private CraftServerAccessor craftServerAccessor;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        craftServerAccessor = new CraftServerAccessor(this);
        messageManager = new MessageManager(this);

        getCommand("liveprops").setExecutor(new CmdLiveProps(this));

        getLogger().info("LiveProps v" + getDescription().getVersion() + " by " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + " ENABLED.");
    }

    @Override
    public void onDisable() {
        getLogger().info("LiveProps v" + getDescription().getVersion() + " by " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + " DISABLED.");
        instance = null;
    }

    public CraftServerAccessor getCraftServerAccessor() {
        return craftServerAccessor;
    }

}