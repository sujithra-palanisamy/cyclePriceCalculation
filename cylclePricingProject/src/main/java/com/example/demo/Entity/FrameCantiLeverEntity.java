package com.example.demo.Entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name ="frame_cantilever")
public class FrameCantiLeverEntity {
	
	@Id
	@Column(name = "lever_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leverId;
	
	@Column(name="from_date")
	@JsonFormat(pattern="YYYY-MM_DD")
	private Date fromDate; 
	
	@Column(name="to_date")
	@JsonFormat(pattern="YYYY-MM_DD")
	private Date toDate; 
	
	@Column(name="price")
	private Long price;

	public Long getLeverId() {
		return leverId;
	}

	public void setLeverId(Long leverId) {
		this.leverId = leverId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
