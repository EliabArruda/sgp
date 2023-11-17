document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    const mensagemDiv = document.getElementById('mensagem');

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const id = document.getElementById('id-value').textContent;
        const status = document.getElementById('status').value;

        mudarStatus(id, status, mensagemDiv);
    });
});

function carregarDadosDoProtocolo(id) {
    console.log("Carregou!", id);
    fetch('http://localhost:8080/protocolo/' + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => response.json())
        .then(protocolo => {
            // Preencha os campos do formulário com as informações do protocolo
            document.getElementById('id-value').textContent = protocolo.id;
            document.getElementById('protocolo-value').textContent = protocolo.protocolo;
            document.getElementById('requerente-value').textContent = protocolo.requerente.nome;
            document.getElementById('endereco-value').textContent = protocolo.requerente.endereco.cep;
            document.getElementById('email-value').textContent = protocolo.requerente.email;
            document.getElementById('telefone-value').textContent = protocolo.requerente.telefone;
            document.getElementById('descricao-value').textContent = protocolo.descricao;
            document.getElementById('status').value = protocolo.status;
        })
        .catch(error => {
            // Lidar com erros (exibir mensagem de erro, etc.)
            console.log('Erro:', error);
        });
}

// Função para obter um parâmetro da URL com base no nome da variável
function getQueryVariable(variable) {
    const query = window.location.search.substring(1);
    const vars = query.split('&');
    for (let i = 0; i < vars.length; i++) {
        const pair = vars[i].split('=');
        if (decodeURIComponent(pair[0]) === variable) {
            return decodeURIComponent(pair[1]);
        }
    }
    return null;
}

// Obtém o ID do protocolo da URL usando a função getQueryVariable
function mudarStatus(id, novoStatus, mensagemDiv) {
    statusObj = { status: novoStatus };
    console.log("Vai carregar!");

    if (novoStatus === 'PENDENTE') {
        mensagemDiv.textContent = "O status não pode ser alterado para Pendente!";
        mensagemDiv.style.display = 'block';
    } else {
        fetch('http://localhost:8080/protocolo/' + id + '/mudar-status?status=' + novoStatus, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(statusObj),
            })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    throw new Error('Erro na solicitação: ' + response.status);
                }
            })
            .then(responseData => {
                console.log('Resposta do servidor:', responseData);
                mensagemDiv.textContent = "Protocolo modificado com sucesso!";
                mensagemDiv.style.display = 'flex';
            })
            .catch(error => {
                console.log('Erro:', error);
                mensagemDiv.textContent = "Ocorreu um erro ao modificar o protocolo.";
                mensagemDiv.style.display = 'block';
            });
    }
}