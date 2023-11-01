#!/bin/bash

echo "[" > protocolos.json

iconv -f utf8 -t ascii//TRANSLIT $1 | \
	grep -v "^$"                | \
        awk -F";" '{printf $0";"} {$1 = system("/var/www/html/sistemas/git/sgp/traduzir_codigo.sh "$1)}' | \
	awk -F";" '{ print "{\"requerente\":\""$3"\",\"protocolo\":\""$2"\",\"status\":\"Pendente\",\"telefone\":\""$6"\",\"data\":\""$5"\",\"descricao\":\"Atendente: "$7", Despachante: "$4", Código: "$1" - "$8"\"},"}' | \
	sed 's/\/22/\/2022/g' | \
	sed 's/\/21/\/2021/g' | \
	sed 's/\//\\\//g' >> protocolos.json

#echo "EOF" >> protocolos.json

cat SgpDados.json_original_bkp | sed 's/\[//' | sed 's/\]//' >> protocolos.json

echo "]" >> protocolos.json

mv SgpDados.json SgpDados.json_bkp_$(date '+%Y%m%d_%H%M%S')

iconv -f utf8 -t ascii//TRANSLIT protocolos.json > protocolos_sanitized.json

tr -d '\n' < protocolos_sanitized.json > SgpDados.json

chown www-data.www-data SgpDados.json

#sed -i 's/},EOF/}/' SgpDados.json

cat SgpDados.json

exit 0;
