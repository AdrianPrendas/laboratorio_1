package cr.ac.una.moviles.lab1.domain;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Producto {
    private int codigo;
    private String nombre;
    private float precio;
    private Character importado;
    private int tipo;

    public Producto(){}
    
    public Producto(int codigo, String nombre, float precio, Character importado, int tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
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

    public Character getImportado() {
        return importado;
    }

    public void setImportado(Character importado) {
        this.importado = importado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String type(){
        switch(this.tipo){
            case 1: return "Canasta Basica";
            case 2: return "Popular";
            case 3: return "Suntuario";
            default: return "no definido";
        }
    }
    
    public String toString(){
        return "{"+
                "codigo: "+this.codigo+
                ", nombre: "+this.nombre+
                ", precio: "+this.precio+
                ", importado: "+this.importado+
                ", tipo: "+this.type()+
                "}";
    }
    
}
