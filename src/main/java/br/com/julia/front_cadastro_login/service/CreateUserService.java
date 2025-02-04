package br.com.julia.front_cadastro_login.service;

import br.com.julia.front_cadastro_login.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserService {

    @Value("${host.api.cadastro.login}")
    private String hostAPICadastroLogin;

    public void execute(CreateUserDTO createUserDTO){

        try{
            RestTemplate rt = new RestTemplate();

            HttpEntity<CreateUserDTO> request = new HttpEntity<>(createUserDTO);

            var url = hostAPICadastroLogin.concat("/cadastro/usuario");

            var result = rt.postForObject(url, request, String.class);
            System.out.println(result);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.CONFLICT) { // 409 Conflict (e-mail já existe)
                throw new HttpClientErrorException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
            } else {
                throw ex; // Lança outras exceções
            }
        }



    }

}
