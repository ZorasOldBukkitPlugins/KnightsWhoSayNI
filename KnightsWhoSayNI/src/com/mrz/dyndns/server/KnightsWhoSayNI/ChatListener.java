package com.mrz.dyndns.server.KnightsWhoSayNI;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ChatListener implements Listener
{
	public ChatListener(JavaPlugin plugin)
	{
		this.plugin 			= plugin;

		this.pushAway 			= Config.getPushAway();
		this.pushUp 			= Config.getPushUp();
		this.fireTicks 			= Config.getFireTicks();
		this.damage				= Config.getDamage();
		this.kill				= Config.getKill();
		this.almostKill 		= Config.getAlmostKill();
		this.hitPlayers 		= Config.getHitPlayers();
		this.listenForFusRoDah 	= Config.getListenForFusRoDah();
		this.listenForNI		= Config.getListenForNI();
		this.notAllowedString	= Config.getNotAllowedString();
	}
	
	private final JavaPlugin plugin;

	private final double pushAway;
	private final double pushUp;
	private final int fireTicks;
	private final double damage;
	private final boolean kill;
	private final boolean almostKill;
	private final boolean hitPlayers;
	private final boolean listenForFusRoDah;
	private final boolean listenForNI;
	private final String notAllowedString;
	
	private boolean messageIsPower(String message)
	{
		message = message.toLowerCase();
		
		if (listenForFusRoDah)
		{
			switch(message)
			{
			case "fus roh dah":
			case "fus roh dah!":
			case "fus ro dah":
			case "fus ro dah!":
				return true;
			}
		}
		
		if(listenForNI)
		{
			switch(message)
			{
			case "ni":
			case "ni!":
			case "ni.":
				return true;
			}
		}
		
		return false;
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerChat(final AsyncPlayerChatEvent event)
	{	
		if(messageIsPower(event.getMessage()))
		{
			final Player player = event.getPlayer();
			
			plugin.getServer().getScheduler().runTask(plugin, new Runnable()
			{
				@Override
				public void run() 
				{
					if (Permissions.CAN_SAY_NI.verify(player))
					{
						int range = Config.getRange();
						
						double reach = range / 2.0D;
						
						List<Entity> entities = player.getNearbyEntities(reach, reach, reach);
						
						for(Entity e : entities)
						{
							if (e instanceof Player)
							{
								if(hitPlayers)
								{
									Player target = (Player) e;
									if (!Permissions.IMMUNE_TO_NI.verify(target))
									{
										hit(target);
									}
								}
							}
							else if (e instanceof LivingEntity)
							{
								LivingEntity creature = (LivingEntity) e;
								hit(creature);
							}
						}
					}
					else
					{
						if(notAllowedString.length() > 0)
						{
							player.sendMessage(ChatColor.RED + notAllowedString);
						}
					}
				}

				private void hit(LivingEntity livingEntity)
				{
					if(fireTicks > 0)
					{
						livingEntity.setFireTicks(fireTicks);
					}
					
					//thanks: https://forums.bukkit.org/threads/pushing-entities-away.105666/
					
					// Get velocity unit vector:
					Vector unitVector = livingEntity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
					
					// Set speed and push entity:
					livingEntity.setVelocity(unitVector.multiply(pushAway).add(new Vector(0.0, pushUp, 0.0)));
					

					if(kill)
					{
						livingEntity.damage(0.0);
						livingEntity.setHealth(0.0);
					}
					else if(almostKill)
					{
						livingEntity.setHealth(livingEntity.getMaxHealth() - 1.0);
					}
					else
					{
						livingEntity.damage(damage);
					}
				}
			});
		}
	}
}
