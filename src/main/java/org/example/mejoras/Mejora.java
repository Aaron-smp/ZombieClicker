package org.example.mejoras;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Mejora implements Serializable {
    private String nombre;
    private double coste;
    private double aumento;
    private String descripcion;
    private long numeroCompras;
}
