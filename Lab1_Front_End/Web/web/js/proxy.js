var Proxy = Proxy || {};

Proxy.save = function(product){
    $.ajax({
        url: "/Web/Application",
        type: "POST",
        dataType: "json",
        data: {
            action: "saveProduct",
            product: JSON.stringify(product, JsonUtils.replacer)
        }
    }).done(function (result) { 
        alert(result.detailMessage);    
        if(result.detailMessage == "Exito")
            location.reload();
    }).fail(function (e, msg, excepn) {
        alert('**** AJAX ERROR ' + msg + ' ****');
    });
};

Proxy.findAll = function(callBack){
    $.ajax({
        url: "/Web/Application",
        type: "GET",
        dataType: "json",
        data:{
            action:"findAll"
        }
    }).done(function (result) { 
        if (result instanceof Array){
            var arr = result.map(JsonUtils.revive);
            callBack(arr);
        }
        else
            swal('Oops...',result.detailMessage,'error')
    }).fail(function (e, msg, excepn) {
        alert('**** AJAX ERROR ' + msg + ' ****');
    });
};

Proxy.findByName = function(name, callBack){
    $.ajax({
        url: "/Web/Application",
        type: "POST",
        dataType: "json",
        data:{
            action:"findByName",
            name:name
        }
    }).done(function (result) { 
        if (result instanceof Array){
            var arr = result.map(JsonUtils.revive);
            callBack(arr);
        }
        else
            swal('Oops...',result.detailMessage,'error')
    }).fail(function (e, msg, excepn) {
        alert('**** AJAX ERROR ' + msg + ' ****');
    });
};

Proxy.findByType = function(type, callBack){
    $.ajax({
        url: "/Web/Application",
        type: "POST",
        dataType: "json",
        data:{
            action:"findByType",
            type:type
        }
    }).done(function (result) { 
        if (result instanceof Array){
            var arr = result.map(JsonUtils.revive);
            callBack(arr);
        }
        else
            swal('Oops...',result.detailMessage,'error')
    }).fail(function (e, msg, excepn) {
        alert('**** AJAX ERROR ' + msg + ' ****');
    });
};