package com.example.demo.service;

import java.sql.Date;

public class Seating {
	  private String cusion;
	  private Date cusionDate;
	  private String leather;
	  private Date leatherDate;
	public String getCusion() {
		return cusion;
	}
	public void setCusion(String cusion) {
		this.cusion = cusion;
	}
	public Date getCusionDate() {
		return cusionDate;
	}
	public void setCusionDate(Date cusionDate) {
		this.cusionDate = cusionDate;
	}
	
	public String getLeather() {
		return leather;
	}
	public void setLeather(String leather) {
		this.leather = leather;
	}
	public Date getLeatherDate() {
		return leatherDate;
	}
	public void setLeatherDate(Date leatherDate) {
		this.leatherDate = leatherDate;
	}
	  
}
