package com.mtuci.bsu1701.is.entities.dtos.securityDtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
