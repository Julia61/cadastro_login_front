package br.com.julia.front_cadastro_login.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Token {

    private String access_token;
    private List<String> roles;
    private Long expires_in;
    private Date expirationDate;


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }


}
