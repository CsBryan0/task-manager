
# Task Manager

Este é um projeto de exemplo do Task Manager, uma aplicação simples para gerenciar tarefas. O projeto usa as tecnologias Java, Spring Boot, Spring Data JPA, Spring Web, H2 Database, SpringFox para documentação da API, Springdoc OpenAPI e Maven como sistema de gerenciamento de dependências.

## Requisitos

Para executar este projeto localmente, você precisará das seguintes ferramentas e tecnologias instaladas em seu sistema:

- Java Development Kit (JDK) - versão 8 ou superior
- Maven - Sistema de gerenciamento de dependências
- Um ambiente de desenvolvimento integrado (IDE) como o IntelliJ IDEA ou Eclipse (opcional, mas altamente recomendado)
- Spring Boot
- JUnit 5

## Instruções para Execução Local

Siga as etapas abaixo para executar o projeto em sua máquina:

1. Certifique-se de ter o JDK e o Maven instalados e configurados corretamente em seu sistema.

2. Clone este repositório em seu computador:

   ```bash
   git clone https://github.com/CsBryan0/tasks.git

3. Navegue até o diretório raiz do projeto:

	 ```bash
	  cd tasks
	```	
4. Execute o aplicativo usando o Maven. Isso irá baixar as dependências e iniciar o servidor incorporado do Spring Boot:

	  ```bash
	  mvn spring-boot:run
	```

Após a conclusão bem-sucedida, o aplicativo estará disponível em http://localhost:8080. Você pode acessar a documentação da API Swagger em http://localhost:8080/taskPath/swagger-ui.html.

## Endpoints Disponíveis:
- GET /tasks: Recupera todas as tarefas.
- GET /tasks/{id}: Recupera uma tarefa específica pelo ID.
- POST /tasks: Cria uma nova tarefa.
- PUT /tasks/{id}: Atualiza uma tarefa existente.
- DELETE /tasks/{id}: Remove uma tarefa existente.

Use uma ferramenta como cURL, Postman ou qualquer cliente HTTP para fazer solicitações à API do Task Manager. Consulte a documentação da API para obter informações sobre os pontos de extremidade disponíveis.

Para parar a aplicação, basta pressionar Ctrl + C no terminal em que você iniciou o aplicativo.

