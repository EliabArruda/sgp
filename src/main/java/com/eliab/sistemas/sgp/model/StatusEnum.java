package com.eliab.sistemas.sgp.model;

import java.util.Arrays;

public enum StatusEnum {

    PENDENTE,
    DEFERIDO,
    INDEFERIDO;

    public static boolean isValid2(String status) {
        for (StatusEnum validStatus: values()) {
            if (validStatus.toString().equals(status)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValid(String status) {
        return Arrays.stream(values())
                .map(Object::toString)
                .anyMatch(s -> s.equals(status));
    }


}
