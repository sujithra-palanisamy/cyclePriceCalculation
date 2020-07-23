package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.HandleDropEntity;


@Repository
public interface HandleDropRepository extends JpaRepository<HandleDropEntity, Long> {
	@Query("SELECT t FROM HandleDropEntity t WHERE :date between t.fromDate and t.toDate")
	HandleDropEntity searchPriceByBetweenDate(@Param("date")Date date);
}