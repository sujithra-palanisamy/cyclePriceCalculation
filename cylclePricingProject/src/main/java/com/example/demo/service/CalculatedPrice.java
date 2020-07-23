package com.example.demo.service;

public class CalculatedPrice {
private int price;

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

@Override
public String toString() {
	return "CalculatedPrice [price=" + price + "]";
}

}
