package com.notheif.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.configuration.ConfigurationSection;

import com.notheif.tracking.Amount;
import com.notheif.tracking.TrackingData;

public class Tracking extends Config{

	public String prefix = "&7[&cNoThief&7]";
	
	public List<TrackingData> data = new ArrayList<TrackingData>();

	public Tracking(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Tracking.lookfor.0.Amount.0.GreaterThan", 1));
		this.items.add(new ConfigItem("Tracking.lookfor.0.Amount.0.LessThan", 5));
		this.items.add(new ConfigItem("Tracking.lookfor.0.Amount.1.GreaterThan", 5));
		this.items.add(new ConfigItem("Tracking.lookfor.0.Amount.1.LessThan", 65));
		

	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.TRACKING;
	}
	
	@Override
	public void initialize() {
		
		ConfigurationSection reports = this.getConfiguration().getConfigurationSection("Tracking.lookfor");
		
		for(String preset : reports.getKeys(false)) {
			
			ConfigurationSection report = reports.getConfigurationSection(preset);
			
			List<Amount> amounts = new ArrayList<Amount>();
			
			if(report.isConfigurationSection("Amount")) {
				
				ConfigurationSection amountSection = report.getConfigurationSection("Amount");
				
				for(String amountID : amountSection.getKeys(false)) {
					
					ConfigurationSection amount = amountSection.getConfigurationSection(amountID);
					
					int greaterThan = amount.getInt("GreaterThan");
					int lessThan = amount.getInt("LessThan");
					
					Amount data = new Amount(greaterThan, lessThan);
					
					amounts.add(data);
					
				}
				
			}
			
			TrackingData trackingData = new TrackingData(preset, amounts);
			
			this.data.add(trackingData);
			
		}
		
	}
	
	public void update() {
		
		List<TrackingData> distinct = this.data.stream().distinct().collect(Collectors.toList());
		
		ConfigurationSection section = this.getConfiguration().getConfigurationSection("Tracking.lookfor");
		
		for(TrackingData trackingData : distinct) {
			
			section.set(trackingData.getPreset() + ".title", trackingData.getTitle());
			
			if(!trackingData.getAmount().isEmpty()) {
			
				int counter = 0;
				
				for(Amount amount : trackingData.getAmount()) {
				
					section.set(trackingData.getPreset() + ".Amount." + counter + ".GreaterThan", amount.getGreaterThan());
					section.set(trackingData.getPreset() + ".Amount." + counter + ".LessThan", amount.getLessThan());
				
				}
				
				counter++;
			}
			
			
		}
		
		this.save();
		
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<TrackingData> getData() {
		return data;
	}

	public void setData(List<TrackingData> data) {
		this.data = data;
	}


	
}
