var JsonUtils = JsonUtils || {};

JsonUtils.revive = function (k, v) { 
    if (k instanceof Object)v=k;

    if (v instanceof Object && v._class == 'Producto')
        return new Producto(v.codigo, v.nombre, v.importado, v.precio, v.tipo, v.porcentaje, v.impuesto, v.precioFinal);
    
    if (v instanceof Object && v._class == 'Tipo')
        return new Tipo(v.tipo,v.porcentaje);
    
    return v;
};

JsonUtils.replacer = function (k, v) {
    if (v instanceof Producto)
        v._class = "Producto";
    if(v instanceof Tipo)
    	v._class = "Tipo";
    
    return v;
};
