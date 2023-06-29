package com.example.academic.repository;

import com.example.academic.models.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {
    @Query(value = "select a.* from academic a where a.user_id = ?1",nativeQuery = true)
    List<Map<String, Objects>> findByUserId(Long userId);

    @Query(value = "select a.* from academic a",nativeQuery = true)
    List<Map<String, Objects>> getByGrade(String grade);
}
