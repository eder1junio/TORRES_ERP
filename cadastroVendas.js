
function adicionarProduto() {
    var id = document.getElementById("id").value;
    var idConvertido = Number(id)
    if (isNaN(idConvertido)) {
    var xrf = new XMLHttpRequest();    
    xrf.open('GET','http://localhost:8080/produto/procuranome?nome='+id)
    xrf.setRequestHeader('Content-Type', 'application/json')
    xrf.send();
    xrf.onload = function() {
        if (xrf.status == 200) {
            var produtoLista = JSON.parse(xrf.responseText);
            var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
            myModal.show();
            var produtosTable = document.getElementById('produtosTableModal').getElementsByTagName('tbody')[0];
            produtosTable.innerHTML = '';
            for (const produto of produtoLista){
                var novaLinha = produtosTable.insertRow();
                novaLinha.classList.add('produto');
                var cont = novaLinha.insertCell(0)
                var id = novaLinha.insertCell(1);
                var nomeProduto = novaLinha.insertCell(2);
                var valor = novaLinha.insertCell(3);
                var botao = novaLinha.insertCell(4);
                cont.innerHTML = 1
                id.innerHTML = ` ${produto.id}`;
                nomeProduto.innerHTML = ` ${produto.nome}`;
                valor.innerHTML = ` ${produto.valorVendaProduto}`;
                botao.innerHTML = `<button type="button" class="btn btn-primary" onclick="selecionar(this)"id="botao"><i class="bi bi-floppy-fill"></i> deletar</button>`;
            }
            var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.);
            total = total + preco;
            document.getElementById("totalVenda").value = total.toFixed(2)

        } else {
            alert('Erro ao buscar produto: ' + xrf.statusText);
        }
        }

    }else{
    
    var xrf = new XMLHttpRequest();
    xrf.open('GET','http://localhost:8080/produto/procurar?id='+id)
    xrf.setRequestHeader('Content-Type', 'application/json')
    xrf.send();
    xrf.onload = function() {
        if (xrf.status == 200) {
            var produto = JSON.parse(xrf.responseText);
            var produtosTable = document.getElementById('produtosTable').getElementsByTagName('tbody')[0];
            var novaLinha = produtosTable.insertRow();
            novaLinha.classList.add('produto');
            var cont = novaLinha.insertCell(0)
            var id = novaLinha.insertCell(1);
            var nomeProduto = novaLinha.insertCell(2);
            var valor = novaLinha.insertCell(3);
            var botao = novaLinha.insertCell(4);
            cont.innerHTML = 1
            id.innerHTML = ` ${produto.id}`;
            nomeProduto.innerHTML = ` ${produto.nome}`;
            valor.innerHTML = ` ${produto.valorProduto}`;
            botao.innerHTML = `<button type="button" class="btn btn-primary" onclick="deletar(this)" id="botao"><i class="bi bi-floppy-fill"></i> deletar</button>`;
           
            var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.valorProduto);
            total = total + preco;
            document.getElementById("totalVenda").value = total.toFixed(2)

        } else {
            alert('Erro ao buscar produto: ' + xrf.statusText);
        }
        }
        
    }

    }

    function deletar(botao){
        const linha =  botao.parentNode.parentNode;
    var valor = parseFloat(linha.cells[3].innerText);
    linha.parentNode.removeChild(linha);

    linha.remove();
    var totalElemento = document.getElementById("totalVenda").value;
    var total = parseFloat(totalElemento)|| 0
    total = total -valor;
    document.getElementById("totalVenda").value = total.toFixed(2)
    
}
function openModal() {
    var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
    myModal.show();
  }
function selecionar(button){
    const row = button.parentElement.parentElement;
    const clonedRow = row.cloneNode(true);
    var celselecionada = clonedRow.cells[4];
    celselecionada.innerHTML = '';
    const novobotao = document.createElement('button')
    novobotao.className = 'btn btn-primary';
    novobotao.type = 'button';
    novobotao.textContent = 'Deletar';
    novobotao.onclick = function(){
        deletar(this)
    }
    celselecionada.appendChild(novobotao);
    const targetTable = document.getElementById('produtosTable').getElementsByTagName('tbody')[0];

    targetTable.appendChild(clonedRow);

    var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(clonedRow.cells[3].textContent);
            total = total + preco;
            document.getElementById("totalVenda").value = total.toFixed(2)


}
  
function salvaVenda(){
    var nomeVenda = document.getElementById("nomeVenda").value;
    var dataVenda = document.getElementById("data").value;
    var totalVenda = document.getElementById("totalVenda").value;
    var tabelaProduto = document.getElementById("produtosTable").getElementsByTagName("tbody")[0];
    if (!tabelaProduto) {
        console.error("Tabela de produtos não encontrada.");
        return;
      }
    var cadastroVenda = {"cliente":nomeVenda, "data":dataVenda,"valorTotalDaVenda":totalVenda,"produto":[]};
    for (var i = 0 ;i< tabelaProduto.rows.length; i++){
    var linhaProduto = tabelaProduto.rows[i];
    var idProduto = linhaProduto.cells[1].textContent;
    cadastroVenda.produto.push({"id":idProduto});
    }
    var xrf = new XMLHttpRequest();
    xrf.open('POST','http://localhost:8080/vendas/cadastra');
    xrf.setRequestHeader('Content-Type', 'application/json');
    xrf.send(JSON.stringify(cadastroVenda));
     xrf.onload = function() {
    if (xrf.status == 200) {
        alert("cadastro Realizado com suseso")
    }else{
        alert("erro")

    }
    }
}
