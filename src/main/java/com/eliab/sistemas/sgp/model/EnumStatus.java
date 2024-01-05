package com.eliab.sistemas.sgp.model;

public enum EnumStatus {

        PENDENTE("Pendente"),
        DEFERIDO("Deferido"),
        INDEFERIDO("Indeferido");

        private final String status;

        EnumStatus(String statusDescricao){
            this.status = statusDescricao;
        }
    }

