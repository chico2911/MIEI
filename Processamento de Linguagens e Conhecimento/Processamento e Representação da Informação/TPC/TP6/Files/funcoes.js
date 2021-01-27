function d(value) {
    url = "/atualizartarefa?id=" + value;
    $.getJSON( url, function() {
       console.log('hello')
                }
                )
        $('table#toDo tr#' + value).remove();
        addToTableDoneCancelled(value,'feita')
}

function c(value) {    
    url = "/cancelartarefa?id=" + value;
        $.getJSON( url, function() {
        }
        )
      $('table#toDo tr#' + value).remove();
      addToTableDoneCancelled(value,'cancelada')  
    
}



function callback(){
    location.reload(true)
}

function addToTableDoneCancelled(value,estado){
    $.ajax({
        type: 'GET',
        url: "/tarefas/" + value,
        dataType: 'json',
        success: function(data) {
            var responsavel = data.responsavel
            var data_limite = data.data_limite
            var descricao = data.descricao  
            var markup = "<tr>  <td>" + data_limite + "</td><td>"+responsavel+"</td> <td>"+descricao+"</td> <td>" +estado + "</td></tr>"
            $("table#doneCancelled").append(markup);    
         }
    });
    

}