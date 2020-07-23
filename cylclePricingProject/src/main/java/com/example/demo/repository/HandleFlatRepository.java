package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HandleFlatEntity;
import com.example.demo.Entity.HandleRiserEntity;

@Repository
public interface HandleFlatRepository extends JpaRepository<HandleFlatEntity, Long> {
	@Query("SELECT t FROM HandleFlatEntity t WHERE :date between t.fromDate and t.toDate")
	HandleFlatEntity searchPriceByBetweenDate(@Param("date")Date date);
}