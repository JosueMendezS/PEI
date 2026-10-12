package ucr.ac.cr.c5h060.pe1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ucr.ac.cr.c5h060.pe1.dto.CrearMatriculaDTO;
import ucr.ac.cr.c5h060.pe1.dto.MatriculaCursoDTO;
import ucr.ac.cr.c5h060.pe1.service.MatriculaCursoService;

@RestController
@RequestMapping("/api/v1/matriculas")
@CrossOrigin(origins = "http://localhost:4200")
public class MatriculaCursoController {

    private final MatriculaCursoService matriculaCursoService;

    public MatriculaCursoController(MatriculaCursoService service) {
        this.matriculaCursoService = service;
    }

    @GetMapping
    public ResponseEntity<List<MatriculaCursoDTO>> obtenerTodas() {
        return ResponseEntity.ok(matriculaCursoService.obtenerTodas());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MatriculaCursoDTO>> obtenerPorEstado(
            @PathVariable String estado) {
        return ResponseEntity.ok(matriculaCursoService.obtenerPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<MatriculaCursoDTO> crearMatricula(
            @Valid @RequestBody CrearMatriculaDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(matriculaCursoService.crearMatricula(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<MatriculaCursoDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(matriculaCursoService.actualizarEstado(id, nuevoEstado));
    }
}