package com.decagon.binchecking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BinResponse {
    private String bank;
    private String scheme;
    private String type;

    public BinResponse(String bank, String scheme, String type) {
        this.bank = bank;
        this.scheme = scheme;
        this.type = type;
    }
}
