package com.notheif.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.notheif.tracking.Reason;
import com.notheif.utils.Time;

public class Logs extends Config{

	private List<Reason> reasons = new ArrayList<Reason>();
	
	public Logs(String name, double version) {
		super(name, version);
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.LOGS;
	}
	
	@Override
	public void initialize() {
		
		if(!this.getConfiguration().isConfigurationSection("Logs")) {
			return;
		}
		
		ConfigurationSection logs = this.getConfiguration().getConfigurationSection("Logs");
		
		for(String id : logs.getKeys(false)) {
			
			ConfigurationSection log = logs.getConfigurationSection(id);
			
			String reported = log.getString("Reported");
			String reason = log.getString("Reason");
			
			String lu = log.getString("Users.LastUser");
			String ns = log.getString("Users.NewUser");
			
			Date period = Time.convertStringToDate(log.getString("Period.logged"));
			
			UUID lastUser = null;
			UUID newUser = null;
			
			if(!lu.equals("None")) {
				lastUser = UUID.fromString(lu);
			}
			
			if(!ns.equals("None")) {
				newUser = UUID.fromString(ns);
			}
			
			ItemStack item = log.getItemStack("Item");
			
			Reason reasonData = new Reason(reported, reason, lastUser, newUser, item, period);
			
			reasons.add(reasonData);
		
		}
		
	}
	
	public void addData(Reason reason) {
		this.reasons.add(reason);
	}
	
	public List<Reason> getData(String filter) {
		
		List<Reason> results = new ArrayList<Reason>();
		
		for(Reason i : this.reasons) {
			
			if(i.item.hasItemMeta() && i.item.getItemMeta().hasDisplayName()) {
				String strippedColour = ChatColor.stripColor(i.item.getItemMeta().getDisplayName()).trim();
				if(strippedColour.equalsIgnoreCase(filter.trim())) {
					results.add(i);
				}
			}
		
		}
		
		return results;
		
	}
	
	public void update() {

		int counter = 0;
		
		for(Reason reason : this.reasons) {
	
			ConfigurationSection section = this.getConfiguration();
			
			section.set("Logs." + counter + ".Reported", reason.reported);
			section.set("Logs." + counter + ".Reason", reason.reason);
			section.set("Logs." + counter + ".Users.LastUser", "None");
			section.set("Logs." + counter + ".Users.NewUser", "None");
			section.set("Logs." + counter + ".Item", reason.item);
			section.set("Logs." + counter + ".Period.logged", Time.ConvertDateToString(reason.period));
		
			counter++;
			
		}
	
		this.save();
		
	}


	
}
