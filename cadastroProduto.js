function salvaProduto(){
    var nome = document.getElementById("nomeProduto").value;
    var descricao = document.getElementById("descricaoProdudo").value;
    var valor = document.getElementById("valor").value;
    var produto = {"nome":nome,"descricao":descricao,"valorProduto":valor}
    var xhr = new XMLHttpRequest;
    xhr.open("POST","http://localhost:8080/produto/cadastra")
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(produto));
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            alert(xhr.responseText);
        } else {
            alert('There was an error!', xhr.statusText);
        }
    
    }
    document.getElementById("nomeProduto").value = " ";
    document.getElementById("descricaoProdudo").value = " ";
    document.getElementById("valor").value = " ";




}function limpa(){
    document.getElementById("nomeProduto").value = " ";
    document.getElementById("descricaoProdudo").value = " ";
    document.getElementById("valor").value = " ";

}
