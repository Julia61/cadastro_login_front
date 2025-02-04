package br.com.julia.front_cadastro_login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserDTO {

    private String usuario;
    private UUID id;
}
