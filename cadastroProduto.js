function salvaProduto(){
    var nome = document.getElementById("nomeProduto").value;
    var descricao = document.getElementById("descricaoProdudo").value;
    var valorCcompra = document.getElementById("valor").value;
    let valorRevenda = document.getElementById("valorRevenda").value;
    let CodigoBarras = document.getElementById("CodigoBarras").value;
    var produto = {"nome":nome,"descricao":descricao,"valorProdutoCompra":valorCcompra,"valorVendaProduto": valorRevenda,"codigoBarras":CodigoBarras}
    var xhr = new XMLHttpRequest;
    xhr.open("POST","http://54.207.192.147:8080/produto/cadastra")
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(produto));
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("salvo com sucesso")
        } else {
            alert('erro ao grava ', xhr.statusText);
        }
    
    }
    document.getElementById("nomeProduto").value = " ";
    document.getElementById("descricaoProdudo").value = " ";
    document.getElementById("valorRevenda").value = "";
    document.getElementById("CodigoBarras").value = "";
   


}function limpa(){
    document.getElementById("nomeProduto").value = " ";
    document.getElementById("descricaoProdudo").value = " ";
    document.getElementById("valorRevenda").value = "";
    document.getElementById("CodigoBarras").value = "";
   
}
