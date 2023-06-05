package com.eliab.sistemas.sgp.model;


import com.eliab.sistemas.sgp.model.format.Formatado;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;


@Entity
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroDoProtocolo;

    @ManyToOne
    private Requerente requerente;

    @NotNull(message = "The status is required.")
    private EnumStatus status;

    @NotNull(message = "The description is required.")
    @Size(min = 2, message = "O campo 'descrição' precisa ter no mínimo 2 caracteres.")
    private String descricao;

    private String data;


    public String getData() {
        DateTimeFormatter dataTime = DateTimeFormatter
                .ofPattern("dd-MM-yyyy")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime data = LocalDateTime.now();
        String formatado = data.format(dataTime);
        return formatado;
    }

    public String getNumeroDoProtocolo() {
        Formatado formatter = new Formatado();

        this.numeroDoProtocolo = formatter.getFormatado();
        String idFormatado = String.format("%9s", id).replace(" ", "0");

        String completo = numeroDoProtocolo + idFormatado;
        return completo;
    }

    /*

        String strContador = String.valueOf(zeros + id);

        if (nCaracteresDoId == (nCaracteresDoId + 1)) {
            strContador = String.valueOf(zeros.substring(1, numZeros) + id);
        }

        */

    //FORMATAR ID COM 000000

    /*
    public static void main(String[] args) {
        String[] ids = "1 3 76 874 9999 10987655 765432 98765432 9999999".split("\s+");
        for (String id: ids) {
            String dataFormatada = "20230601";
            String idFormatado = String.format("%8s", id).replace(" ", "0");

            String numeroProtocoloFormatado = dataFormatada + idFormatado;
            System.out.println(numeroProtocoloFormatado);
        }
    }

     */
}








