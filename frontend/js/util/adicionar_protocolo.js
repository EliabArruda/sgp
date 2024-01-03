document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('protocoloForm');
    const mensagemDiv = document.getElementById('mensagem');

    // Carregar UFs ao carregar a página
    carregarUFs();

    // Adiciona um ouvinte de evento para o evento de mudança (change) na UF
    document.getElementById('uf').addEventListener('change', function() {
        const ufSelecionada = this.value;

        // Verifica se a UF foi selecionada
        if (ufSelecionada) {
            carregarCidades(ufSelecionada);
        } else {
            document.getElementById('cidade').value = '';
        }
    });

    const cepInput = document.getElementById('cep');
    cepInput.addEventListener('input', function() {
        const cep = cepInput.value.replace(/\D/g, ''); // Remover caracteres não numéricos
        if (cep.length === 8) {
            carregarEnderecoPorCEP(cep);
        }
    });

    // Adiciona um ouvinte de evento para o envio do formulário
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        const formData = obterDadosFormulario();
        console.log(formData);
        enviarProtocolo(formData);
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
                console.error('Erro ao carregar UFs:', error);
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
                const selectCidade = document.getElementById('cidade');
                selectCidade.innerHTML = '';

                cidades.forEach(cidade => {
                    selectCidade.innerHTML += `<option value="${cidade.nome}">${cidade.nome}</option>`;
                });
            })
            .catch(error => {
                console.error('Erro ao carregar cidades:', error);
            });
    }

    function carregarEnderecoPorCEP(cep) {
        fetch(`https://viacep.com.br/ws/${cep}/json/`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar endereço.');
                }
                return response.json();
            })
            .then(endereco => {
                preencherCamposEndereco(endereco);
            })
            .catch(error => {
                console.error('Erro ao carregar endereço:', error);
                document.getElementById('uf').readOnly = false;
                document.getElementById('cidade').readOnly = false;
            });
    }

    function preencherCamposEndereco(endereco) {
        document.getElementById('logradouro').value = endereco.logradouro || '';
        document.getElementById('bairro').value = endereco.bairro || '';
        document.getElementById('uf').value = endereco.uf || '';
        document.getElementById('cidade').value = endereco.localidade || '';
        document.getElementById('ibge').value = endereco.ibge || '';
        document.getElementById('gia').value = endereco.gia || '';
        document.getElementById('ddd').value = endereco.ddd || '';
        document.getElementById('siafi').value = endereco.siafi || '';

    }

    function obterDadosFormulario() {
        var requerenteNome = document.getElementById('requerente').value;
        var email = document.getElementById('email').value;
        var telefone = document.getElementById('telefone').value;
        var status = document.getElementById('status').value;
        var descricao = document.getElementById('descricao').value;

        // Construir objeto de dados
        var dadosFormulario = {
            requerente: {
                nome: requerenteNome,
            },
            email: email,
            telefone: telefone,
            status: status,
            descricao: descricao,
        };

        return dadosFormulario;
    }

    function enviarProtocolo(dadosFormulario) {
        // Enviar requisição POST
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
            })
            .catch(error => {
                console.error('Erro ao enviar protocolo:', error);
            });
    }
});


$(document).ready(function() {
    // Evento de envio do formulário
    $("#protocoloForm").submit(function(event) {
        // Impede o comportamento padrão de envio do formulário
        event.preventDefault();

        // Obtém os dados do formulário
        var formData = {
            requerente: {
                nome: $("#requerente").val(),
                endereco: {
                    cep: $("#cep").val()
                        // Adicione outros campos de endereço conforme necessário
                },
                email: $("#email").val(),
                telefone: $("#telefone").val()
            },
            status: "PENDENTE",
            descricao: $("#descricao").val()
        };

        // Envia os dados para o servidor usando AJAX
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/protocolo/salvar",
            data: JSON.stringify(formData),
            dataType: "json",
            success: function(data) {
                // Manipula o sucesso da resposta do servidor
                console.log("Sucesso:", data);
                // Você pode adicionar código aqui para lidar com o sucesso, se necessário
            },
            error: function(error) {
                // Manipula erros na resposta do servidor
                console.error("Erro:", error);
                // Você pode adicionar código aqui para lidar com erros, se necessário
            }
        });
    });
});