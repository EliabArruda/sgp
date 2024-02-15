document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    const mensagemDiv = document.getElementById('mensagem');

    const protocoloId = getQueryVariable('id');
    carregarDadosDoProtocolo(protocoloId);
});

function carregarDadosDoProtocolo(id) {
    console.log("Carregando dados do protocolo:", id);
    fetch('http://localhost:8080/protocolo/' + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar dados do protocolo. Status: ' + response.status);
            }
            return response.json();
        })
        .then(protocolo => {
            document.getElementById('id-value').textContent = protocolo.id;
            document.getElementById('protocolo-value').textContent = protocolo.protocolo;
            document.getElementById('requerente-value').textContent = protocolo.requerente.nome;
            document.getElementById('endereco-value').textContent = protocolo.requerente.endereco;
            document.getElementById('email-value').textContent = protocolo.requerente.email;
            document.getElementById('telefone-value').textContent = protocolo.requerente.telefone;
            document.getElementById('descricao-value').textContent = protocolo.descricao;
            document.getElementById('data-value').textContent = protocolo.data;
            document.getElementById('status').value = protocolo.status;
        })
        .catch(error => {
            console.error('Erro ao carregar dados do protocolo:', error);
            mensagemDiv.textContent = "Ocorreu um erro ao carregar dados do protocolo.";
            mensagemDiv.style.display = 'block';
        });
}

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