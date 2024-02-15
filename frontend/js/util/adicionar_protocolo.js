document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = obterDadosFormulario();
        console.log(formData);
        enviarProtocolo(formData);
    });

    function obterDadosFormulario() {
        var requerenteNome = document.getElementById('requerente').value;
        var email = document.getElementById('email').value;
        var telefone = document.getElementById('telefone').value;
        var endereco = document.getElementById('endereco').value;
        var status = document.getElementById('status').value;
        var descricao = document.getElementById('descricao').value;
        var data = document.getElementById('data').value;

        const dadosFormulario = {
            requerente: {
                nome: requerenteNome,
                email: email,
                endereco: endereco,
                telefone: telefone
            },
            status: status,
            descricao: descricao,
            data: data
        };

        return dadosFormulario;
    }

    function enviarProtocolo(dadosFormulario) {
        console.log('Corpo da requisição:', JSON.stringify(dadosFormulario));

        fetch('http://localhost:8080/protocolo/salvar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dadosFormulario),
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro ao enviar protocolo. Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('Sucesso:', data);
                mostrarMensagemSucesso();
            })
            .catch(error => {
                console.error('Erro ao enviar protocolo:', error);
                mostrarMensagemErro();
            });
    }

    function mostrarMensagemSucesso() {
        const mensagemDiv = document.getElementById('mensagem');
        mensagemDiv.textContent = "Protocolo adicionado com sucesso!";
        mensagemDiv.style.display = 'block';
    }

    function mostrarMensagemErro() {
        const mensagemDiv = document.getElementById('mensagem');
        mensagemDiv.textContent = "Ocorreu um erro ao adicionar o protocolo.";
        mensagemDiv.style.display = 'block';
    }
});