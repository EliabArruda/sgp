package com.eliab.sistemas.sgp.model;

import java.util.Optional;

public enum EnumStatus {

        PENDENTE("Pendente"),
        DEFERIDO("Deferido"),
        INDEFERIDO("Indeferido");

        private String status;

        EnumStatus(String statusDescricao){
            this.status = statusDescricao;
        }

    public EnumStatus deferir(Long id, EnumStatus status){
        return EnumStatus.DEFERIDO;
    }
    public EnumStatus indeferir(Long id, EnumStatus status){
        return EnumStatus.INDEFERIDO;
    }
    }

