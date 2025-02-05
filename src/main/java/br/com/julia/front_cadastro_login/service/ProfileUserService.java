package br.com.julia.front_cadastro_login.service;

import br.com.julia.front_cadastro_login.dto.ProfileUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProfileUserService {
    @Value("${host.api.cadastro.login}")
    private String hostAPICadastroLogin;

    public ProfileUserDTO execute(String token) {


        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
        var url = hostAPICadastroLogin.concat("/cadastro/");


        try {
            var result = rt.exchange(url, HttpMethod.GET, request, ProfileUserDTO.class);

            return result.getBody();
        } catch (HttpClientErrorException.Unauthorized ex) {

            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
