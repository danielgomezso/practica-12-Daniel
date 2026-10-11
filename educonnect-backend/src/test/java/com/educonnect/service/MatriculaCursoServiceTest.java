package com.educonnect.service;

import com.educonnect.dto.CrearMatriculaDTO;
import com.educonnect.dto.MatriculaCursoDTO;
import com.educonnect.model.Estudiante;
import com.educonnect.model.MatriculaCurso;
import com.educonnect.model.Profesor;
import com.educonnect.repository.EstudianteRepository;
import com.educonnect.repository.MatriculaCursoRepository;
import com.educonnect.repository.ProfesorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatriculaCursoServiceTest {

    @Mock
    private MatriculaCursoRepository matriculaRepository;
    
    @Mock
    private EstudianteRepository estudianteRepository;
    
    @Mock
    private ProfesorRepository profesorRepository;
    
    @InjectMocks
    private MatriculaCursoService service;

    @Test
    void crearMatricula_Exito() {
        // Arrange (Preparar los datos de prueba)
        CrearMatriculaDTO dto = new CrearMatriculaDTO();
        dto.setEstudianteId(1L);
        dto.setProfesorId(1L);
        dto.setNombreCurso("Desarrollo Web");
        dto.setCreditos(4);
        dto.setPeriodo("II-2026");
        dto.setMontoArancel(new BigDecimal("45000.00"));

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombreCompleto("Valeria Monge");

        Profesor profesor = new Profesor();
        profesor.setId(1L);
        profesor.setNombre("Dr. Armando Ramos");
        profesor.setActivo(true);

        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor));
        when(matriculaRepository.save(any(MatriculaCurso.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act (Ejecutar el método a probar)
        MatriculaCursoDTO resultado = service.crearMatricula(dto);

        // Assert (Verificar que los resultados sean los esperados)
        assertNotNull(resultado);
        assertTrue(resultado.getCodigoMatricula().startsWith("MAT-2026-"));
        assertEquals("ACTIVA", resultado.getEstado());
        verify(matriculaRepository, times(1)).save(any(MatriculaCurso.class));
    }
}