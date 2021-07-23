package com.notheif.tracking;

import java.util.List;

public class TrackingData {

	private String preset;
	private String name;
	private String title;
	private List<Amount> amount;
	
	public TrackingData(String preset, List<Amount> amount) {
		 this.preset = preset;
		 this.setTitle(title);
		 this.amount = amount;
	}

	public String getPreset() {
		return preset;
	}

	public void setPreset(String preset) {
		this.preset = preset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Amount> getAmount() {
		return amount;
	}

	public void setAmount(List<Amount> amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
