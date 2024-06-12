/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author juand
 */
public class Equipo {
    private int equipoId;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private Date fechaAdqui;
    private String ubicacion;

    public Equipo() {
    }

    public Equipo(int equipoId, String numeroSerie, String marca, String modelo, Date fechaAdqui, String ubicacion) {
        this.equipoId = equipoId;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaAdqui = fechaAdqui;
        this.ubicacion = ubicacion;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaAdqui() {
        return fechaAdqui;
    }

    public void setFechaAdqui(Date fechaAdqui) {
        this.fechaAdqui = fechaAdqui;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString(){
        return  modelo;
    }
}
