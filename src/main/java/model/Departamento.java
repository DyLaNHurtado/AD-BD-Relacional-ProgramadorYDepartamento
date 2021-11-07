package model;

import lombok.Data;

@Data
public class Departamento {
    private long id;
    private String nombre;
    private int presupuesto;
    private String jefe;
    private String programadorList;


    public Departamento(long id, String nombre, int presupuesto, String jefe, String programadorList) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.jefe = jefe;
        this.programadorList = programadorList;
    }
}
