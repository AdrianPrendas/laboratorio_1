package cr.ac.una.moviles.lab1.domain;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Producto {
    private int codigo;
    private String nombre;
    private float precio;
    private boolean importado;
    private String tipo;
    private float porcentaje;
    private float impuesto;
    private float precioFinal;
    

    public Producto(){}
    
    public Producto(int codigo, String nombre, float precio, boolean importado, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
    }

    public Producto(int codigo, String nombre, float precio, boolean importado, String tipo, float porcentaje, float impuesto, float precioFinal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.impuesto = impuesto;
        this.precioFinal = precioFinal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean getImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }
    
    public String toString(){
        return "{"+
                "codigo: "+this.codigo+
                ", nombre: "+this.nombre+
                ", precio: "+this.precio+
                ", importado: "+((this.importado)?"s":"n")+
                ", tipo: "+this.tipo+
                ", porcentaje: "+this.porcentaje+
                ", impuesto: "+this.impuesto+
                ", precio final: "+this.precioFinal+
                "}";
    }
    
}
