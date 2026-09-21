# Calculadora de Equações

Aplicação de linha de comando desenvolvida em Java para realizar cálculos matemáticos de forma simples, organizada e extensível — com operações aritméticas, equações do 2º grau e (em desenvolvimento) cálculos de geometria.

O projeto foi construído com foco em boas práticas de orientação a objetos: cada operação é uma classe independente, registrada em um catálogo central e executada por meio de uma interface comum, o que torna simples adicionar novas operações sem alterar o código já existente.

## Funcionalidades

Atualmente, o projeto contempla:

* Operações aritméticas
* Cálculos geométricos
* Equações de segundo grau

## Arquitetura

src/main/java
├── Main.java                     # Ponto de entrada da aplicação
├── ui/
│   └── ConsoleUI.java             # Interface de interação via console
├── service/
│   ├── CalculadoraService.java    # Fachada: recebe pedidos e devolve resultados
│   └── CatalogoOperacoes.java     # Registro e busca das operações disponíveis
├── operacao/
│   ├── Operacao.java               # Contrato comum a toda operação
│   ├── OperacaoBase.java           # Validações e fluxo compartilhado
│   ├── aritmetica/
│   │   ├── Soma.java
│   │   └── Divisao.java
│   └── segundoGrau/
│       └── EquacaoSegundoGrau.java
├── model/
│   ├── Categoria.java              # Enum das categorias de operação
│   ├── Entrada.java                # Parâmetros informados pelo usuário
│   ├── Parametro.java              # Metadados de cada parâmetro de uma operação
│   └── Resultado.java              # Resultado final, com passos e valores
└── exception/
    ├── OperacaoNaoEncontradaException.java
    └── ParametroInvalidoException.java

## Objetivo

Projeto desenvolvido com o objetivo de praticar **lógica de programação, matemática e desenvolvimento em Java**, aplicando conceitos de organização e estruturação de código.

