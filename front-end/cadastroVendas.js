
function adicionarProduto() {
    var id = document.getElementById("id").value;
    var idConvertido = Number(id)
    if (isNaN(idConvertido)) {
    var xrf = new XMLHttpRequest();    
    xrf.open('GET','http://54.207.192.147:8080/produto/procuranome?nome='+id)
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

            
        } else {
            alert('Erro ao buscar produto: ' + xrf.statusText);
        }
        }

    }else{
    
    var xrf = new XMLHttpRequest();
    xrf.open('GET','http://54.207.192.147:8080/produto/procurar?id='+id)
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
  
async function salvaVenda(){
    var nomeVenda = document.getElementById("nomeVenda").value.trim();
    var dataVenda = document.getElementById("data").value;
    var totalVenda = parseFloat(document.getElementById("totalVenda").value) || 0;
    var tabelaProduto = document.getElementById("produtosTable").getElementsByTagName("tbody")[0];
    if (!tabelaProduto) {
        console.error("Tabela de produtos não encontrada.");
        return;
      }
    var cadastroVenda = { "dataVenda":dataVenda,"valorVenda":totalVenda,"produto":[]};
    for (var i = 0 ;i< tabelaProduto.rows.length; i++){
    const linhaProduto = tabelaProduto.rows[i];
    const idProduto = linhaProduto.cells[1].textContent;
    const valorVenda = parseFloat(linhaProduto.cells[3].querySelector("input").value) || 0;
    const quantidadeProduto =  parseInt(linhaProduto.cells[4].querySelector("input").value) || 11;

    cadastroVenda.produto.push({"produtoID":idProduto,"quantidade":quantidadeProduto,"precoUnitario":valorVenda});
    console.log(JSON.stringify(cadastroVenda, null, 2));
    }
    try{
        const resposta = await fetch(`http://localhost:8080/venda/cadastra`,
            {method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cadastroVenda,)
        }); 
        if(resposta.ok){
            alert("Cadastrado com sucesso");
        }
        
    }catch(erro){{
        alert("erro ao acessa a api: "+erro.message)
        console.error("erro ao acessa a api:", erro);
    }

    }
}




async function procuraProduto() {
    const campoid = document.getElementById("id").value.trim();
    if (campoid ===" "){
        alert("Campo id Vazio")
        return;
    }

    try{
        const resposta = await fetch(`http://localhost:8080/produto/buscar/${campoid}`);
        if(!resposta.ok){
            alert(`Produto com ID ${campoid} não encontrado.`);
        }
            const produto = await resposta.json();
            const produtosTable = document.getElementById('produtosTable').getElementsByTagName('tbody')[0];
            const novaLinha = produtosTable.insertRow();
            novaLinha.classList.add('produto');
            novaLinha.insertCell(0).innerText = produtosTable.rows.length;
            novaLinha.insertCell(1).innerText = produto.id;
            novaLinha.insertCell(2).innerText = produto.nome;
            novaLinha.insertCell(3).innerHTML = `R$<input  type="number" step="0.00">`
            novaLinha.insertCell(4).innerHTML = `<input  type="number" step="0">`

        const celulaBotao = novaLinha.insertCell(5);
        celulaBotao.innerHTML = `<button type="button" class="btn btn-danger" onclick="deletar(this)">
                                    <i class="bi bi-trash"></i> Deletar
                                 </button>`
           
            var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.valorProduto);
            total = total + preco;
            document.getElementById("totalVenda").value = total.toFixed(2)

        
    }catch (erro) {
        alert("Erro de rede: " + erro.message);
        console.error("Erro ao buscar produto:", erro);
    }
    
}
