package com.example.butcher_shop.repositories;

import com.example.butcher_shop.databases.Meat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeatRepository extends JpaRepository<Meat,Long> {
}
