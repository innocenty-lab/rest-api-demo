package com.enigma.model.response;

import lombok.Getter;
import lombok.Setter;

public abstract class CommonResponse {
    @Setter @Getter
    private String code;

    @Setter @Getter
    private String status;

    @Setter @Getter
    private String message;
}
