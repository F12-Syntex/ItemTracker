package com.notheif.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.notheif.config.Logs;
import com.notheif.config.Settings;
import com.notheif.main.NoTheif;
import com.notheif.tracking.Cause;
import com.notheif.tracking.Reason;
import com.notheif.tracking.ReasonFactory;
import com.notheif.utils.StringMinipulation;
import com.notheif.utils.Time;

public class TrackingEvent extends SubEvent{

	private Settings settings;
	private Logs logs;
	private Map<UUID, List<ItemStack>> users = new HashMap<UUID, List<ItemStack>>();
	
	public TrackingEvent() {
	
		this.settings = NoTheif.getInstance().configManager.settings;
		this.logs = NoTheif.getInstance().configManager.logs;
		
	}
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "tracking event";
	}
	
	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Tracks all item movements";
	}
	
	@EventHandler
    public void playerOpenInventory(InventoryOpenEvent e) {
		
		List<ItemStack> items = new ArrayList<ItemStack>();
		
		for(ItemStack i : e.getPlayer().getInventory().getContents()) {
			if(i == null) continue;
			items.add(i.clone());
		}
		
		users.put(e.getPlayer().getUniqueId(), items);
	}
	
	@EventHandler
    public void playerCloseInventory(InventoryCloseEvent e) {
		
	
		if(!users.containsKey(e.getPlayer().getUniqueId())) return;
		
		List<ItemStack> base = users.get(e.getPlayer().getUniqueId());
		
		List<ItemStack> original = new ArrayList<ItemStack>();
		ArrayList<ItemStack> updated = new ArrayList<ItemStack>();
		
		for(ItemStack i : base) {
			
			if(ReasonFactory.checkItem(i)) {
				original.add(i);
			}
		}
		
		for(ItemStack i : e.getPlayer().getInventory().getContents()) {
			if(ReasonFactory.checkItem(i)) updated.add(i);
		}
		
		Location location = e.getPlayer().getLocation();
		
		List<Reason> reasons = new ArrayList<Reason>();
		
		//new items
		for(ItemStack i : updated) {
			if(!original.contains(i)) {
				 Reason reason = new Reason(i.getItemMeta().getDisplayName() + "&7 has been taken from, " + e.getInventory().getName() + ", at x: " +
  						location.getBlockX() + ", y: " +
  						location.getBlockY() + ", z: " +
  						location.getBlockZ(), Cause.MOVED.name(), e.getPlayer().getUniqueId(), null, i, Time.getCurrentTimeInDate()); 
				 
				 reasons.add(reason);
			}
		}
		
		//removed items
		for(ItemStack i : original) {
			if(!updated.contains(i)) {
				 Reason reason = new Reason(i.getItemMeta().getDisplayName() + "&7 has been put in , " + e.getInventory().getName() + ", at x: " +
  						location.getBlockX() + ", y: " +
  						location.getBlockY() + ", z: " +
  						location.getBlockZ(), Cause.MOVED.name(), e.getPlayer().getUniqueId(), null, i, Time.getCurrentTimeInDate()); 
				 
				 reasons.add(reason);
			}
		}
		
	       if(reasons.isEmpty()) return;
	        
	       for(Reason i : reasons) {
	          this.logs.addData(i);
	       }
		   
		   this.logs.update();
		
		   users.remove(e.getPlayer().getUniqueId());
		   
		
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        Player p = (Player) e.getPlayer();
        
		Item droppedItem = e.getItem();
		
		if(this.settings.get(Cause.PICKED_UP) && ReasonFactory.checkItem(droppedItem.getItemStack())) {
			
			String name = StringMinipulation.capitalizeString(droppedItem.getItemStack().getType().name().toLowerCase().replace("_", " "));
			
			if(droppedItem.getItemStack().hasItemMeta()) {
				name = droppedItem.getItemStack().getItemMeta().getDisplayName();
			}
			
			Reason reason = new Reason(p.getName() + " has picked up " + name + "&7 on the location x: " +
			droppedItem.getLocation().getBlockX() + ", y: " +
			droppedItem.getLocation().getBlockY() + ", z: " +
			droppedItem.getLocation().getBlockZ(), Cause.PICKED_UP.name(), e.getPlayer().getUniqueId(), null, droppedItem.getItemStack(), Time.getCurrentTimeInDate());
			
			this.logs.addData(reason);
			this.logs.update();
			
		}
    }
	
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e) {
        
		Item droppedItem = e.getEntity();
		
		if(this.settings.get(Cause.PICKED_UP) && ReasonFactory.checkItem(droppedItem.getItemStack())) {
			
			String name = StringMinipulation.capitalizeString(droppedItem.getItemStack().getType().name().toLowerCase().replace("_", " "));
			
			if(droppedItem.getItemStack().hasItemMeta()) {
				name = droppedItem.getItemStack().getItemMeta().getDisplayName();
			}
			
			Reason reason = new Reason(name + "&7 has despawned on the location x: " +
			droppedItem.getLocation().getBlockX() + ", y: " +
			droppedItem.getLocation().getBlockY() + ", z: " +
			droppedItem.getLocation().getBlockZ(), Cause.PICKED_UP.name(), null, null, droppedItem.getItemStack(), Time.getCurrentTimeInDate());
			
			this.logs.addData(reason);
			this.logs.update();
			
		}
    }
	
	
	@EventHandler
	public void onItemDespawn(EntityCombustEvent e) {
        
		if(!(e.getEntity() instanceof Item)) {
			return;
		}
		
		Item droppedItem = (Item) e.getEntity();
		
		if(this.settings.get(Cause.BURNED) && ReasonFactory.checkItem(droppedItem.getItemStack())) {
			
			String name = StringMinipulation.capitalizeString(droppedItem.getItemStack().getType().name().toLowerCase().replace("_", " "));
			
			if(droppedItem.getItemStack().hasItemMeta()) {
				name = droppedItem.getItemStack().getItemMeta().getDisplayName();
			}
			
			Reason reason = new Reason(name + "&7 has burnt on the location x: " +
			droppedItem.getLocation().getBlockX() + ", y: " +
			droppedItem.getLocation().getBlockY() + ", z: " +
			droppedItem.getLocation().getBlockZ(), Cause.BURNED.name(), null, null, droppedItem.getItemStack(), Time.getCurrentTimeInDate());
			
			this.logs.addData(reason);
			this.logs.update();
			
		}
    }
	
	@EventHandler
	public void onItemDropped(PlayerDropItemEvent e) {
		
		Item droppedItem = e.getItemDrop();
		
		if(this.settings.get(Cause.DROPPED) && ReasonFactory.checkItem(droppedItem.getItemStack())) {
			
			System.out.println("asd");
			
			String name = StringMinipulation.capitalizeString(droppedItem.getItemStack().getType().name().toLowerCase().replace("_", " "));
			
			if(droppedItem.getItemStack().hasItemMeta()) {
				name = droppedItem.getItemStack().getItemMeta().getDisplayName();
			}
			
			Reason reason = new Reason(e.getPlayer().getDisplayName() + " has dropped " + name + "&7 on the location x: " +
			droppedItem.getLocation().getBlockX() + ", y: " +
			droppedItem.getLocation().getBlockY() + ", z: " +
			droppedItem.getLocation().getBlockZ(), Cause.DROPPED.name(), e.getPlayer().getUniqueId(), null, droppedItem.getItemStack(), Time.getCurrentTimeInDate());
			
			this.logs.addData(reason);
			this.logs.update();
			
		}
		
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		for(ItemStack droppedItem : e.getDrops()) {
			
			if(this.settings.get(Cause.DEATH) && ReasonFactory.checkItem(droppedItem)) {
				
				String name = StringMinipulation.capitalizeString(droppedItem.getType().name().toLowerCase().replace("_", " "));
				
				if(droppedItem.hasItemMeta()) {
					name = droppedItem.getItemMeta().getDisplayName();
				}
				
				Reason reason = new Reason(e.getEntity().getDisplayName() + " has died, and dropped, " + name + "&7 on the location x: " +
				e.getEntity().getLocation().getBlockX() + ", y: " +
				e.getEntity().getLocation().getBlockY() + ", z: " +
				e.getEntity().getLocation().getBlockZ(), Cause.DEATH.name(), e.getEntity().getUniqueId(), null, droppedItem, Time.getCurrentTimeInDate());
				
				this.logs.addData(reason);
				this.logs.update();
				
			}
			
		}
		
	}
	

}
