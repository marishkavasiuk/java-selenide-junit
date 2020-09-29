package com.socks.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Embedded extends Model{

	@JsonProperty("customer")
	private List<CustomerItem> customer;

	public void setCustomer(List<CustomerItem> customer){
		this.customer = customer;
	}

	public List<CustomerItem> getCustomer(){
		return customer;
	}

	@Override
 	public String toString(){
		return 
			"Embedded{" + 
			"customer = '" + customer + '\'' + 
			"}";
		}
}