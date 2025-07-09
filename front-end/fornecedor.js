

async function salvaFornecedor() {
    let fornecedor = document.getElementById("fornecedor").value;
    let dataCadastro = document.getElementById("dataCadastro").value;
    let jsonFornecedor = {"nome":fornecedor,"dataCadastro":dataCadastro};
    
    try{
    const resposta = await fetch(`http://localhost:8080/fornecedor/cadastro`,
        {method: 'POST',
        headers:{ 'Content-Type': 'application/json'},
        body: JSON.stringify(jsonFornecedor)
        }
    );
    if(resposta.ok){
        alert("Cadastro realisado com sucesso")
    }else{
        alert("erro ao cadastrar"+ resposta.status+" "+resposta.message)


    }
    }catch(erro){
     alert("erro ao envia os dados"+erro.message)
    }
}

async function listaFornecedor() {
    const {jsPDF}= window.jspdf;
    const doc= new jsPDF();

    try{
        const resposta = await fetch(`http://localhost:8080/fornecedor/listar`);
        if(!resposta.ok){
            alert("erro ao cria lista")

        }
        const listaFornecedor = await resposta.json();
        doc.setFontSize(18)
        doc.text("Lista De Fornecedor",105,15,{align:"center"})
        let y=25;
        for (const fornecedor of listaFornecedor){
            if(y >270){
                doc.addPage();
                y = 10;
            }
            doc.setFontSize(12);
            let dataFormatada = "Data nao Informada";
            if(Array.isArray(fornecedor.dataCadastro)){
                const[ano,mes,dia]=fornecedor.dataCadastro;
                dataFormatada = `${String(dia).padStart(2, '0')}/${String(mes).padStart(2, '0')}/${ano}`;
            }
            doc.text(`Nome Fornecedor: ${fornecedor.nome}| Data do cadastro  : ${dataFormatada}`,10,y)
            y+= 8 ;

        }
         doc.save("Lista de fornecedores.pdf");



    }catch(erro){
        console.error("Erro ao gerar o PDF:", erro);
    }
    
}