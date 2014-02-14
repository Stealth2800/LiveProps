package com.codeslingersmc.liveprops.commands;

import com.codeslingersmc.liveprops.LiveProps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdLiveProps implements CommandExecutor {

    private LiveProps plugin;

    public CmdLiveProps(LiveProps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "version":
                    break;

                default:
                    sender.sendMessage("Type /" + label + " help for help.");
                    break;
            }
        } else {
            sender.sendMessage("Type /" + label + " help for help.");
        }
        cmdVersion(sender, command, label, args);
        return true;
    }

    /*
     * Version command
     */
    private void cmdVersion(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.RED + "Live" + ChatColor.DARK_GREEN + "Props" + ChatColor.GOLD + " v" + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GOLD + "Created by " + ChatColor.BOLD + "CodeSlingers");
        sender.sendMessage(ChatColor.AQUA + "http://aWebsiteThatDoesntExist.com/");
    }

}