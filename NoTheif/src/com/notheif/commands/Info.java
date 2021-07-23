
package com.notheif.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.notheif.config.Tracking;
import com.notheif.main.NoTheif;
import com.notheif.tracking.Reason;
import com.notheif.tracking.TrackingData;
import com.notheif.utils.MessageUtils;

public class Info extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	if(args.length < 2) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.info_syntax);
    		return;
    	}
    	
    	String preset = "";
    	
    	for(int i = 1; i < args.length; i++) {
    		preset += args[i] + " ";
    	}
    	
    	preset = preset.trim();
    	
    	Tracking tracking = NoTheif.getInstance().configManager.tracking;

    	boolean exists = false;
    	
    	for(TrackingData data : tracking.data) {
    		if(data.getPreset().equalsIgnoreCase(preset)) {
    			exists = true;
    			break;
    		}
    	}
    	
    	if(!exists) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.invalid_preset);
    		return;
    	}
    	
    	List<Reason> data = NoTheif.getInstance().configManager.logs.getData(preset);
    	
    	if(data.isEmpty()) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.noLogs);
    		return;
    	}
    	
    	
    	//for(Reason o : data) {
        //	MessageUtils.sendMessage(player, o.reported);
    	//}
    	
    	MessageUtils.sendMessage(player, data.get(data.size()-1).reported);
    	
    }

    @Override
    public String name() {
        return "info";
    }

    @Override
    public String info() {
        return "displays where the item was last seen.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  NoTheif.getInstance().configManager.permissions.start;
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