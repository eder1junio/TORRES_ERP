function salvaFornecedor(){
    let fornecedor = document.getElementById("fornecedor").value;
    let dataCadastro = document.getElementById("dataCadastro").value;
    let jsonFornecedor = {"fornecedor":fornecedor,"dataCadastro":dataCadastro};
    let xrf = new XMLHttpRequest();
    xrf.open("POST","http://54.207.192.147:8080/fornecedor/salva");
    xrf.setRequestHeader('Content-Type', 'application/json');
    xrf.send(JSON.stringify(jsonFornecedor))
    xrf.onload = function(){
        if(xrf.status == 200){
            alert("cadastro Realizado com sucesso")

        }else{
            alert("Erro ao realizar o cadastro", xrf.statusText)
        }
    }

}