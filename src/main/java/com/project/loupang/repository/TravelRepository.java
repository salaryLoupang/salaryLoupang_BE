package com.project.loupang.repository;

import com.project.loupang.domain.Menu;
import com.project.loupang.domain.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
