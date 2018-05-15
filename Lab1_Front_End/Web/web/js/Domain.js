
function Tipo(){
	this.Tipo(tipo,porcentaje);
}

Tipo.prototype ={
	Tipo: function(tipo, porcentaje){
		this.tipo = tipo;
		this.porcentaje = porcentaje;
	}

};

function Producto(codigo, nombre, importado, precio, tipo, porcentaje, impuesto, precioFinal){
    this.Producto(codigo, nombre, importado, precio, tipo, porcentaje, impuesto, precioFinal);
}

Producto.prototype = {
    Producto: function (codigo, nombre, importado, precio, tipo, porcentaje, impuesto, precioFinal){
        this.codigo = codigo;
		this.nombre = nombre;
		this.importado = importado;
		this.precio = precio;
		this.tipo = tipo;
		this.porcentaje = porcentaje;
		this.impuesto = impuesto;
		this.precioFinal = precioFinal;
    },
    toString: function () {
        return JSON.stringify(this);
    }
};

/*
buena cancion
https://www.youtube.com/watch?v=jg74CfaAdV8&index=10&list=RDnQ4SmLVPwGA
*/