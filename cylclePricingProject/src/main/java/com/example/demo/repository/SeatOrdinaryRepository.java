package com.example.demo.repository;


import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.SeatOrdinaryEntity;
import com.example.demo.Entity.WheelRimEntity;
import com.example.demo.Entity.WheelSpokesEntity;
import com.example.demo.Entity.WheelTubeEntity;
import com.example.demo.Entity.WheelTyreEntity;
import com.example.demo.service.Wheel;

@Repository
public interface SeatOrdinaryRepository extends JpaRepository<SeatOrdinaryEntity, Long> {
	@Query("SELECT t FROM SeatOrdinaryEntity t WHERE :date between t.fromDate and t.toDate")
	SeatOrdinaryEntity searchPriceByBetweenDate(@Param("date")Date date);
}
