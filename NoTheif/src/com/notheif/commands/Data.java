
package com.notheif.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.notheif.main.NoTheif;

public class Data extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {

    }

    @Override
    public String name() {
        return "info";
    }

    @Override
    public String info() {
        return "Automatically find an item";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  NoTheif.getInstance().configManager.permissions.track;
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