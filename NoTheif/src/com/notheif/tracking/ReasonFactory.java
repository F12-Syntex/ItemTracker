package com.notheif.tracking;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.notheif.main.NoTheif;

public class ReasonFactory {
	
	public static boolean checkItem(ItemStack stack) {

		if(stack == null) return false;
		if(!stack.hasItemMeta()) return false;
		if(!stack.getItemMeta().hasDisplayName()) return false;
		
		System.out.println("Checking item");
		
		String strippedColour = ChatColor.stripColor(stack.getItemMeta().getDisplayName()).trim();
		
		for(TrackingData i : NoTheif.getInstance().configManager.tracking.data) {
			
			//System.out.println("\"" + strippedColour + "\"" + " with " + "\"" + i.getPreset() + "\"");
			
			if(strippedColour.equalsIgnoreCase(i.getPreset().trim())) {
				
				//System.out.println("They are equal, comparing amounts");
				
				if(i.getAmount().isEmpty()) {
					//System.out.println("No amount defined.");
					return true;
				}else {
					
					for(Amount o : i.getAmount()) {
						
						//System.out.println("Item amount is: " + stack.getAmount());
						//System.out.println("Checking if item is more than " + o.getGreaterThan() + " and less that " + o.getLessThan());
						
						if(stack.getAmount() > o.getGreaterThan() && stack.getAmount() < o.getLessThan()) {
							//System.out.println("Item has been verified.");
							return true;
						}
						
					}
					
				}
				
				return false;
			}
			
		}
		
		return false;
	}
	
	@SuppressWarnings("unused")
	private static boolean checkAmount(ItemStack stack, TrackingData data) {
		
		if(data.getAmount().isEmpty()) return true;
		
		for(Amount i : data.getAmount()) {
			if(i.getGreaterThan() > stack.getAmount()) {
				if(i.getLessThan() < stack.getAmount()) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
}
