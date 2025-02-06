# Front-end do Projeto de Cadastro e Login

## Visão Geral

Este repositório contém o código front-end para a aplicação de Cadastro e Login. O projeto utiliza HTML, Thymeleaf, Tailwind CSS para estilização e JavaScript para validação de formulários. Ele está integrado com um back-end desenvolvido em Spring Boot, que gerencia a autenticação e persistência de usuários.

### Tecnologias Utilizadas

- **HTML5**

- **Thymeleaf** (para renderização dinâmica de templates no Spring Boot)

- **Tailwind CSS** (para estilização responsiva e moderna)

- **JavaScript** (para validação de senhas no formulário de cadastro)

## Estrutura do Projeto

### 1. Página de Cadastro

- **Arquivo: registerUser.html**

- **Contém um formulário para cadastro de novos usuários.**

- **Campos: Usuário, Email, Senha e Confirmação de Senha.**

- **Validação de senhas via JavaScript antes do envio do formulário.**

- **Exibe mensagens de erro caso as senhas não coincidam.**

- **Botão para submissão do formulário que envia os dados para o endpoint /user/create.**

### 2. Página de Login

- **Arquivo: loginUser.html**

- **Contém um formulário para login de usuários cadastrados.**

- **Campos: Email e Senha.**

- **Mensagens de erro são exibidas caso as credenciais estejam incorretas.**

- **O formulário é enviado para o endpoint /user/signIn.**

###  3. Página de Bem-vindo (Protegida)

- **Arquivo: welcome.html**

- **Página acessível apenas para usuários autenticados.**

- **Exibe uma mensagem de boas-vindas ao usuário.**

- **Link para logout, encerrando a sessão e redirecionando para a página de login.**

## Integração com o Back-end

O front-end interage com o back-end utilizando Thymeleaf para renderização dinâmica de páginas. As requisições são feitas para a API utilizando Spring Boot Controllers.

- **Cadastro: Enviado para /user/create**

- **Login: Enviado para /user/signIn**

- **Página Protegida: Acessível apenas com autenticação**

O Spring Security é responsável pela autenticação, garantindo que apenas usuários autenticados tenham acesso a determinadas páginas.

## Como Rodar o Projeto

#### 1. Clone este repositório:

 ```bash
     git clone https://github.com/Julia61/cadastro_login_front.git
   ```

#### 2. Inicie o back-end (Spring Boot) para garantir a integração correta.

#### 3. Execute o front-end através de um servidor embutido do Spring Boot ou configure uma hospedagem estática.  
