package com.eliab.sistemas.sgp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusEnumTest {

    @Test
    public void isValid() {
        assertTrue(StatusEnum.isValid("PENDENTE"));
        assertTrue(StatusEnum.isValid("INDEFERIDO"));
        assertTrue(StatusEnum.isValid("DEFERIDO"));
    }

    @Test
    public void isNotValid(){
        assertFalse(StatusEnum.isValid("DEFE RIDO"));
        assertFalse(StatusEnum.isValid(" DEFERIDO"));
        assertFalse(StatusEnum.isValid(" DEFERIDO "));
        assertFalse(StatusEnum.isValid("PENDENTE11"));
        assertFalse(StatusEnum.isValid("PENDENTE33"));
        assertFalse(StatusEnum.isValid("PENDENT3"));
        assertFalse(StatusEnum.isValid("pendente"));
        assertFalse(StatusEnum.isValid(""));
        assertFalse(StatusEnum.isValid(" "));
        assertFalse(StatusEnum.isValid("  "));
        assertFalse(StatusEnum.isValid("a"));
        assertFalse(StatusEnum.isValid(null));
        assertFalse(StatusEnum.isValid("1PENDENTE"));
    }
}
