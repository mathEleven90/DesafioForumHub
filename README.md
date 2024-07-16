# 💻 FórumHub API

FórumHub é uma API REST desenvolvida em Spring Boot para um fórum de discussão, permitindo a criação, listagem, atualização e exclusão de tópicos.

## ⚙️ Funcionalidades

- CRUD de tópicos
- Validação de tópicos duplicados (mesmo título e mensagem)
- Verificação de ID ao detalhar um tópico

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Maven
- MySQL
- Hibernate
- Flyway
- Lombok

## 🔒 Autenticação

Para interagir com a API, é necessário autenticar-se utilizando tokens JWT (JSON Web Token):

- Acesse a rota `http://localhost:8080/login` para obter um token JWT válido.
- Inclua o token JWT no cabeçalho de autorização de todas as requisições subsequentes.
- Utilize a biblioteca JWT.io para gerenciar tokens JWT na sua aplicação.

## 📄 Documentação

A estrutura das funcionalidades da aplicação pode ser acessada no Trello - ForumHub.

## 🏗️ Funcionalidades Opcionais

- Implementação de rotas adicionais para gerenciamento de usuários e respostas: `/usuario`, `/respostas`.
- Documentação interativa da API utilizando Swagger.

## ℹ️ Sobre o Projeto

### História

Bem-vindo ao nosso mais recente desafio Challenge Back End!

Um fórum é um espaço onde todos os participantes de uma plataforma podem colocar suas perguntas sobre determinados assuntos. Aqui na Alura, os alunos e alunas utilizam o fórum para tirar suas dúvidas sobre os cursos e projetos em que estão participando. Este lugar mágico está cheio de muita aprendizagem e colaboração entre alunos, professores e moderadores.

Já sabemos para que serve o fórum e sabemos sua aparência, mas sabemos como ele funciona por trás dos panos? Isto é, onde se armazenam as informações? Como são tratados os dados para que se relacione um tópico com uma resposta, ou como se relacionam os usuários com as respostas de um tópico?

Este é o nosso desafio, chamado de FórumHub: nele, vamos replicar este processo no nível do back end e, para isso, criaremos uma API REST usando Spring.

Nossa API se concentrará especificamente nos tópicos, e deve permitir aos usuários:

- Criar um novo tópico;
- Mostrar todos os tópicos criados;
- Mostrar um tópico específico;
- Atualizar um tópico;
- Eliminar um tópico.

É o que conhecemos normalmente como CRUD (CREATE, READ, UPDATE, DELETE)* e é muito parecido com o que desenvolvemos no LiterAlura, mas agora de forma completa, agregando as operações de UPDATE e DELETE, e usando um framework que facilitará muito o nosso trabalho.


