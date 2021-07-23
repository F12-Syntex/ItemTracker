
package com.notheif.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.notheif.main.NoTheif;
import com.notheif.utils.MessageUtils;

public class Stop extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	NoTheif.getInstance().configManager.settings.getConfiguration().set("Tracking.track", false);
    	NoTheif.getInstance().configManager.settings.save();
    	MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.stop);
    	
    }

    @Override
    public String name() {
        return "stop";
    }

    @Override
    public String info() {
        return "Stops tracking";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  NoTheif.getInstance().configManager.permissions.stop;
	}
	
	@Override
	public AutoComplete autoComplete(CommandSender sender) {
		AutoComplete tabCompleter = new AutoComplete();
		
		List<SubCommand> commands = NoTheif.getInstance().CommandManager.getCommands();
		
		for(SubCommand i : commands) {
			tabCompleter.createEntry(i.name());
		}
		
		return tabCompleter;
	}
	

}