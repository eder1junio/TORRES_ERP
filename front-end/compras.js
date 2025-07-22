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


async function adicionarProduto() {
    const id = document.getElementById("idProduto").value.trim();
    if(id === " "){
        alert("O campo nao pode esta em branco")
        return;
    };
    try {
        const resposta = await fetch(`${API_URL}/produto/buscar/${id}`);
        if(resposta.ok){
            
        }
         const produto = await resposta.json();
          const produtosTable = document.getElementById('tabelaCompra').getElementsByTagName('tbody')[0];
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

        
    } catch (erro) {
         alert("Erro de rede: " + erro.message);
        console.error("Erro ao buscar produto:", erro);
        
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

async function salvaCompra(){
    const fornecedpor = document.getElementById("fornecedorid").value.trim();
     const tipoPagamento = document.getElementById("tipoPagamentoid").value.trim();
    const dataCompra = document.getElementById("dataCompra").value.trim();
     const valorTotalProduto = document.getElementById("valorTotalProduto").value.trim();
    const tabelaProduto = document.getElementById("tabelaCompra").getElementsByTagName("tbody")[0];
    const salvaCompra = {"fonecerdor":{"id":fornecedpor},"pagamento":{"id":tipoPagamento},"dataDaCompra":dataCompra,"valorTotlaDosProdutos":valorTotalProduto, "produto":[]}
    for(let i = 0; i < tabelaProduto.rows.length; i++){
        let idproduto = tabelaProduto.rows[i];
        let idprodutoCel = idproduto.cells[1].textContent
        salvaCompra.produto.push({"id":idprodutoCel})
    }
    try{
        const resposta = await fetch(`${API_URL}/compra/cadastro`,
            {method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(salvaCompra)             
            });
            if(resposta.ok){
                alert("Compra Salva")
            }
    }catch(erro){
         alert("erro ao acessa a api: "+erro.message)
        console.error("erro ao acessa a api:", erro);

    }
   



}