package com.mrz.dyndns.server.KnightsWhoSayNI;

import org.bukkit.command.CommandSender;

public enum Permissions 
{
	CAN_SAY_NI		("KnightsWhoSayNi.ni"),
	IMMUNE_TO_NI	("KnightsWhoSayNi.nope"),
	CAN_RELOAD		("KnightsWhoSayNi.reload");
	
	private Permissions(String node)
	{
		this.node = node;
	}
	
	private final String node;
	
	public boolean verify(CommandSender sender)
	{
		return sender.hasPermission(node) || sender.isOp();
	}
}
