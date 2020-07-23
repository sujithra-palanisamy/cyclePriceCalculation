package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class CyclePriceRequest {
	
	private Wheel wheel;
	private Seating seating;
	private HandleandBreak handleandBreak;
	private Frame frame;
	private ChainAssembly assembly;
	public Wheel getWheel() {
		return wheel;
	}
	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}
	public Seating getSeating() {
		return seating;
	}
	public void setSeating(Seating seating) {
		this.seating = seating;
	}
	public HandleandBreak getHandleandBreak() {
		return handleandBreak;
	}
	public void setHandleandBreak(HandleandBreak handleandBreak) {
		this.handleandBreak = handleandBreak;
	}
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	public ChainAssembly getAssembly() {
		return assembly;
	}
	public void setAssembly(ChainAssembly assembly) {
		this.assembly = assembly;
	}
	
}
