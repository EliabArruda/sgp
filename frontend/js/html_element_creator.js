createCell = (elemento) => {
    var celula = document.createElement("td");
    celula.appendChild(elemento);
    return celula;
};

createTextCell = (texto, className) => {
    var textNode = document.createTextNode(texto);
    let cell = createCell(textNode);
    cell.setAttribute("class", className);
    return cell;
};

createBoldTextCell = (texto, className) => {
    var cell = createTextCell(texto, className);
    cell.setAttribute("style", "font-weight: bold;");
    return cell;
};

createAnchor = (address) => {
    var anchor = document.createElement("a");
    anchor.setAttribute("href", address);
    anchor.setAttribute("target", "_blank");
    anchor.setAttribute("class", "popup");
    return anchor;
};

createLinkElement = (text, address) => {
    var anchor = createAnchor(address);
    var label = document.createTextNode(text);
    anchor.appendChild(label);
    return anchor;
};

createIconElement = (text, icon, address) => {
    var anchor = createAnchor(address);
    var i = document.createElement("i");
    i.setAttribute("class", "far fa-" + icon);
    anchor.appendChild(i);
    anchor.setAttribute("alt", text);
    anchor.setAttribute("title", text);
    return anchor;
};
