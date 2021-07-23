package com.notheif.config;

import com.notheif.main.NoTheif;

public class Permissions extends Config{

	public String basic = "bukkit.command.help";
	public String admin = "nothief.admin";
	public String bypass = "nothief.timer.bypass";
	public String reload = "nothief.admin.reload";
	public String configure = "nothief.admin.configure";
	
	public String start = "nothief.admin.start";
	public String stop = "nothief.admin.stop";
	public String track = "nothief.admin.track";
	public String info = "nothief.admin.info";
	
	
	
	public Permissions(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Permissions.everyone.basic", basic));
		this.items.add(new ConfigItem("Permissions.administration.admin", admin));
		this.items.add(new ConfigItem("Permissions.administration.timer_bypass", bypass));
		this.items.add(new ConfigItem("Permissions.administration.reload", reload));
		this.items.add(new ConfigItem("Permissions.administration.configure", configure));
	
		this.items.add(new ConfigItem("Permissions.administration.start", start));
		this.items.add(new ConfigItem("Permissions.administration.stop", stop));
		this.items.add(new ConfigItem("Permissions.administration.track", track));
		this.items.add(new ConfigItem("Permissions.administration.info", info));
	
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.PERMISSIONS;
	}
	
	@Override
	public void initialize() {
		this.basic = this.getConfiguration().getString("Permissions.everyone.basic").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.admin = this.getConfiguration().getString("Permissions.administration.admin").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.bypass = this.getConfiguration().getString("Permissions.administration.timer_bypass").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.reload = this.getConfiguration().getString("Permissions.administration.reload").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.configure = this.getConfiguration().getString("Permissions.administration.configure").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
	
		this.start = this.getConfiguration().getString("Permissions.administration.start").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.stop = this.getConfiguration().getString("Permissions.administration.stop").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.track = this.getConfiguration().getString("Permissions.administration.track").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
		this.info = this.getConfiguration().getString("Permissions.administration.info").replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix);
	}


	
}
