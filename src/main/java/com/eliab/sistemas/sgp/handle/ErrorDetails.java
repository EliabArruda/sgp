package com.eliab.sistemas.sgp.handle;

import java.time.LocalDateTime;

    public record ErrorDetails(LocalDateTime timestamp,
                                    String message,
                                    String details) {
    }
