

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
    const {jsPDF}= window.jsPDF;
    const doc= new jsPDF();

    try{
        const resposta = await fetch
    }
    
}