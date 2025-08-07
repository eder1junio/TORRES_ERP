async function salvaTipoPagamento(){
    const tipoPagamento = document.getElementById("tipoPagamento").value.trim();
    const jsonTipoPagamento = {"tipoPagamento":tipoPagamento}
    try {
        const resposta = await fetch(`${API_URL}/tipoPagamento/cadastra`,{
                method: "POST",
                headers:{"Content-Type": "application/json"},
                body:JSON.stringify(jsonTipoPagamento)


            });
         if(resposta.ok){
            alert("Salvo com sucesso")
         }


        
    } catch (error) {
         alert("Erro de rede: " + error.message);
        console.error("Erro ao salvar o tipo de pagamento:", erro);
        
    }

}
async function listaTipoPagamento() {
     const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    try {
        const resposta = await fetch(`${API_URL}/tipoPagamento/lista`)
        const tipoPagamento = await resposta.json();

        doc.setFontSize(16);
        doc.text("Lista tipo de Pagamento", 10, 10);
        const head = [['#','id','tipo de Pagamento']];
        const body = tipoPagamento.map((tipoPagamento,index)=>[index +1,
            tipoPagamento.id,
            tipoPagamento.tipoPagamento
        ]);
        doc.autoTable({
            startY:20,
            head:head,
            body:body,
            styles:{fontSize:12},
            headStyles: { fillColor: [22, 160, 133] }, // cor verde-azulada


        })

        doc.save('tipoPagamento.pdf');


    } catch (error) {
         alert("Erro ao gerar PDF: " + erro.message);
        
    }
    
}