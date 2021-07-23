package com.notheif.config;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import com.notheif.tracking.Cause;

public class Settings extends Config{

	public Map<String, Boolean> mapping = new HashMap<String, Boolean>();
	
	public boolean track;
	
	public Settings(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Tracking.track", false));
		
		for(Cause i : Cause.values()) {
			this.items.add(new ConfigItem("Tracking.Cause." + i, true));
		}
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.SETTINGS;
	}
	
	@Override
	public void initialize() {
		
		ConfigurationSection causes = this.getConfiguration().getConfigurationSection("Tracking.Cause");
		
		for(String i : causes.getKeys(false)) {
			mapping.put(i, causes.getBoolean(i));
		}
	
		this.track = this.getConfiguration().getBoolean("Tracking.track");
		
	}

	public boolean get(Cause cause) {
		return this.mapping.get(cause.name());	
	}

	
}
