# RegistrarPresenca

Sistema web para **cadastro de funcionários e registro de presença (ponto)**, desenvolvido em **Java com Jakarta EE 8**, usando **JSF (Facelets) + PrimeFaces** na camada de interface e **JPA/Hibernate** na persistência.

O projeto foi desenvolvido por [Flaviana Andrade](https://github.com/flavianaal) como parte dos estudos em desenvolvimento back-end/full-stack Java.

## Funcionalidades

- **Cadastro de Funcionários**: nome, matrícula e cargo profissional, além do endereço completo (logradouro, número, bairro, cidade, estado e CEP).
- **Pesquisa de Funcionários**: busca por nome e/ou cargo, com listagem paginada e ações de editar/excluir.
- **Folha de Ponto**: registro de presença por funcionário e data, com possibilidade de adicionar múltiplos horários de entrada/saída a uma mesma folha.
- **Registro de Entrada e Saída**: tela dedicada para lançar horário de entrada e saída de um funcionário.

Os cargos profissionais disponíveis (enum `CargosProfissionais`) são: Diretor(a), Coordenador(a), Auxiliar Administrativo, Professor(a), Auxiliar de Serviços Gerais, Cuidador(a) e Porteiro(a) — o que indica que o sistema foi pensado para uso em instituições de ensino.

## Tecnologias utilizadas

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 8 |
| Especificação | Jakarta EE 8 (`jakarta.jakartaee-api`) |
| Build | Maven (empacotado como `.war`) |
| Front-end | JSF (Facelets/XHTML) + PrimeFaces 12 |
| Persistência | JPA 2.2 + Hibernate 5.4 (ORM) |
| Banco de dados | Configurado via *datasource* JTA (`jdbc/RegistrarPresencaRP`), definido no servidor de aplicação |
| Servidor de aplicação | Payara Server 5 (Jakarta EE 8 Web Profile) — conforme configuração do projeto no NetBeans |
| Componentes REST | JAX-RS (endpoint de teste `/resources/rest`) |

## Estrutura do projeto

```
RegistrarPresenca/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── programe/io/Modelo/          # Entidades JPA: Funcionario, Endereco, FolhaDePonto, EntradaSaida
    │   ├── programe/io/Manager/         # Managed Beans JSF (@Named @ViewScoped): controlam as telas
    │   ├── programe/io/servico/         # Camada de serviço (EJBs) para cada entidade
    │   ├── programe/io/generico/        # EntidadeGenerica e ServicoGenerico<T>: CRUD genérico reutilizável
    │   ├── programe/io/enums/           # CargosProfissionais
    │   ├── programe/io/Util/            # Utilitários (datas, mensagens, caracteres, geração de tabelas)
    │   ├── aula/io/converter/           # Converters JSF (Funcionario, CargosProfissionais)
    │   └── programe/io/registrarpresencarp/  # Configuração JAX-RS e recurso REST de exemplo
    ├── resources/META-INF/persistence.xml    # Unidade de persistência JPA
    └── webapp/
        ├── index.xhtml                       # Página inicial com menu de navegação
        ├── CadastrarFuncionario.xhtml
        ├── PesquisarFuncionario.xhtml
        ├── CadastrarFolhaDePonto.xhtml
        ├── RegistrarEntradaESaida.xhtml
        └── WEB-INF/ (web.xml, beans.xml)
```

### Modelo de dados

- **Funcionario**: nome, matrícula, cargo (enum), endereço (`@OneToOne`) e lista de folhas de ponto (`@OneToMany`).
- **Endereco**: logradouro, número, bairro, cidade, estado e CEP.
- **FolhaDePonto**: data e funcionário associado, além de uma lista de registros de `EntradaSaida`.
- **EntradaSaida**: horário de entrada e horário de saída, vinculado a uma folha de ponto.

Todas as entidades herdam de `EntidadeGenerica`, que adiciona o campo `ativo` (exclusão lógica/soft delete) e é reaproveitada pela classe `ServicoGenerico<T>`, que fornece operações genéricas de `salvar`, `atualizar`, `delete`, `find` e `findAll` (filtrando apenas registros ativos).

## Como executar

Pré-requisitos:
- JDK 8
- Maven
- Um servidor de aplicação Jakarta EE 8 (o projeto foi configurado para **Payara Server 5**, mas qualquer servidor compatível deve funcionar)
- Um banco de dados relacional com um *connection pool*/*datasource* JNDI chamado `jdbc/RegistrarPresencaRP` configurado no servidor

Passos:

1. Clone o repositório:
   ```bash
   git clone https://github.com/flavianaal/RegistrarPresenca.git
   ```
2. Configure no servidor de aplicação um *datasource* JTA com o nome JNDI `jdbc/RegistrarPresencaRP`, apontando para o banco de dados desejado.
3. Gere o pacote `.war`:
   ```bash
   mvn clean package
   ```
4. Faça o deploy do arquivo `.war` gerado (em `target/`) no servidor de aplicação.
5. Acesse a aplicação pelo navegador (ex.: `http://localhost:8080/RegistrarPresencaRP-1.0-SNAPSHOT/`), que redireciona para `index.xhtml`.

> A propriedade `javax.persistence.schema-generation.database.action=create` está definida no `persistence.xml`, ou seja, o esquema do banco é criado automaticamente a partir das entidades JPA ao subir a aplicação.

## Status do projeto

Projeto de estudo/portfólio. Algumas telas (como a exclusão de funcionários na tela de pesquisa) ainda estão com a ação de botão não implementada, e a página inicial (`index.html`) ainda contém o conteúdo padrão gerado pelo Maven ("Hello World!"), indicando que o projeto está em desenvolvimento.

## Autoria

Desenvolvido por **Flaviana Andrade**.
