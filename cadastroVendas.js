function adicionarProduto() {
    var id = document.getElementById("id").value;
    var xrf = new XMLHttpRequest();
    xrf.open('GET','http://localhost:8080/produto/procurar?id='+id)
    xrf.setRequestHeader('Content-Type', 'application/json')
    xrf.send();
    xrf.onload = function() {
        if (xrf.status == 200) {
            var produto = JSON.parse(xrf.responseText);
            var produtosTable = document.getElementById('produtosTable').getElementsByTagName('tbody')[0];
            var novaLinha = produtosTable.insertRow();
            novaLinha.classList.add('produto');
            var id = novaLinha.insertCell(0);
            var nomeProduto = novaLinha.insertCell(1);
            var valor = novaLinha.insertCell(2);
            var botao = novaLinha.insertCell(3);
            id.innerHTML = ` ${produto.id}`;
            nomeProduto.innerHTML = ` ${produto.nome}`;
            valor.innerHTML = ` ${produto.preco}`;
            botao.innerHTML = `<button type="button" onclick="removerProduto(this)">Remover</button>`;
           
            var totalElemento = document.getElementById("total").value;
            var total = parseFloat(totalElemento)|| 0
            var preco = parseFloat(produto.preco);
            total = total + preco;
            document.getElementById("total").value = total.toFixed(2)

        } else {
            alert('Erro ao buscar produto: ' + xrf.statusText);
        }
       
        
    }

    }
