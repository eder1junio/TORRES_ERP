function autocomplet() {
    let busca = document.getElementById('fornecedorAutoComplete').value;
    if (busca.length > 0) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/fornecedor/procuraFornecedor?fornecedor='+busca);
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
        xhr.open('GET','http://localhost:8080/tipoPagamento/procura?tipoPagamento='+buscaTipoPagamento)
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
    xrf.open('GET','http://localhost:8080/produto/procuranome?nome='+id)
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
                var botao = novaLinha.insertCell(4);
                cont.innerHTML = 1
                id.innerHTML = ` ${produto.id}`;
                nomeProduto.innerHTML = ` ${produto.nome}`;
                valor.innerHTML = ` ${produto.valorProduto}`;
                botao.innerHTML = `<button type="button" class="btn btn-primary" onclick="selecionar(this)"id="botao"><i class="bi bi-floppy-fill"></i> deletar</button>`;
            }
            var totalElemento = document.getElementById("totalVenda").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.valorProduto);
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
            var produtosTable = document.getElementById('tabelaCompra').getElementsByTagName('tbody')[0];
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
