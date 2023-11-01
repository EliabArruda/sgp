function cleanLocalStorage() {
    if (typeof(Storage) !== "undefined") {

        localStorage.setItem("requerente", "");
        localStorage.setItem("protocolo", "");
        localStorage.setItem("status", "");
        localStorage.setItem("dataInicial", "");
    } else {
        console.log("Sorry! No Web Storage support.");
    }
}

function getFromLocalStorage(name) {
    if (typeof(Storage) !== "undefined") {
        let value = localStorage.getItem(name);
        return value;
    } else {
        console.log("Sorry! No Web Storage support.");
        return "";
    }
}

function setInLocalStorage(name, value) {
    if (typeof(Storage) !== "undefined") {

        localStorage.setItem(name, value);
    } else {
        console.log("Sorry! No Web Storage support.");
    }
}

function onRequerenteKeydown() {
    var requerenteInput = document.getElementById("requerente");

}

function onRequerenteKeypress() {
    var requerenteInput = document.getElementById("requerente");
}

function onRequerenteKeyup() {
    var requerenteInput = document.getElementById("requerente");
    if (!requerenteInput.value) {
        setInLocalStorage("requerente", "")
    } else {
        document.getElementById("button-limpar-filtros").disabled = false;
    }
    search();
}

function onRequerenteChange() {
    var requerenteInput = document.getElementById("requerente");
    if (requerenteInput) {
        setInLocalStorage("requerente", requerenteInput.value.toUpperCase())
        search();
    }
}

function onProtocoloKeydown() {
    var protocoloInput = document.getElementById("protocolo");
}

function onProtocoloKeypress() {
    var protocoloInput = document.getElementById("protocolo");
}

function onProtocoloKeyup() {
    var protocoloInput = document.getElementById("protocolo");
    if (!protocoloInput.value) {
        setInLocalStorage("protocolo", "")
    } else {
        document.getElementById("button-limpar-filtros").disabled = false;
    }
    search();
}

function onProtocoloChange() {
    var protocoloInput = document.getElementById("protocolo");
    if (protocoloInput) {
        setInLocalStorage("protocolo", protocoloInput.value.toUpperCase())
        if (protocolo.value) {
            document.getElementById("button-limpar-filtros").disabled = false;
        }
        search();
    }
}

function onStatusChange() {
    var statusInput = document.getElementById("status");
    if (statusInput) {
        setInLocalStorage("status", statusInput.value.toUpperCase())
        if (statusInput.value) {
            document.getElementById("button-limpar-filtros").disabled = false;
        }
        search();
    }
}

function normalise(value) {
    return value.toUpperCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
}

function verificaFiltros(requerente, protocolo, status, filtroRequerente, filtroProtocolo, filtroStatus) {
    if (!filtroRequerente && !filtroProtocolo && !filtroStatus) {
        return true;
    }

    var requerenteMatchs = !filtroRequerente || (normalise(requerente).indexOf(normalise(filtroRequerente)) > -1);
    var protocoloMatchs = !filtroProtocolo || (normalise(protocolo).indexOf(normalise(filtroProtocolo)) > -1);
    var statusMatchs = !filtroStatus || normalise(filtroStatus) == "TODAS" || (normalise(status) == normalise(filtroStatus));

    if (requerenteMatchs && protocoloMatchs && statusMatchs) {
        return true;
    } else {
        return false;
    }
}

function search() {

    var requerenteInput = document.getElementById("requerente");
    var protocoloInput = document.getElementById("protocolo");
    var statusInput = document.getElementById("status");

    var filtroRequerente = requerenteInput.value.toUpperCase() || getFromLocalStorage("requerente");
    var filtroProtocolo = protocoloInput.value.toUpperCase() || getFromLocalStorage("protocolo");
    var filtroStatus = /*statusInput.value || */ getFromLocalStorage("status");

    requerenteInput.value = filtroRequerente;
    protocoloInput.value = filtroProtocolo;
    statusInput.value = filtroStatus;

    var table = document.getElementById("tabela-protocolos");
    var tr = table.getElementsByTagName("tr");

    let nPendentes = 0;
    let nIndeferidos = 0;
    let nDeferidos = 0;

    let dataInicial = getDataInicial();

    var i;
    for (i = 0; i < tr.length; i++) {
        var tdRequerente = tr[i].getElementsByTagName("td")[0];
        var tdProtocolo = tr[i].getElementsByTagName("td")[1];
        var tdStatus = tr[i].getElementsByTagName("td")[2];
        if (tdRequerente && tdProtocolo && tdStatus) {

            var requerente = tdRequerente.textContent || tdRequerente.innerText;
            var protocolo = tdProtocolo.textContent || tdProtocolo.innerText;
            var status = tdStatus.textContent || tdStatus.innerText;

            var tdData = tr[i].getElementsByTagName("td")[4];
            var data = tdData.textContent || tdStatus.innerText;
            let passaFiltroDeData = (createDateFromProtocoloData(data) - dataInicial) >= 0;

            if (passaFiltroDeData && verificaFiltros(requerente, protocolo, status, filtroRequerente, filtroProtocolo, filtroStatus)) {
                tr[i].style.display = "";
                if (status.toUpperCase() == "PENDENTE") {
                    nPendentes = nPendentes + 1;
                } else if (status.toUpperCase() == "INDEFERIDO") {
                    nIndeferidos = nIndeferidos + 1;
                } else if (status.toUpperCase() == "DEFERIDO") {
                    nDeferidos = nDeferidos + 1;
                }
            } else {
                tr[i].style.display = "none";
            }
        }
    }

    renderGrafico(nPendentes, nIndeferidos, nDeferidos);

    total = nPendentes + nIndeferidos + nDeferidos;
    let element = document.getElementById('total-de-atendimentos');
    element.innerHTML = total;

    if (!filtroRequerente && !filtroProtocolo && !filtroStatus && !getFromLocalStorage("dataInicial")) {
        document.getElementById("button-limpar-filtros").disabled = true;
    } else {
        document.getElementById("button-limpar-filtros").disabled = false;
    }
}

