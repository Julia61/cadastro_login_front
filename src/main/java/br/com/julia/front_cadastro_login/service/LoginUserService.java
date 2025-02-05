package br.com.julia.front_cadastro_login.service;


import br.com.julia.front_cadastro_login.dto.Token;
import br.com.julia.front_cadastro_login.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUserService {

    @Value("${host.api.cadastro.login}")
    private String hostAPICadastroLogin;

    public Token login(String email, String senha) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("senha", senha);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var url = hostAPICadastroLogin.concat("/cadastro/logar");

        var result = rt.postForObject(url,request, Token.class);

        Date expirationDate = new Date(System.currentTimeMillis() + result.getExpires_in() * 1000);
        result.setExpirationDate(expirationDate);


        return result;

    }
}
