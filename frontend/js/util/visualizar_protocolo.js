function carregarProtocolo(id) {

    console.log("Carregou!", id)
    fetch('http://localhost:8080/protocolo/' + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },

        })
        .then(response => response.json())
        .then(atendimento => {
            // Lidar com a resposta do servidor
            console.log('Resposta do servidor:', atendimento);
            console.log(document.getElementById('id-value').textContent = atendimento.id);
            visualizarProtocolo(atendimento);

        })
        .catch(error => {
            // Lidar com erros
            console.log('Erro:', error);
        });
}

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    alert('Query Variable ' + variable + ' not found');
}

function visualizarProtocolo(atendimento) {

    console.log("vai visualizar");

    document.getElementById('protocolo-value').textContent = atendimento.protocolo;
    document.getElementById('requerente-value').textContent = atendimento.requerente.nome;
    document.getElementById('endereco-value').textContent = atendimento.requerente.endereco;
    document.getElementById('email-value').textContent = atendimento.requerente.email;
    document.getElementById('telefone-value').textContent = atendimento.requerente.telefone;
    document.getElementById('descricao-value').textContent = atendimento.descricao;
    document.getElementById('data-value').textContent = atendimento.data;
    document.getElementById('status-value').textContent = atendimento.protocolo.status;
};