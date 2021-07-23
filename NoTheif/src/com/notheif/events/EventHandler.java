package com.notheif.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.notheif.main.NoTheif;

public class EventHandler {

    public List<SubEvent> events = new ArrayList<SubEvent>();
	
    private Plugin plugin = NoTheif.instance;
    
	public void setup() {
		this.events.add(new InputHandler());
		this.events.add(new TrackingEvent());
		this.events.forEach(i -> plugin.getServer().getPluginManager().registerEvents(i, plugin));
	}
	
}
