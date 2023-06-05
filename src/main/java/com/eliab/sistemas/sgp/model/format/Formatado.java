package com.eliab.sistemas.sgp.model.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Formatado {

    public String getFormatado() {
        DateTimeFormatter dataTime = DateTimeFormatter
                .ofPattern("uuuuMMdd")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime data = LocalDateTime.now();
        String formatado = data.format(dataTime);
        return formatado;
    }
}
