package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FrameProneEntity;

@Repository
public interface FrameProneRepository extends JpaRepository<FrameProneEntity, Long> {
	@Query("SELECT t FROM FrameProneEntity t WHERE :date between t.fromDate and t.toDate")
	FrameProneEntity searchPriceByBetweenDate(@Param("date")Date date);
}