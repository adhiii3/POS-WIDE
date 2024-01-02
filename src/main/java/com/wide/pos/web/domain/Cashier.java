package com.wide.pos.web.domain;

public class Cashier {
	private String nip;
	private String name;
	
	public Cashier(String nip, String name) {
		this.nip = nip;
		this.name = name;
	}
	public String getNip() {
		return nip;
	}
	public String getName() {
		return name;
	}
	
	
}
