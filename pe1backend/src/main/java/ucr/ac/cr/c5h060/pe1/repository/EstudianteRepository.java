package ucr.ac.cr.c5h060.pe1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.c5h060.pe1.model.Estudiante;

@Repository
public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long> {
}