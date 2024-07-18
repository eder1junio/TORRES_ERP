function salvaTipoPagamento(){
    let tipoPagamento = document.getElementById("tipoPagamento").value;
    let jsonTipoPagamento = {"tipoPagamento":tipoPagamento}
    let  xrf = new XMLHttpRequest(); 
    xrf.open("POST","http://localhost:8080/tipoPagamento/salvar")
    xrf.setRequestHeader('Content-Type', 'application/json');
    xrf.send(JSON.stringify(jsonTipoPagamento));
    xrf.onload = function(){
        if(xrf.status == 200){
            alert("cadastro realizado com sucesso")

        }else{
            alert("erro ao cadastra",xrf.statusText)
        }

}
    document.getElementById("tipoPagamento").value = "";
}