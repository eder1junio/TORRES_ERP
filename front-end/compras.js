function autocomplet() {
    let busca = document.getElementById('fornecedorAutoComplete').value;
    if (busca.length > 0) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://54.207.192.147:8080/fornecedor/procuraFornecedor?fornecedor='+busca);
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.send();
        xhr.onload = function() {
            if (xhr.status === 200) {
                let data = JSON.parse(xhr.responseText);
                let suggestionsList = document.getElementById('suggestions-list');
                suggestionsList.innerHTML = '';
                data.forEach(function(item) {
                    let suggestionItem = document.createElement('p');
                    suggestionItem.className = 'autocomplete-suggestion';
                    suggestionItem.textContent = item.fornecedor;
                    suggestionItem.addEventListener('click', function() {
                        document.getElementById('fornecedorid').value = item.id;
                        document.getElementById('fornecedorAutoComplete').value = item.fornecedor;

                        suggestionsList.innerHTML = '';
                    });
                    suggestionsList.appendChild(suggestionItem);
                });
            }
        };
    } else {
        document.getElementById('suggestions-list').innerHTML = '';
    }
}
function autocompletTipoPagamento(){
    let buscaTipoPagamento = document.getElementById("tipoPagamento").value;
    if (buscaTipoPagamento.length> 0){
        let xhr = new XMLHttpRequest();
        xhr.open('GET','http://54.207.192.147:8080/tipoPagamento/procura?tipoPagamento='+buscaTipoPagamento)
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.send();
        xhr.onload = function(){
            let tipoPagamento = JSON.parse(xhr.responseText);
            let sugetaoListajs = document.getElementById("sugetaoLista");
            sugetaoListajs.innerHTML = "";
            tipoPagamento.forEach(function(item){
                let sugetaoItem = document.createElement("p");
                sugetaoItem.className = 'autocomplete-suggestion';
                sugetaoItem.textContent = item.tipoPagamento;
                sugetaoItem.addEventListener('click', function(){
                    document.getElementById("tipoPagamento").value = item.tipoPagamento;
                    document.getElementById("tipoPagamentoid").value = item.id;
                    sugetaoListajs.innerHTML = '';

                })
                sugetaoListajs.appendChild(sugetaoItem);
            });


            }

        }
    
}


function adicionarProduto() {
    var id = document.getElementById("idProduto").value;
    var idConvertido = Number(id)
    if (isNaN(idConvertido)) {
    var xrf = new XMLHttpRequest();    
    xrf.open('GET','http://54.207.192.147:8080/produto/procuranome?nome='+id)
    xrf.setRequestHeader('Content-Type', 'application/json')
    xrf.send();
    xrf.onload = function() {
        if (xrf.status == 200) {
            var produtoLista = JSON.parse(xrf.responseText);
            var myModal = new bootstrap.Modal(document.getElementById('produtoModal'));
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
                var valorRevenda = novaLinha.insertCell(4);
                var botao = novaLinha.insertCell(5);
                cont.innerHTML = 1
                id.innerHTML = ` ${produto.id}`;
                nomeProduto.innerHTML = ` ${produto.nome}`;
                valor.innerHTML = ` ${produto.valorProdutoCompra}`;
                valorRevenda.innerHTML = `${produto.valorVendaProduto}`;
                botao.innerHTML = `<button type="button" class="btn btn-primary" onclick="selecionar(this)"id="botao"><i class="bi bi-floppy-fill"></i>Selecionar</button>`;
            }
            var totalElemento = document.getElementById("valorTotalProduto").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.valorProdutoCompra);
            total = total + preco;
            document.getElementById("valorTotalProduto").value = total.toFixed(2)

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
            var produtosTable = document.getElementById('tabelaCompra').getElementsByTagName('tbody')[0];
            var novaLinha = produtosTable.insertRow();
            novaLinha.classList.add('produto');
            var cont = novaLinha.insertCell(0)
            var id = novaLinha.insertCell(1);
            var nomeProduto = novaLinha.insertCell(2);
            var valor = novaLinha.insertCell(3);
            var valorRevenda = novaLinha.insertCell(4);
            var botao = novaLinha.insertCell(5);
            cont.innerHTML = 1
            id.innerHTML = ` ${produto.id}`;
            nomeProduto.innerHTML = ` ${produto.nome}`;
            valor.innerHTML = ` ${produto.valorProdutoCompra}`;
            valorRevenda.innerHTML = `${produto.valorVendaProduto}`;
            botao.innerHTML = `<button type="button" class="btn btn-primary" onclick="deletar(this)" id="botao"><i class="bi bi-floppy-fill"></i> deletar</button>`;
           
            var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.valorProdutoCompra);
            total = total + preco;
            document.getElementById("valorTotalProduto").value = total.toFixed(2)

        } else {
            alert('Erro ao buscar produto: ' + xrf.statusText);
        }
        }
        
    }
}

function selecionar(button){
    const row = button.parentElement.parentElement;
    const clonedRow = row.cloneNode(true);
    var celselecionada = clonedRow.cells[5];
    celselecionada.innerHTML = '';
    const novobotao = document.createElement('button')
    novobotao.className = 'btn btn-primary';
    novobotao.type = 'button';
    novobotao.textContent = 'Deletar';
    novobotao.onclick = function(){
        deletar(this)
    }
    celselecionada.appendChild(novobotao);
    const targetTable = document.getElementById('tabelaCompra').getElementsByTagName('tbody')[0];

    targetTable.appendChild(clonedRow);

    var totalElemento = document.getElementById("valorTotalProduto").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(clonedRow.cells[3].textContent);
            total = total + preco;
            document.getElementById("valorTotalProduto").value = total.toFixed(2)


}
function deletar(botao){
    const linha =  botao.parentNode.parentNode;
var valor = parseFloat(linha.cells[3].innerText);
linha.parentNode.removeChild(linha);

linha.remove();
var totalElemento = document.getElementById("valorTotalProduto").value;
var total = parseFloat(totalElemento)|| 0
total = total -valor;
document.getElementById("valorTotalProduto").value = total.toFixed(2)

}

function salvaCompra(){
    let fornecedpor = document.getElementById("fornecedorid").value;
    let tipoPagamento = document.getElementById("tipoPagamentoid").value;
    let dataCompra = document.getElementById("dataCompra").value;
    let valorTotalProduto = document.getElementById("valorTotalProduto").value;
    let tabelaProduto = document.getElementById("tabelaCompra").getElementsByTagName("tbody")[0];
    let salvaCompra = {"fonecerdor":{"id":fornecedpor},"pagamento":{"id":tipoPagamento},"dataDaCompra":dataCompra,"valorTotlaDosProdutos":valorTotalProduto, "produto":[]}
    for(let i = 0; i < tabelaProduto.rows.length; i++){
        let idproduto = tabelaProduto.rows[i];
        let idprodutoCel = idproduto.cells[1].textContent
        salvaCompra.produto.push({"id":idprodutoCel})
    }
    let xrf = new XMLHttpRequest();
    xrf.open("POST","http://54.207.192.147:8080/compras/salvar");
    xrf.setRequestHeader('Content-Type', 'application/json');
    xrf.send(JSON.stringify(salvaCompra));
    xrf.onload = function(){
        if(xrf.status == 200){
            alert("cadastrado com sucesaso")

        }else{
            alert("erro ao cadastrar")
        }
    };




}