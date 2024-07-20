document.addEventListener('DOMContentLoaded', function() {
  graficoVendas();
  graficoPordutoTop();
  graficoCompras();
});



function graficoVendas(){
let valor =[]
let data = []
var xrf = new XMLHttpRequest();
xrf.open("GET","http://localhost:8080/vendas/totalVendidoPordia")
xrf.setRequestHeader('Content-Type', 'application/json');
xrf.send();
xrf.onload = function(){
    if(xrf.status == 200){
        
        let vendas = JSON.parse(xrf.responseText);
        for(const vendas1 of vendas){
            valor.push(vendas1.totalValorProduto);
            let date = new Date(vendas1.data);
            let formattedDate = date.toLocaleDateString('pt-BR', {
              day: '2-digit',
              month: '2-digit',
              year: 'numeric'
          });
            data.push(formattedDate);
            
        }const ctx = document.getElementById('graficoDia');

        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: data,
            datasets: [{
              label: '# Vendas Por dia',
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
}
function graficoPordutoTop(){
let nomeProduto =[]
let totalVendido = []
var xrf = new XMLHttpRequest();
xrf.open("GET","http://localhost:8080/produto/produtoTop")
xrf.setRequestHeader('Content-Type', 'application/json');
xrf.send();
xrf.onload = function(){
    if(xrf.status == 200){
        
        let produtoTop = JSON.parse(xrf.responseText);
        for(const produtoTop1 of produtoTop){
            nomeProduto.push(produtoTop1.nome);
            totalVendido.push(produtoTop1.total)
            
            
        }const ctx = document.getElementById('graficoProdutoTop');

        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: nomeProduto,
            datasets: [{
              label: '# Produto mais vendido',
              data: totalVendido,
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
}

function graficoCompras(){
  let nomeFornecedor =[]
  let totalCompra = []
  var xrf = new XMLHttpRequest();
  xrf.open("GET","http://localhost:8080/compras/obterTotal")
  xrf.setRequestHeader('Content-Type', 'application/json');
  xrf.send();
  xrf.onload = function(){
      if(xrf.status == 200){
          
          let fornecedorTotalCompra = JSON.parse(xrf.responseText);
          for(const fornecedor of fornecedorTotalCompra){
              nomeFornecedor.push(fornecedor.fornecedor);
              totalCompra.push(fornecedor.total)
              
              
          }const ctx = document.getElementById('graficoCompras');
  
          new Chart(ctx, {
            type: 'bar',
            data: {
              labels: nomeFornecedor,
              datasets: [{
                label: '# Compras totais por Fonecedor ',
                data: totalCompra,
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
  }


