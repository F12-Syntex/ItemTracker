package com.notheif.placeholder.time;

import java.util.Comparator;
import java.util.List;

import com.notheif.config.Cooldown;
import com.notheif.main.NoTheif;

public class TimeFormater {

	public TimeFormater() {
		
	}
	
	public String parse(int seconds) {
		
		StringBuilder builder = new StringBuilder();
		Cooldown cooldown = NoTheif.getInstance().configManager.cooldown;
		
		
		List<TimeData> timeData = cooldown.getTimeData();
		
		timeData.sort(Comparator.comparingLong(TimeData::getSeconds).reversed());
		
		String coding = cooldown.coding;
		int simplified = cooldown.complexity;
		
		for(TimeData data : timeData) {
			if(data.getSeconds() <= seconds) {
				builder.append(coding.replace("%name%", data.getName()).replace("%shortname%", data.shortname).replace("%time%", seconds/data.getSeconds()+""));
				seconds -= data.getSeconds() * (seconds/data.getSeconds());
				if(simplified == 0) {
					return builder.toString().trim();
				}
				simplified-=1;
			}
		}
	
		return builder.toString().trim();
	
	}
	
}
