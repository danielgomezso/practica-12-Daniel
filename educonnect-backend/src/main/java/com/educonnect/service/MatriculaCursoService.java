package com.educonnect.service;

import com.educonnect.dto.CrearMatriculaDTO;
import com.educonnect.dto.MatriculaCursoDTO;
import com.educonnect.model.Estudiante;
import com.educonnect.model.MatriculaCurso;
import com.educonnect.model.Profesor;
import com.educonnect.repository.EstudianteRepository;
import com.educonnect.repository.MatriculaCursoRepository;
import com.educonnect.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaCursoService {

    private final MatriculaCursoRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;

    // Inyección de dependencias mediante el constructor
    public MatriculaCursoService(MatriculaCursoRepository matriculaRepository,EstudianteRepository estudianteRepository,ProfesorRepository profesorRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
    }

    // 1. Obtener Todas
    public List<MatriculaCursoDTO> obtenerTodas() {
        return matriculaRepository.findAllByOrderByFechaMatriculaDesc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 2. Obtener Por Estado
    public List<MatriculaCursoDTO> obtenerPorEstado(String estado) {
        return matriculaRepository.findByEstadoOrderByFechaMatriculaDesc(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 3. Crear Matrícula
    public MatriculaCursoDTO crearMatricula(CrearMatriculaDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));

        Profesor profesor = profesorRepository.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado."));

        // Regla de Negocio: Validar profesor activo
        if (!profesor.getActivo()) {
            throw new IllegalArgumentException("El profesor seleccionado no se encuentra activo.");
        }

        MatriculaCurso matricula = new MatriculaCurso();
        // Regla de Negocio: Generar código dinámico
        matricula.setCodigoMatricula("MAT-2026-" + System.currentTimeMillis());
        matricula.setEstudiante(estudiante);
        matricula.setProfesor(profesor);
        matricula.setNombreCurso(dto.getNombreCurso());
        matricula.setCreditos(dto.getCreditos());
        matricula.setPeriodo(dto.getPeriodo());
        // Regla de Negocio: Estado inicial
        matricula.setEstado("ACTIVA");
        matricula.setMontoArancel(dto.getMontoArancel());
        // Regla de Negocio: Fecha actual
        matricula.setFechaMatricula(LocalDateTime.now());

        MatriculaCurso guardada = matriculaRepository.save(matricula);
        return mapToDTO(guardada);
    }

    // 4. Actualizar Estado
    public MatriculaCursoDTO actualizarEstado(Long id, String nuevoEstado) {
        MatriculaCurso matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        matricula.setEstado(nuevoEstado);
        MatriculaCurso actualizada = matriculaRepository.save(matricula);
        return mapToDTO(actualizada);
    }

    // Método auxiliar para mapear de Entidad a DTO
    private MatriculaCursoDTO mapToDTO(MatriculaCurso matricula) {
        MatriculaCursoDTO dto = new MatriculaCursoDTO();
        dto.setId(matricula.getId());
        dto.setCodigoMatricula(matricula.getCodigoMatricula());
        dto.setNombreEstudiante(matricula.getEstudiante().getNombreCompleto());
        dto.setCarnetEstudiante(matricula.getEstudiante().getCarnet());
        dto.setNombreProfesor(matricula.getProfesor().getNombre());
        dto.setNombreCurso(matricula.getNombreCurso());
        dto.setCreditos(matricula.getCreditos());
        dto.setPeriodo(matricula.getPeriodo());
        dto.setEstado(matricula.getEstado());
        dto.setMontoArancel(matricula.getMontoArancel());
        dto.setFechaMatricula(matricula.getFechaMatricula());
        return dto;
    }
}