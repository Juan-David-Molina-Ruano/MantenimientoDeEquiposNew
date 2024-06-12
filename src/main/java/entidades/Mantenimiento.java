package entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Mantenimiento {
    private  int mantenimientoId;
    private Date fecha;
    private String descripcion;
    private double costo;
    private String tipoMantenimiento;
    private  int equipoId;
    private Equipo equipo;

    public Mantenimiento() {
    }

    public Mantenimiento(int mantenimientoId, Date fecha, String descripcion, String tipoMantenimiento, double costo, int equipoId, Equipo equipo) {
        this.mantenimientoId = mantenimientoId;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoMantenimiento = tipoMantenimiento;
        this.costo = costo;
        this.equipoId = equipoId;
        this.equipo = equipo;
    }

    public int getMantenimientoId() {
        return mantenimientoId;
    }

    public void setMantenimientoId(int mantenimientoId) {
        this.mantenimientoId = mantenimientoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
