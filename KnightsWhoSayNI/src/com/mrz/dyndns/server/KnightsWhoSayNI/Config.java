package com.mrz.dyndns.server.KnightsWhoSayNI;

import org.bukkit.configuration.Configuration;

public class Config 
{
	private Config()
	{
		
	}
	
	public static void Configure(Configuration config)
	{
		range 				= config.getInt("Range");
		fireTicks 			= config.getInt("FireTicks");
		pushUp 				= config.getDouble("Push.Up");
		pushAway 			= config.getDouble("Push.Away");
		damage 				= config.getDouble("Damage");
		almostKill			= config.getBoolean("AlmostKill");
		kill				= config.getBoolean("Kill");
		hitPlayers			= config.getBoolean("HitPlayers");
		listenForFusRoDah 	= config.getBoolean("ListenForFusRoDah");
		listenForNI			= config.getBoolean("ListenForNI");
		notAllowedString	= config.getString("NotAllowedMessage");
		
		if(DEBUG)
		{
			System.out.println("[DEBUG] range: \t" + range);
			System.out.println("[DEBUG] fireTicks: \t" + fireTicks);
			System.out.println("[DEBUG] pushUp: \t" + pushUp);
			System.out.println("[DEBUG] pushAway: \t" + pushAway);
			System.out.println("[DEBUG] damage: \t" + damage);
			System.out.println("[DEBUG] almostKill: \t" + almostKill);
			System.out.println("[DEBUG] kill: \t\t" + kill);
			System.out.println("[DEBUG] hitPlayers: \t" + hitPlayers);
			System.out.println("[DEBUG] fusrohdah: \t" + listenForFusRoDah);
		}
	}
	
	private static final boolean DEBUG = false;
	
	private static int range;
	private static double pushUp;
	private static double pushAway;
	private static int fireTicks;
	private static double damage;
	private static boolean almostKill;
	private static boolean kill;
	private static boolean hitPlayers;
	private static boolean listenForFusRoDah;
	private static boolean listenForNI;
	private static String notAllowedString;
	
	public static int getRange()
	{
		return range;
	}
	
	public static double getPushUp()
	{
		return pushUp;
	}
	
	public static double getPushAway()
	{
		return pushAway;
	}
	
	public static int getFireTicks()
	{
		return fireTicks;
	}
	
	public static double getDamage()
	{
		return damage;
	}
	
	public static boolean getAlmostKill()
	{
		return almostKill;
	}
	
	public static boolean getKill()
	{
		return kill;
	}
	
	public static boolean getHitPlayers()
	{
		return hitPlayers;
	}
	
	public static boolean getListenForFusRoDah()
	{
		return listenForFusRoDah;
	}
	
	public static boolean getListenForNI()
	{
		return listenForNI;
	}
	
	public static String getNotAllowedString()
	{
		return notAllowedString;
	}
}
