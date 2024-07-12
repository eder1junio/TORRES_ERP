
let valor =[]
let data = []
var xrf = new XMLHttpRequest();
xrf.open("GET","http://localhost:8080/vendas/listar")
xrf.setRequestHeader('Content-Type', 'application/json');
xrf.send();
xrf.onload = function(){
    if(xrf.status == 200){
        
        let vendas = JSON.parse(xrf.responseText);
        for(const vendas1 of vendas){
            valor.push(vendas1.valorTotalDaVenda);
            data.push(vendas1.data);
            
        }const ctx = document.getElementById('graficoDia');

        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: data,
            datasets: [{
              label: '# of Votes',
              data: valor,
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });

}else{
    alert("erro ao carrega o conteudo " + xrf.status)
}

}

