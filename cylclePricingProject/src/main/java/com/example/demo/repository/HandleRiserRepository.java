package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HandleRiserEntity;
import com.example.demo.Entity.SeatCusionEntity;

	@Repository
	public interface HandleRiserRepository extends JpaRepository<HandleRiserEntity, Long> {
		@Query("SELECT t FROM HandleRiserEntity t WHERE :date between t.fromDate and t.toDate")
		HandleRiserEntity searchPriceByBetweenDate(@Param("date")Date date);
	}
