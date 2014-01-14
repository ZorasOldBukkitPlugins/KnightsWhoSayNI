package com.mrz.dyndns.server.KnightsWhoSayNI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class KnightsWhoSayNI extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		Config.Configure(getConfig());
		
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ni"))
		{
			if(args.length == 0)
			{
				if(sender instanceof Player)
				{
					if (Permissions.CAN_SAY_NI.verify(sender))
					{
						sender.sendMessage(ChatColor.RED + "You are a knight who says NI, so say it! (It's not a command...)");
					}
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "You can't say ni :( You can, however, reload the plugin. " + ChatColor.GREEN + "/ni reload");
				}
			}
			else
			{
				if(args.length > 0 && args[0].equalsIgnoreCase("reload"))
				{
					if (Permissions.CAN_RELOAD.verify(sender))
					{
						onDisable();
						reloadConfig();
						onEnable();
						
						sender.sendMessage(ChatColor.GREEN + "Reload complete!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this);
	}
}
