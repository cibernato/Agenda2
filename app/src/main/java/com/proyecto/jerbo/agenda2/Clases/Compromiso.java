package com.proyecto.jerbo.agenda2.Clases;

import java.io.Serializable;

public class Compromiso implements Serializable {
    private String fecha, hora, tipo, comentario,persona,id_proceso,recordatorioText="";
    private int id;
    private boolean recordatorio;

    public Compromiso(String fecha, String hora, String tipo, String comentario, String persona, String id_proceso, boolean recordatorio,String recordatorioText) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.comentario = comentario;
        this.persona = persona;
        this.id_proceso = id_proceso;   
        this.recordatorio = recordatorio;
        this.recordatorioText=recordatorioText;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public boolean isRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(boolean recordatorio) {
        this.recordatorio = recordatorio;
    }

    public String getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(String id_proceso) {
        this.id_proceso = id_proceso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecordatorioText() {
        return recordatorioText;
    }

    public void setRecordatorioText(String recordatorioText) {
        this.recordatorioText = recordatorioText;
    }
}
