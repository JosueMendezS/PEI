package ucr.ac.cr.c5h060.pe1.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CrearMatriculaDTO {

    @NotNull
    private Long estudianteId;

    @NotNull
    private Long profesorId;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombreCurso;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer creditos;

    @NotBlank
    private String periodo;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal montoArancel;

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getMontoArancel() {
        return montoArancel;
    }

    public void setMontoArancel(BigDecimal montoArancel) {
        this.montoArancel = montoArancel;
    }

}