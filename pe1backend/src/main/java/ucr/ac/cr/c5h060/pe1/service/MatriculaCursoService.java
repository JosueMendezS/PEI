package ucr.ac.cr.c5h060.pe1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import ucr.ac.cr.c5h060.pe1.dto.CrearMatriculaDTO;
import ucr.ac.cr.c5h060.pe1.dto.MatriculaCursoDTO;
import ucr.ac.cr.c5h060.pe1.exception.ResourceNotFoundException;
import ucr.ac.cr.c5h060.pe1.model.Estudiante;
import ucr.ac.cr.c5h060.pe1.model.MatriculaCurso;
import ucr.ac.cr.c5h060.pe1.model.Profesor;
import ucr.ac.cr.c5h060.pe1.repository.EstudianteRepository;
import ucr.ac.cr.c5h060.pe1.repository.MatriculaCursoRepository;
import ucr.ac.cr.c5h060.pe1.repository.ProfesorRepository;

@Service
public class MatriculaCursoService {

    private final MatriculaCursoRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;

    public MatriculaCursoService(MatriculaCursoRepository matriculaRepository,
            EstudianteRepository estudianteRepository,
            ProfesorRepository profesorRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
    }

    // 1. Todas, ordenadas por fecha descendente
    public List<MatriculaCursoDTO> obtenerTodas() {
        return matriculaRepository.findAllByOrderByFechaMatriculaDesc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 2. Filtradas por estado
    public List<MatriculaCursoDTO> obtenerPorEstado(String estado) {
        return matriculaRepository.findByEstadoOrderByFechaMatriculaDesc(estado.toUpperCase())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 3. Crear matrícula
    public MatriculaCursoDTO crearMatricula(CrearMatriculaDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estudiante no encontrado con id " + dto.getEstudianteId()));

        Profesor profesor = profesorRepository.findById(dto.getProfesorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor no encontrado con id " + dto.getProfesorId()));

        if (!Boolean.TRUE.equals(profesor.getActivo())) {
            throw new IllegalArgumentException("El profesor seleccionado no se encuentra activo.");
        }

        MatriculaCurso matricula = new MatriculaCurso();
        matricula.setCodigoMatricula("MAT-2026-" + ThreadLocalRandom.current().nextInt(1000, 10000));
        matricula.setEstudiante(estudiante);
        matricula.setProfesor(profesor);
        matricula.setNombreCurso(dto.getNombreCurso());
        matricula.setCreditos(dto.getCreditos());
        matricula.setPeriodo(dto.getPeriodo());
        matricula.setMontoArancel(dto.getMontoArancel());
        matricula.setEstado("ACTIVA");
        matricula.setFechaMatricula(LocalDateTime.now());

        return toDTO(matriculaRepository.save(matricula));
    }

    // 4. Cambiar el estado
    public MatriculaCursoDTO actualizarEstado(Long id, String nuevoEstado) {
        String estado = nuevoEstado.toUpperCase();
        if (!Set.of("ACTIVA", "COMPLETADA", "CANCELADA").contains(estado)) {
            throw new IllegalArgumentException("Estado inválido: " + nuevoEstado);
        }

        MatriculaCurso matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Matrícula no encontrada con id " + id));

        matricula.setEstado(estado);
        return toDTO(matriculaRepository.save(matricula));
    }

    // Convierte Entity a DTO
    private MatriculaCursoDTO toDTO(MatriculaCurso m) {
        MatriculaCursoDTO d = new MatriculaCursoDTO();
        d.setId(m.getId());
        d.setCodigoMatricula(m.getCodigoMatricula());
        d.setNombreEstudiante(m.getEstudiante().getNombreCompleto());
        d.setCarnetEstudiante(m.getEstudiante().getCarnet());
        d.setNombreProfesor(m.getProfesor().getNombre());
        d.setNombreCurso(m.getNombreCurso());
        d.setCreditos(m.getCreditos());
        d.setPeriodo(m.getPeriodo());
        d.setEstado(m.getEstado());
        d.setMontoArancel(m.getMontoArancel());
        d.setFechaMatricula(m.getFechaMatricula());
        return d;
    }
}