
package com.notheif.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.notheif.main.NoTheif;
import com.notheif.utils.MessageUtils;

public class Help extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	if(args.length <= 1) {
    		MessageUtils.sendHelp(player);
    		return;
    	}
    	
    	SubCommand command = NoTheif.getInstance().CommandManager.get(args[1]);
    	
    	if(command == null) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.invalid_help_command.replace("%command%", args[1]));
    		return;
    	}
    	
    	for(String i : NoTheif.getInstance().configManager.messages.help_format) {
    		MessageUtils.sendRawMessage(player, i.replace("%command%", args[1]).replace("%description%", command.info()).replace("%permission%", command.permission()).replace("%prefix%", NoTheif.getInstance().configManager.messages.prefix));
    	}
    	
    }

    @Override

    public String name() {
        return "help";
    }

    @Override
    public String info() {
        return "displays the help command";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  NoTheif.getInstance().configManager.permissions.basic;
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