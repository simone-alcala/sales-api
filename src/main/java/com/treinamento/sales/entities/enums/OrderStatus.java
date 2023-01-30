package com.treinamento.sales.entities.enums;

public enum OrderStatus {
	WAITING_PAYMENT("WAITING_PAYMENT"),
	PAID("PAID"),
	PACKING("PACKING"),
	SHIPPED("SHIPPED"),
	DELIVERED("DELIVERED"),
	CANCELED("CANCELED");
	
	private String code;
	
	private OrderStatus(String code) {
		this.code = code.toUpperCase();
	}
	
	public String getCode() {
		return code;
	}
	
}
