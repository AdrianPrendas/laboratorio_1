Controller = {
	loadTable: function(a){
            var str = "";
            a.sort((function(p,q){return p.codigo-q.codigo}))
            for(var p in a){
                str += "<tr>"+
                    "<td>"+a[p].codigo+"</td>"+
                    "<td>"+a[p].nombre+"</td>"+
                    "<td>"+((a[p].importado)?"si":"no")+"</td>"+
                    "<td>"+a[p].precio+"</td>"+
                    "<td>"+a[p].tipo+"</td>"+
                    "<td>"+a[p].porcentaje+"</td>"+
                    "<td>"+a[p].impuesto+"</td>"+
                    "<td>"+a[p].precioFinal+"</td>"+
                    "</tr>";
            }
            $("#tabla").html(str);
	},
        findAll:function(){
            Proxy.findAll(function(a){
                Controller.loadTable(a)
                swal("Se han cargado los productos con exito.")
            })
        },
        findByName:function(name){
            Proxy.findByName(name,function(a){
                Controller.loadTable(a)
            })
        },
        findByType:function(){
            Proxy.findByType($("#buscarTipo").val(),function(a){
                Controller.loadTable(a)
            })
        }
};


$(document).ready(function(){
    
    Controller.findAll();
    
    $("#btn-buscar-nombre").on("click",function(){
        var name = $("#buscarNombre").val();
        if(name!="")
            Controller.findByName(name)
        else
            Controller.findAll();
    })
    $("#btn-buscar-tipo").on("click",function(){
        Controller.findByType()
    })
    $("#form").on("submit",function(event){
        event.preventDefault();
        
        var cod = $("#codigo").val();
        var name = $("#nombre").val();
        var price = $("#precio").val();
        var importado = $("#tipoAgregar").val();
        var type = $("#tipoAgregar").val();
        var p = new Producto(cod,name,importado,price,type);
        
        Proxy.save(p);
    })
    
});