function limparFiltros() {
    cleanLocalStorage();
    var requerenteInput = document.getElementById("requerente");
    var protocoloInput = document.getElementById("protocolo");
    var statusInput = document.getElementById("status");
    requerenteInput.value = "";
    protocoloInput.value = "";
    statusInput.value = "TODAS";
    document.getElementById("button-limpar-filtros").disabled = true;
    document.getElementById("button-mostrar-todos").disabled = false;
    document.getElementById("button-mostrar-hoje").disabled = false;
    search();
}

createData = (ano, mes, dia) => new Date(ano + "-" + mes + "-" + dia);

createDateFromProtocoloData = (data) => new Date(data.split('/')[2] + "-" + data.split('/')[1] + "-" + data.split('/')[0]);

getDataAsObject = protocolo => createDateFromProtocoloData(protocolo.data);

comparadorDeProtocolos = (protocoloA, protocoloB) => {
    let dataA = getDataAsObject(protocoloA);
    let dataB = getDataAsObject(protocoloB);
    if (dataA - dataB < 0) {
        return +1;
    } else if (dataA - dataB > 0) {
        return -1;
    } else {
        return protocoloB.protocolo - protocoloA.protocolo;
    }
};

function mostrarApenasOsDeHoje() {
    var today = new Date();
    today.setMinutes(today.getMinutes() - today.getTimezoneOffset());
    today.toISOString().slice(0, 10);
    let hoje = today.toISOString().split('T')[0];
    setInLocalStorage("dataInicial", hoje);
    document.getElementById("button-mostrar-hoje").disabled = true;
    document.getElementById("button-mostrar-todos").disabled = false;
    document.getElementById("button-limpar-filtros").disabled = false;
    search();
}

function mostrarDeTodosOsDias() {
    setInLocalStorage("dataInicial", "2001-01-01");
    document.getElementById("button-mostrar-todos").disabled = true;
    document.getElementById("button-mostrar-hoje").disabled = false;
    document.getElementById("button-limpar-filtros").disabled = false;
    search();
}

function getDataInicial() {
    let dataInicialFromLocalStorage = getFromLocalStorage("dataInicial");
    if (dataInicialFromLocalStorage) {
        return new Date(dataInicialFromLocalStorage);
    } else {
        return new Date(config.dataInicial);
    }
}

preProcess = (dadosOriginais) => {
    let dataInicial = getDataInicial();
    let dados = dadosOriginais.filter(protocolo => getDataAsObject(protocolo) - (new Date(config.dataInicial)) >= 0);
    dados.sort(comparadorDeProtocolos);
    return dados;
};

createActionsCell = (protocolo, id, status) => {
    let actions = document.createElement("div");
    let span1 = document.createElement("span");
    span1.setAttribute("style", "margin-right: 5px;");
    let span2 = document.createElement("span");
    span2.setAttribute("style", "margin-right: 5px;");
    let span3 = document.createElement("span");
    span1.appendChild(createIconElement("Visualizar", "file", "html/visualizar_protocolo.html?acao=visualizar&id=" + id)); // Adicione esta linha
    span2.appendChild(createIconElement("Editar", "edit", "html/editar_protocolo.html?acao=editar&id=" + id)); // Adicione esta linha
    actions.appendChild(span1);
    actions.appendChild(span2);
    actions.appendChild(span3);
    return createCell(actions);
};

loadJSON('http://localhost:8080/protocolo/busca-todos', function(erro, atendimentosOriginais) {
    console.log('Vai carregar a API');
    if (erro !== null) {

        console.log('Algo saiu errado: ', erro);
    } else {
        console.log('carregou', atendimentosOriginais);
        console.log('Número de atendimentosOriginais', atendimentosOriginais.length);
        let atendimentos = preProcess(atendimentosOriginais);
        console.log('Número de atendimentos', atendimentos.length);
        let tabela = document.getElementById("tabela-protocolos");
        for (i = 0; i < atendimentos.length; i++) {

            let linha = document.createElement('tr');

            linha.appendChild(createBoldTextCell(atendimentos[i].requerente.nome));
            linha.appendChild(createTextCell(atendimentos[i].protocolo));
            linha.appendChild(createTextCell(atendimentos[i].status, atendimentos[i].status.toUpperCase()));
            linha.appendChild(createTextCell(atendimentos[i].requerente.telefone));
            linha.appendChild(createTextCell(atendimentos[i].data));
            linha.appendChild(createActionsCell(atendimentos[i].protocolo, atendimentos[i].id, atendimentos[i].status));

            tabela.appendChild(linha);
        }
        search();
    }
});