async function salvaProduto(){
    const nome = document.getElementById("nome").value;
    const descricao = document.getElementById("descricaoProdudo").value;
    const valorCompra = document.getElementById("precoCompra").value;
    const valorRevenda = document.getElementById("precoVenda").value;
    const codigoBarra = document.getElementById("codigoBarra").value.trim();
    const CodigoBarras = document.getElementById("codigoBarra").value;
    const produto = {nome:nome,
        descricao:descricao,
        precoCompra:valorCompra,
        precoVenda:valorRevenda,
        quantidadeEstoque:codigoBarra,
        codigoBarras:CodigoBarras};

        try{
            const resposta = await fetch(`${API_URL}/produto/cadastro`,{
            method: "POST",
            headers:{
              "Content-Type": "application/json"
            },
            body:JSON.stringify(produto)
        });
        if(resposta.ok){
            alert("produto salvo com sucesso")
            limpa()
        }else{
            const erro = await resposta.text();
            alert("Erro ao salvar produto: " + erro);

        }

        } catch (error)
         {
            alert("Erro de rede: " + error.message);
            console.error("Erro ao salvar produto:", erro);
        }
   
   


}function limpa(){
    document.getElementById("nomeProduto").value = " ";
    document.getElementById("descricaoProdudo").value = " ";
    document.getElementById("valorRevenda").value = "";
    document.getElementById("CodigoBarras").value = "";
   
}
async function exibirCatálogo(){
    try{
        const resposta = await fetch(`${API_URL}/produto/listar`);
        const produto = await resposta.json();
        const tabela = document.querySelector("#tabelaProdutos tbody");
        tabela.innerHTML="";

        produto.forEach(produto => {
            const linha = document.createElement("tr");
            
            linha.innerHTML =` 
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
                            <td>${produto.descricao}</td>
                             <td>R$ ${parseFloat(produto.precoVenda).toFixed(2)}</td>
                             <td>R$ ${parseFloat(produto.precoCompra).toFixed(2)}</td>
                             <td>${produto.codigoBarra}</td>` ;
            tabela.appendChild(linha);
        });
    }catch(erro){
        console.error("Erro ao buscar produtos:", erro);
        alert("Erro ao carregar produtos.")
        
    }
    
}

async function buscaProdutoID() {
    const id = document.getElementById("IDProduto").value.trim();
   
    if (id === " ") {
        alert("Digite um ID válido para buscar.");
        return;
    }

    try {
        const resposta = await fetch(`${API_URL}/produto/buscar/${id}`);

        if (resposta.ok) {
            const produto = await resposta.json();

           
            document.getElementById("nomeProduto").value = produto.nome || "";
            document.getElementById("descricaoProdudo").value = produto.descricao || "";
            document.getElementById("valor").value = produto.valorCompra || "";
            document.getElementById("valorRevenda").value = produto.valorVenda || "";
            document.getElementById("CodigoBarras").value = produto.codigoBarras || "";

        } else if (resposta.status === 404) {
            alert("Produto não encontrado!");
            limpa();
        } else {
            const erro = await resposta.text();
            alert("Erro ao buscar produto: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede: " + erro.message);
        console.error("Erro ao buscar produto:", erro);
    }
}

async function gerarPDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    try {
        const resposta = await fetch(`${API_URL}/produto/listar`);
        const produtos = await resposta.json();

        doc.setFontSize(16);
        doc.text("Lista de Produtos", 10, 10);
        
        const head =[['#', 'Nome do produto', 'valor da Revenda','valor de Compra']];
        const body = produtos.map((produto,index) =>[index +1,
            produto.nome ||'Sem Nome',
            produto.precoVenda != null ? `R$ ${Number(produto.precoVenda).toFixed(2)}`:"Sem Valor",
            produto.precoCompra != null? `R$ ${Number(produto.precoCompra).toFixed(2)}`:"Sem Valor"
        ]);
        doc.autoTable({
            startY:20,
            head:head,
            body:body,
            styles:{fontSize:12},
            headStyles: { fillColor: [22, 160, 133] }, // cor verde-azulada

        });

        doc.save("produtos.pdf");
    } catch (erro) {
        alert("Erro ao gerar PDF: " + erro.message);
    }
}

async function deletaProduto() {
    const id = document.getElementById("IDProduto").value.trim();
    const idNumber = Number(id);

if (!id || isNaN(idNumber) || idNumber <= 0) {
    alert("Por favor, insira um ID numérico válido e maior que zero.");
    return;
}
 if(!confirm(`Tem certeza que gostaria de deleta o fornecedo de id ${id}`))
        return;

    try{
       
        const resposta = await fetch(`${API_URL}/produto/deletar/${id}`,
            {method: 'DELETE',

            });
    

        if(resposta.ok){
            alert("Produto Deletado")

        }else{
            alert(resposta.text())
        }
    }catch(erro){
        alert('erro ao deletar '+ erro.message)
    }
    
}

