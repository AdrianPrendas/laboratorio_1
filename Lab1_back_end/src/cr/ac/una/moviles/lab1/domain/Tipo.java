package cr.ac.una.moviles.lab1.domain;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Tipo {
    private String nombre;
    private float porcentaje;

    public Tipo(String nombre, float porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

}
