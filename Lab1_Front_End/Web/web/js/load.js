Model ={arrayOfProducts:[]};

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
                    '<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="Controller.modify('+"'"+a[p].codigo+"'"+');">'+
                    '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
                    '</button>'+
                    '<button type="button" class="btn btn-default btn-xs" aria-label="Right Align" onclick="Controller.remove('+"'"+a[p].codigo+"'"+');" >'+
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
                    '</button></td>'+
                    "</tr>";
            }
            $("#tabla").html(str);
	},
        findAll:function(){
            Proxy.findAll(function(a){
                Controller.loadTable(a)
                Model.arrayOfProducts = a;
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
        },
        modify: function(id){
            $("#codigo").val((Model.arrayOfProducts.find(function(p){return p.codigo == id})).codigo);
            $("#nombre").val((Model.arrayOfProducts.find(function(p){return p.codigo == id})).nombre);
            $("#precio").val((Model.arrayOfProducts.find(function(p){return p.codigo == id})).precio);
            $("#importado").prop("checked",(Model.arrayOfProducts.find(function(p){return p.codigo == id})).importado);
            $("#tipoAgregar").val((Model.arrayOfProducts.find(function(p){return p.codigo == id})).tipo);
            $("#codigo").attr("disabled",true);
            console.log(Model.arrayOfProducts.find(function(p){return p.codigo == id}));
            $("#btn-agregar").val("Modificar");
        },
        remove: function(id){
            Proxy.delete(id,function(){location.reload();})
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
        
        var option = $("#btn-agregar").val();
        console.log(option);
        
        var cod = $("#codigo").val();
        var name = $("#nombre").val();
        var price = $("#precio").val();
        var importado = $("#importado").prop("checked");
        var type = $("#tipoAgregar").val();
        var p = new Producto(cod,name,importado,price,type);
        console.log(p);
        
        if(option == 'Agregar')
            Proxy.save(p,function(){location.reload();});
        else if(option=='Modificar')
            Proxy.update(p,function(){location.reload();});
            
    })
    
});

