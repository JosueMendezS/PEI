package ucr.ac.cr.c5h060.pe1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucr.ac.cr.c5h060.pe1.model.MatriculaCurso;

@Repository
public interface MatriculaCursoRepository
        extends JpaRepository<MatriculaCurso, Long> {

    List<MatriculaCurso> findAllByOrderByFechaMatriculaDesc();

    List<MatriculaCurso> findByEstadoOrderByFechaMatriculaDesc(String estado);

}