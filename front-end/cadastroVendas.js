

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

async function salvaVenda(){
    const nomeVenda = document.getElementById("nomeVenda").value.trim();
    const dataVenda = document.getElementById("data").value;
    const totalVenda = parseFloat(document.getElementById("totalVenda").value) || 0;
    const tabelaProduto = document.getElementById("produtosTable").getElementsByTagName("tbody")[0];
    if (!tabelaProduto) {
        console.error("Tabela de produtos não encontrada.");
        return;
      }
    var cadastroVenda = { "nome":nomeVenda, "dataVenda":dataVenda,"valorVenda":totalVenda,"produto":[]};
    for (var i = 0 ;i< tabelaProduto.rows.length; i++){
    const linhaProduto = tabelaProduto.rows[i];
    const idProduto = linhaProduto.cells[1].textContent;
    const valorVenda = parseFloat(linhaProduto.cells[3].querySelector("input").value) || 0;
    const quantidadeProduto =  parseInt(linhaProduto.cells[4].querySelector("input").value) || 11;

    cadastroVenda.produto.push({"produtoID":idProduto,"quantidade":quantidadeProduto,"precoUnitario":valorVenda});
    }
    try{
        const resposta = await fetch(`http://35.233.132.93:8080/venda/cadastra`,
            {method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cadastroVenda,)
        }); 
        if(resposta.ok){
            alert("Cadastrado com sucesso");
        }
        
    }catch(erro){
        alert("erro ao acessa a api: "+erro.message)
        console.error("erro ao acessa a api:", erro);
    }

    
    
}




async function procuraProduto() {
    const campoid = document.getElementById("id").value.trim();
    if (campoid ===" "){
        alert("Campo id Vazio")
        return;
    }

    try{
        const resposta = await fetch(`http://35.233.132.93:8080/produto/buscar/${campoid}`);
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

async function gerarPDF(){
    const {jsPDF} = windowindow.jspdf;
    const doc = new jsPDF();

    try{
        const resposta = await fetch(`http://35.233.132.93:8080/venda/listar`);
        const vendas = await resposta.json
        doc.setFontSize(16);
        doc.text("Lista de Vendas ")
        let y = 20;
        
        for( const venda of vendas){
            doc.setFontSize(12);
            doc.text(`Venda ID:${venda.id}`)
            y+=6;
           
                const dadosTabela = produto.map(prod =>([
                    prod.nome,
                    prod.quantidade,
                    `R$ ${prod.precoVenda.toFixed(2)}`
                ]));
                doc.autoTable({
                    startY: y,
                    heard:[['Porduto', 'Quantidade', 'Preço de Venda']],
                    body:dadosTabela,
                    styles:{ fontSize: 10},
                    margin:{left:10, right:10},
                    theme:'grid'
                });

                if(y > 270){
                    doc.addPag();
                    y = 10;
                }
            }
        
        doc.save("Relatorio_DE_VENDAS.pdf");

        
    }catch(erro){
        console.error("Erro ao gerar o PDF:", erro);

    }
}
