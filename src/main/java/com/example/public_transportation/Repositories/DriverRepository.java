package com.example.public_transportation.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation.Models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{}