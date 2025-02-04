package br.com.julia.front_cadastro_login.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDTO {

    private UUID id;

    private String usuario;

    private String email;

    private String senha;

}
