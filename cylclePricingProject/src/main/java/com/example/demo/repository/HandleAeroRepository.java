package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HandleAeroEntity;

@Repository
public interface HandleAeroRepository extends JpaRepository<HandleAeroEntity, Long> {
	@Query("SELECT t FROM HandleAeroEntity t WHERE :date between t.fromDate and t.toDate")
	HandleAeroEntity searchPriceByBetweenDate(@Param("date")Date date);
}