package com.example.demo.service;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class CycleResponseEntity {
	
	private Long frameprice;
	
	private Long handlePrice;
	
	private Long seatPrice;
	
	private Long chainPrice;
	
	private Long wheelPrice;
	
	private Long cycleTotalPice;

	public Long getFrameprice() {
		return frameprice;
	}

	public void setFrameprice(Long frameprice) {
		this.frameprice = frameprice;
	}

	public Long getHandlePrice() {
		return handlePrice;
	}

	public void setHandlePrice(Long handlePrice) {
		this.handlePrice = handlePrice;
	}

	public Long getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Long seatPrice) {
		this.seatPrice = seatPrice;
	}

	public Long getChainPrice() {
		return chainPrice;
	}

	public void setChainPrice(Long chainPrice) {
		this.chainPrice = chainPrice;
	}

	public Long getWheelPrice() {
		return wheelPrice;
	}

	public void setWheelPrice(Long wheelPrice) {
		this.wheelPrice = wheelPrice;
	}

	public Long getCycleTotalPice() {
		return cycleTotalPice;
	}

	public void setCycleTotalPice(Long cycleTotalPice) {
		this.cycleTotalPice = cycleTotalPice;
	}
	
	
}
