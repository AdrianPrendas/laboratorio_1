package cr.ac.una.moviles.lab1.domain;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Tipo {
    private int tipo;
    private String nombre;
    private float porcentaje;

    public Tipo(int tipo, String nombre, float porcentaje) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
