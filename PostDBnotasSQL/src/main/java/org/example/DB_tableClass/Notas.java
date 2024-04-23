package org.example.DB_tableClass;

public class Notas {
    private int id;
    private int estudianteId;
    private int materiaId;
    private double valorNota;

    public Notas(int id, int estudianteId, int materiaId, double valorNota) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.materiaId = materiaId;
        this.valorNota = valorNota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }
}