package br.com.julia.front_cadastro_login.controller;

import br.com.julia.front_cadastro_login.dto.CreateUserDTO;

import br.com.julia.front_cadastro_login.service.CreateUserService;
import br.com.julia.front_cadastro_login.service.LoginUserService;
import br.com.julia.front_cadastro_login.service.ProfileUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private ProfileUserService profileUserService;

    @GetMapping("/create")
    public String newUser(Model model) {
        model.addAttribute("userCreate", new CreateUserDTO());
        return "register/registerUser";
    }

    @PostMapping("/create")
    public String save(CreateUserDTO userCreate,Model model) {
        try {
            this.createUserService.execute(userCreate);
        } catch (HttpClientErrorException ex) {
            // Adiciona a mensagem de erro ao modelo
            model.addAttribute("message_error", "Não foi possível concluir o cadastro. Tente novamente.");
            // Adiciona o objeto userCreate de volta ao modelo para manter os dados preenchidos no formulário
            model.addAttribute("userCreate", userCreate);
            return "register/registerUser"; // Retorna para a página de cadastro com a mensagem de erro
        }
        model.addAttribute("userCreate", userCreate);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login/loginUser";
    }

    //colocar a parte de erro
    //fazer redole quando token expirado
    @PostMapping("/signIn")
    public String signIn(RedirectAttributes redirectAttributes,
                         HttpSession session,
                         String email,
                         String senha){

        try {

            var token = this.loginUserService.login(email, senha);


            var grants = token.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())).toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAccess_token());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext = SecurityContextHolder.getContext();

            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", token);

            return "redirect:/user/profile";

        }catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error_message", "Usuário/Senha incorretos");

            return "redirect:/user/login";

        }
    }


    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile(Model model) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            var user = this.profileUserService.execute(authentication.getDetails().toString());
            model.addAttribute("user", user);
            return "welcome/welcomeUser";
        } catch (HttpClientErrorException e) {

            return "redirect:/user/login";
        }
    }
}