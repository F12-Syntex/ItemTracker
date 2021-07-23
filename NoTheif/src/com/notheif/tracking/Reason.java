package com.notheif.tracking;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.notheif.utils.Time;

public class Reason {

    public String reported;
	public String reason;
	public UUID lastUser;
	public UUID newUser;
	public ItemStack item;
	public Date period;
	
	public Reason(String reported, String reason, UUID lastUser, UUID newUser, ItemStack item, Date period) {
		this.reported = reported;
		this.reason = reason;
		this.lastUser = lastUser;
		this.newUser = newUser;
		this.item = item;
		this.period = period;
	}
	
	public static Reason empty() {
		final UUID random = UUID.randomUUID();
		final ItemStack empty = new ItemStack(Material.ANVIL);
		return new Reason("", "", random, random, empty, Time.getCurrentTimeInDate());
	}
	
}
