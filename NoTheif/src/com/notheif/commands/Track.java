
package com.notheif.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.notheif.config.Tracking;
import com.notheif.main.NoTheif;
import com.notheif.tracking.Amount;
import com.notheif.tracking.TrackingData;
import com.notheif.utils.MessageUtils;

public class Track extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	if(args.length < 2) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.track_syntax);
    		return;
    	}
    	
    	
    	String preset = "";
    	
    	for(int i = 1; i < args.length; i++) {
    		preset += args[i] + " ";
    	}
    	
    	preset = preset.trim();
    	
    	/*
    	if(player.getInventory().getItemInHand().getType() == Material.AIR) {
    		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.invalid_item);
    		return;
    	}
    	*/

		ItemStack item = player.getInventory().getItemInMainHand();
		
    	Tracking tracking = NoTheif.getInstance().configManager.tracking;

    	for(TrackingData data : tracking.data) {
    		if(data.getPreset().equalsIgnoreCase(preset)) {
        		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.track_preset_exists);
    			return;
    		}
    	}

    	
    	
    	/*
    	String name = "";
    	List<String> lore = ComponentBuilder.createLore();
  
    	if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
    		name = item.getItemMeta().getDisplayName();
    		lore = item.getItemMeta().getLore();
    	}else if(item.hasItemMeta()) {
    		lore = item.getItemMeta().getLore();
    	}
    	*/
    	
    	/*
    	
    	ConfigurationSection items = configuration.getConfigurationSection("Tracking.lookfor");
    	
    	for(String uuid : items.getKeys(false)) {
    		
    		ConfigurationSection data = items.getConfigurationSection(uuid);
    		/*
    		String data_name = data.getString("Name");
    		List<String> data_lore = data.getStringList("Lore");
    		String ItemType = data.getString("ItemType");
    		
    		
    		ItemStack obj = data.getItemStack("item");
    		
    		//if(data_name.equals(name) && lore.equals(data_lore) && item.getType().name().equals(ItemType)) {
    		if(item.equals(obj)) {
    			MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.track_exists);
    			return;
    		}
    	
    		
    	}
    	*/
    	
    	List<Amount> amount = new ArrayList<Amount>();
    	
    	if(item.getType() == Material.AIR || item.getAmount() == 0) {
        	amount.add(new Amount(-999, 999));
    	}else {
        	amount.add(new Amount(item.getAmount()));
    	}
    	
    	
    	TrackingData trackingData = new TrackingData(preset, amount);
    	
    	tracking.data.add(trackingData);
    	
    	tracking.update();
    	
		MessageUtils.sendRawMessage(player, NoTheif.getInstance().configManager.messages.track_added);
    	
    }

    @Override
    public String name() {
        return "track";
    }

    @Override
    public String info() {
        return "Automatically add the item in hand to the tracking";
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