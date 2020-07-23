package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.WheelRimEntity;

	@Repository
	public interface WheelRimRepository extends JpaRepository<WheelRimEntity, Long> {
		@Query("SELECT t FROM WheelRimEntity t WHERE :date between t.fromDate and t.toDate")
		WheelRimEntity searchPriceByBetweenDate(@Param("date")Date date);
	}

