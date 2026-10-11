package com.educonnect.repository;

import com.educonnect.model.MatriculaCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaCursoRepository extends JpaRepository<MatriculaCurso, Long> {

    // Busca matrículas por estado y las ordena de la más reciente a la más antigua
    List<MatriculaCurso> findByEstadoOrderByFechaMatriculaDesc(String estado);

    // Trae todas las matrículas ordenadas de la más reciente a la más antigua
    List<MatriculaCurso> findAllByOrderByFechaMatriculaDesc();
}