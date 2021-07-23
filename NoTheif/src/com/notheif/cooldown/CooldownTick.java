package com.notheif.cooldown;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitScheduler;

import com.notheif.main.NoTheif;

public class CooldownTick {
	
	private BukkitScheduler scheduler;

	public CooldownTick() {
		this.scheduler = NoTheif.getInstance().getServer().getScheduler();
	}

	@SuppressWarnings("deprecation")
	public void schedule() {

		new Thread(() -> {

	        scheduler.scheduleSyncRepeatingTask(NoTheif.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	List<CooldownRunnable> remove = new ArrayList<CooldownRunnable>();
	            	
	            	
	            	for(CooldownRunnable i : NoTheif.getInstance().cooldownManager.getRunnables()) {
	            		
	            		i.setTimer((i.getTimer()-1));
	            		
	            		if(i.getTimer() == 0) {
	            			i.onComplete();
	            			remove.add(i);
	            		}else {
	            			i.onTick();
	            		}
	            		
	            	}
	            	
	            	for(CooldownRunnable i : remove) {
	            		NoTheif.getInstance().cooldownManager.getRunnables().remove(i);
	            	}
	            }  	
	            	
	        }, 0L, 20L);
	        
	        scheduler.scheduleAsyncRepeatingTask(NoTheif.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	for(CooldownUser i : NoTheif.getInstance().cooldownManager.getUsers()) {
	            		i.tick();
	            	}
	            }  	
	            	
	        }, 0L, 20L);
			
		}, "Schedular").start();

	}
	
	public void stop() {
		this.scheduler.cancelTasks(NoTheif.getInstance());
	}
	
}
