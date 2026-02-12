package com.example.public_transportation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation.Models.Line;
import java.util.Optional;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
    Optional<Line> findByNumber(String lineNumber); // שנה מ-int ל-String
}
