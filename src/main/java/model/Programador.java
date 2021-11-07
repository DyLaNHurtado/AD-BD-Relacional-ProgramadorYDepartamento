package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Programador {
    private long id;
    private String nombre;
    private int aniosExp;
    private double salario;
    private List<String> lenguajes;
    private String departamento;
}
