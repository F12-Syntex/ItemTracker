package com.notheif.tracking;

public class Amount {

	private int greaterThan;
	private int lessThan;
	
	public Amount(int greaterThan, int lessThan) {
		this.greaterThan = greaterThan;
		this.lessThan = lessThan;
	}

	public Amount(int amount) {
		this.greaterThan = (amount-1);
		this.lessThan = (amount+1);
	}
	
	public int getGreaterThan() {
		return greaterThan;
	}

	public void setGreaterThan(int greaterThan) {
		this.greaterThan = greaterThan;
	}

	public int getLessThan() {
		return lessThan;
	}

	public void setLessThan(int lessThan) {
		this.lessThan = lessThan;
	}
	
}
