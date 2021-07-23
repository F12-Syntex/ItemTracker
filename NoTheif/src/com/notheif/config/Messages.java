package com.notheif.config;

import java.util.List;

import com.notheif.utils.ComponentBuilder;
import com.notheif.utils.MessageUtils;

public class Messages extends Config{

	public String prefix = "&7[&cNoThief&7]";
	public String error = "%prefix% sorry an error has accured!";;
	public String invalid_syntax = "%prefix% &cInvalid syntax";
	public String invalid_permission = "%prefix% &cYou cant do that!";
	public String invalid_entitiy = "%prefix% &cplayers only!";
	public String invalid_help_command = "%prefix% &c%command% is not a command!";
	public String invalid_configure_command = "%prefix% &c%config% is not a valid config!";
	public String reloaded = "%prefix% &7Plugin has reloaded";
	
	public String track_added = "%prefix% &7Item added to configuration!";
	public String invalid_item = "%prefix% &cThat's not a valid item.";
	public String track_exists = "%prefix% &cThis name is already being used.";
	public String track_syntax = "%prefix% &cUsage /thieftrack track <name>";
	public String track_preset_exists = "%prefix% &cThat name is already being used!";
	
	public String invalid_preset = "%prefix% &cThat preset does not exist!";
	
	public String info_syntax = "%prefix% &cUsage /thieftrack info <name>";
	
	public String start = "%prefix% &aTracking has started!";
	public String stop = "%prefix% &cTracking has stopped!";
	
	public String noLogs = "%prefix% &cNo logs found!";
	
	public List<String> help_format = ComponentBuilder.createLore("%prefix% &b%command%&7: &c%description%", "%prefix% &bpermissions&7: &c%permission%");

	public Messages(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Messages.prefix", prefix));
		this.items.add(new ConfigItem("Messages.error", error));
		this.items.add(new ConfigItem("Messages.invalid_syntax", invalid_syntax));
		this.items.add(new ConfigItem("Messages.invalid_permission", invalid_permission));
		this.items.add(new ConfigItem("Messages.invalid_entitiy", invalid_entitiy));
		this.items.add(new ConfigItem("Messages.help.invalid_command", invalid_help_command));
		this.items.add(new ConfigItem("Messages.configure.invalid_command", invalid_configure_command));
		this.items.add(new ConfigItem("Messages.help.command_help_format", help_format));
		
		this.items.add(new ConfigItem("Messages.reload.message", reloaded));
		
		this.items.add(new ConfigItem("Messages.track.message", track_added));
		this.items.add(new ConfigItem("Messages.track.invalid_item", invalid_item));
		this.items.add(new ConfigItem("Messages.track.item_exists", track_exists));
		
		this.items.add(new ConfigItem("Messages.track.track_syntax", track_syntax));
		this.items.add(new ConfigItem("Messages.track.preset_exists", track_preset_exists));
		this.items.add(new ConfigItem("Messages.track.invalid_preset", invalid_preset));
		
		this.items.add(new ConfigItem("Messages.start", start));
		this.items.add(new ConfigItem("Messages.stop", stop));
		
		this.items.add(new ConfigItem("Messages.noLogs", noLogs));
		
		this.items.add(new ConfigItem("Messages.info.syntax", info_syntax));
		
		
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.MESSAGES;
	}
	
	@Override
	public void initialize() {
		this.prefix = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.prefix"));
		this.error = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.error").replace("%prefix%", prefix));
		this.invalid_syntax = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_syntax").replace("%prefix%", prefix));
		this.invalid_permission = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_permission").replace("%prefix%", prefix));
		this.invalid_entitiy = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_entitiy").replace("%prefix%", prefix));
		this.invalid_help_command = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.help.invalid_command").replace("%prefix%", prefix));
		this.help_format = ComponentBuilder.createLore(this.getConfiguration().getStringList("Messages.help.command_help_format"));
		this.invalid_configure_command = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.configure.invalid_command").replace("%prefix%", prefix));
		
		this.reloaded = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.reload.message").replace("%prefix%", prefix));
		
		this.track_added = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.message").replace("%prefix%", prefix));
		this.invalid_item = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.invalid_item").replace("%prefix%", prefix));
		this.track_exists = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.item_exists").replace("%prefix%", prefix));
		
		this.start = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.start").replace("%prefix%", prefix));
		this.stop = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.stop").replace("%prefix%", prefix));
		
		this.track_syntax = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.track_syntax").replace("%prefix%", prefix));
		this.track_preset_exists = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.preset_exists").replace("%prefix%", prefix));
		this.invalid_preset = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.track.invalid_preset").replace("%prefix%", prefix));
		
		this.info_syntax = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.info.syntax").replace("%prefix%", prefix));
		
		this.noLogs = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.noLogs").replace("%prefix%", prefix));
	}


	
}
