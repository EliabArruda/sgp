document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();


        const requerente = document.getElementById('requerente').value;
        const email = document.getElementById('email').value;
        const endereco = document.getElementById('endereco').value;
        const telefone = document.getElementById('telefone').value;
        const descricao = document.getElementById('descricao').value;
        const cep = document.getElementById('cep').value;
        const logradouro = document.getElementById('logradouro').value;
        const uf = document.getElementById('uf').value;
        const bairro = document.getElementById('bairro').value;
        const localidade = document.getElementById('localidade').value;
        const complemento = document.getElementById('complemento').value;


        const enderecoObj = {
            cep: cep,
            logradouro: logradouro,
            uf: uf,
            bairro: bairro,
            localidade: localidade,
            complemento: complemento
        }



        // Criar o objeto requerente com os atributos
        const requerenteObj = {
            nome: requerente,
            endereco: enderecoObj,
            email: email,
            telefone: telefone.replace(/\D/g, '')
        };

        const data = {
            requerente: requerenteObj,
            descricao: descricao,
            status: 'PENDENTE' // Você pode definir o status aqui ou como padrão no HTML
        };

        enviarProtocolo(data);

    });
});

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    const mensagemDiv = document.getElementById('mensagem');

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // Aqui, você pode adicionar lógica para enviar o protocolo para o servidor.
        // Se a submissão for bem-sucedida, você exibe a mensagem.
        // Por enquanto, vamos simular uma submissão bem-sucedida:

        setTimeout(function() {
            // Simule uma submissão bem-sucedida (você pode remover isso na implementação real)
            // Suponha que 'respostaDoServidor' contenha a resposta do servidor

            const respostaDoServidor = { sucesso: true };

            if (respostaDoServidor.sucesso) {
                // Exiba a mensagem de sucesso
                mensagemDiv.textContent = 'Mensagem de sucesso: Protocolo adicionado com êxito!';
                mensagemDiv.style.display = 'flex';

                // Limpe o formulário ou tome outras ações, se necessário
                form.reset();
            } else {
                // Trate erros ou exiba uma mensagem de erro, se aplicável
                mensagemDiv.textContent = 'Ocorreu um erro ao adicionar o protocolo.';
                mensagemDiv.style.display = 'block';
            }
        }, 1000); // Simulação de uma resposta do servidor após 1 segundo
    });
});

function enviarProtocolo(data) {
    fetch('http://localhost:8080/protocolo/salvar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => response.json())
        .then(data => {
            // Lidar com a resposta do servidor (pode mostrar uma mensagem de sucesso, etc.)
            console.log('Resposta do servidor:', data);


        })
        .catch(error => {
            // Lidar com erros (exibir mensagem de erro, etc.)
            console.log('Erro:', error);


        });
}