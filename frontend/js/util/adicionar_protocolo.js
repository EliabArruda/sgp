document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    const mensagemDiv = document.getElementById('mensagem');

    // Carregar UFs ao carregar a página
    carregarUFs();

    // Adiciona um ouvinte de evento para o evento de entrada (input) na UF
    document.getElementById('uf').addEventListener('input', function() {
        const ufSelecionada = document.getElementById('uf').value;

        // Verifica se a UF foi selecionada
        if (ufSelecionada) {
            carregarCidades(ufSelecionada);
        } else {
            // Limpa o <select> de cidades se a UF não estiver selecionada
            document.getElementById('cidade').innerHTML = "";
        }
    });

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const requerente = document.getElementById('requerente').value;
        const email = document.getElementById('email').value;
        const telefone = document.getElementById('telefone').value;
        const descricao = document.getElementById('descricao').value;
        const cep = document.getElementById('cep').value;
        const logradouro = document.getElementById('logradouro').value;
        const uf = document.getElementById('uf').value;
        const bairro = document.getElementById('bairro').value;
        const cidade = document.getElementById('cidade').value;
        const complemento = document.getElementById('complemento').value;

        const enderecoObj = {
            cep: cep,
            logradouro: logradouro,
            uf: uf,
            bairro: bairro,
            cidade: cidade,
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
            status: 'PENDENTE'
        };

        enviarProtocolo(data);
    });
});

function carregarUFs() {
    fetch('https://servicodados.ibge.gov.br/api/v1/localidades/estados')
        .then(response => response.json())
        .then(ufs => {
            const ufSelect = document.getElementById('uf');
            ufSelect.innerHTML = '<option value="" selected disabled>Selecione UF</option>';
            ufs.forEach(uf => {
                ufSelect.innerHTML += `<option value="${uf.sigla}">${uf.sigla} - ${uf.nome}</option>`;
            });
        })
        .catch(error => {
            console.log('Erro ao carregar UFs:', error);
        });
}

function carregarCidades(uf) {
    const url = `https://servicodados.ibge.gov.br/api/v1/localidades/estados/${uf}/municipios`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao carregar cidades.');
            }
            return response.json();
        })
        .then(cidades => {
            // Obtém a referência ao elemento <select> no seu formulário
            const selectCidade = document.getElementById('cidade');

            // Limpa as opções existentes no <select>
            selectCidade.innerHTML = "";

            // Preenche o <select> com as opções de cidades
            cidades.forEach(cidade => {
                const option = document.createElement('option');
                option.value = cidade.nome; // Ou outro identificador único, se disponível
                option.text = cidade.nome;
                selectCidade.add(option);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}


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