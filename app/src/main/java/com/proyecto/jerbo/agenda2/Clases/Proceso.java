package com.proyecto.jerbo.agenda2.Clases;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Proceso implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id2;

    private int id;

    private String cliente,expediente,juzgado,especialista;

    public Proceso(String cliente, String expediente, String juzgado, String especialista) {
        this.cliente = cliente;
        this.expediente = expediente;
        this.juzgado = juzgado;
        this.especialista = especialista;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    @Override
    public String toString() {
        return cliente+" "+expediente;
    }
}
