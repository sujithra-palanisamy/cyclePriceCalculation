package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FrameCantiLeverEntity;

@Repository
public interface FrameCantileverRepository extends JpaRepository<FrameCantiLeverEntity, Long> {
	@Query("SELECT t FROM FrameCantiLeverEntity t WHERE :date between t.fromDate and t.toDate")
	FrameCantiLeverEntity searchPriceByBetweenDate(@Param("date")Date date);
}