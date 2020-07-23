package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FrameDiamodEntity;

@Repository
public interface FrameDiamondRepository extends JpaRepository<FrameDiamodEntity, Long> {
	@Query("SELECT t FROM FrameDiamodEntity t WHERE :date between t.fromDate and t.toDate")
	FrameDiamodEntity searchPriceByBetweenDate(@Param("date")Date date);
}