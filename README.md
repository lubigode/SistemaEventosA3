Sistema de Eventos – Projeto A3

Curso: Programação de Soluções Computacionais
Professores: Felipe Frosi e Diego Rocha

Este é um sistema de console desenvolvido em Java, seguindo os princípios de Orientação a Objetos e utilizando SQLite para persistência de dados, conforme as exigências da A3.

🎯 Objetivo do Projeto

Implementar um Sistema de Cadastro e Notificação de Eventos da cidade, contendo:

Cadastro de usuários

Cadastro de eventos

Confirmação e cancelamento de participação

Exibição de eventos cadastrados

Identificação de eventos ocorrendo agora

Exibição de eventos já finalizados

Carregamento e gravação dos dados via banco SQLite

Organização das classes seguindo um modelo orientado a objetos

🏛 Estrutura Geral do Sistema

O projeto segue uma estrutura dividida em camadas:
/src
 ├── model
 │    ├── EntidadeBase.java
 │    ├── User.java
 │    ├── Evento.java
 │    └── Participacao.java
 ├── repository
 │    ├── UserRepository.java
 │    ├── EventoRepository.java
 │    └── ParticipacaoRepository.java
 ├── service
 │    └── SistemaEventos.java
 ├── view
 │    └── Menu.java

 Funcionalidades Implementadas
✔ Cadastro de Usuário

Nome

E-mail

Cidade

✔ Cadastro de Evento

Nome

Endereço

Categoria (delimitadas)

Data e hora (LocalDateTime)

Descrição

✔ Participação do Usuário

Confirmar presença

Cancelar presença

Listar eventos em que o usuário está inscrito

✔ Listagem de Eventos

Listar todos os eventos

Identificar eventos futuros

Mostrar eventos já ocorridos

Detectar eventos que estão ocorrendo agora

✔ Persistência em Banco

Todos os dados são armazenados em SQLite

DB criado automaticamente caso não exista

Sistema carrega os dados do banco ao iniciar

🧱 Modelo UML (Diagrama)

Arquivo incluído no repositório:

📄 diagram-uml.png

Contém as classes:

EntidadeBase

User

Evento

Participacao

UserRepository

EventoRepository

ParticipacaoRepository

SistemaEventos

Menu

Com herança, dependências e métodos principais.

▶ Como Executar o Projeto

Certifique-se de ter o Java 17+ instalado

Abra o projeto em IntelliJ ou outro IDE

Execute a classe:
Main.java

O sistema abrirá o menu interativo no terminal:
SISTEMA DE EVENTOS
1 - Cadastrar usuário
2 - Cadastrar evento
3 - Listar eventos
4 - Confirmar participação
5 - Cancelar participação
6 - Listar usuários
7 - Listar eventos do usuário
0 - Sair

Banco de Dados (SQLite)

O banco é criado automaticamente como:
eventos.db
Tabelas:

usuario

eventos

participacoes

sqlite_sequence (automática)

Ferramentas recomendadas:

DB Browser for SQLite

IntelliJ Database Plugin

🛠 Tecnologias Utilizadas

Java 25

SQLite

JDBC

IntelliJ IDEA

Paradigma Orientado a Objetos

Modelo MVC simplificado

👥 Autoria

Projeto desenvolvido por: Renan Lucena
 └── Main.java
/eventos.db
