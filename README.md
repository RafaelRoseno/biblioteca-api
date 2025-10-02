# 📚 Biblioteca API — Spring Boot (JPA + MySQL)

API REST para gerenciamento de biblioteca acadêmica, construída com **Spring Boot**, **Spring Data JPA (Hibernate)** e **MySQL**.

---

## 🧱 Stack
- Java 17
- Spring Boot
- MySQL
- Maven
- Lombok

---

## ✅ Requisitos
- JDK 17 instalado
- MySQL Server em execução (porta padrão `3306`)
- Maven
- IntelliJ IDEA (recomendado)

---

## ⚙️ Configuração
Edite `src/main/resources/application.properties` e ajuste usuário/senha/porta do seu MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

---

## ▶️ Executando
No IntelliJ, rode a classe principal **`BibliotecaApiApplication`** 
ou via Maven:
```bash
  mvn spring-boot:run
```
Saída esperada:
```
Tomcat started on port(s): 8080
Started BibliotecaApiApplication ...
```

---

## 🔌 Endpoints (base URL)
> Este projeto está versionado com prefixo **`/rest/v1/api`**.

```
http://localhost:8080/rest/v1/api
```

### 📂 Livros
| Método | Rota                       | Descrição        |
|-------:|----------------------------|------------------|
| POST   | `/livros`                  | Criar livro      |
| GET    | `/livros`                  | Listar livros    |
| GET    | `/livros/{id}`             | Detalhar livro   |
| PUT    | `/livros/{id}`             | Atualizar livro  |
| DELETE | `/livros/{id}`             | Excluir livro    |

### 👤 Usuários
| Método | Rota                          | Descrição              |
|-------:|-------------------------------|------------------------|
| POST   | `/usuarios/alunos`            | Criar **Aluno**        |
| POST   | `/usuarios/professores`       | Criar **Professor**    |
| GET    | `/usuarios`                   | Listar usuários        |
| GET    | `/usuarios/{id}`              | Detalhar usuário       |
| DELETE | `/usuarios/{id}`              | Excluir usuário        |

### 📦 Empréstimos
| Método | Rota                                 | Descrição                   |
|-------:|--------------------------------------|-----------------------------|
| POST   | `/emprestimos`                      | Criar empréstimo            |
| GET    | `/emprestimos`                      | Listar abertos              |
| GET    | `/emprestimos?atrasados=true`       | Listar **atrasados**        |
| POST   | `/emprestimos/{id}/devolucao`       | Efetuar **devolução**       |

---

## 🧪 Testes com Postman
A coleção Postman está versionada no projeto em:
```
src/main/resources/collection/biblioteca-api-collection.json
```

### Como importar
1. Abra o **Postman** → **Import** → selecione o arquivo acima.  
2. Crie um **Environment** com a variável:  
   - `baseUrl = http://localhost:8080/rest/v1/api`  
3. Execute os requests na ordem (sugestão):
   1) Criar **Aluno** e **Professor**  
   2) Criar **Livros**  
   3) Criar **Empréstimos**  
   4) Listar **Empréstimos**
   5) Efetuar **Devolução**

---

## 📘 Exemplos (cURL)

### Criar Livro
```bash
curl -X POST "{{baseUrl}}/livros" \
 -H "Content-Type: application/json" \
 -d '{ "titulo":"Engenharia de Software", "autor":"Sommerville", "totais":3, "disponiveis":3 }'
```

### Criar Aluno
```bash
curl -X POST "{{baseUrl}}/usuarios/alunos" \
 -H "Content-Type: application/json" \
 -d '{ "nome":"João Silva", "documento":"RA2025-001" }'
```

### Criar Empréstimo
```bash
curl -X POST "{{baseUrl}}/emprestimos" \
 -H "Content-Type: application/json" \
 -d '{ "usuarioId":1, "livroId":1, "dataPrevista":"2025-10-10" }'
```

### Devolver Empréstimo
```bash
curl -X POST "{{baseUrl}}/emprestimos/1/devolucao"
```

---

## 🧩 Estrutura do projeto (resumo)
```
src/main/java/br/com/biblioteca/biblioteca_api/
 ├── BibliotecaApiApplication.java
 ├── controller/
 │    ├── LivroController.java
 │    ├── UsuarioController.java
 │    └── EmprestimoController.java
 ├── service/
 │    ├── LivroService.java
 │    ├── UsuarioService.java
 │    └── EmprestimoService.java
 ├── repository/
 │    ├── LivroRepository.java
 │    ├── UsuarioRepository.java
 │    └── EmprestimoRepository.java
 └── model/
      ├── Livro.java
      ├── Usuario.java
      ├── Aluno.java
      └── Professor.java
      └── Emprestimo.java
src/main/resources/
 ├── application.properties
 └── collection/
      └── biblioteca-api-collection.json
```

---