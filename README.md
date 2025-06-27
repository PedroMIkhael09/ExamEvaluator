# ExamEvaluator
# Sistema de Avaliação de Alunos

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos. Trata-se de um sistema em Java que permite o gerenciamento das respostas de provas objetivas de alunos, com geração automática de resultados e relatórios.

## Descrição

A aplicação permite que o usuário (professor) cadastre disciplinas, insira alunos e suas respostas a uma prova de 10 questões do tipo verdadeiro ou falso, e em seguida, com base em um gabarito oficial, gere relatórios com a nota de cada aluno e a média da turma.

Os arquivos são organizados em pastas por disciplina e os relatórios podem ser visualizados diretamente na tela e salvos em arquivos de texto.

## Funcionalidades

- Criação de disciplinas e armazenamento das respostas dos alunos.
- Cadastro de qualquer quantidade de alunos por disciplina.
- Validação das respostas (apenas 10 caracteres contendo V ou F).
- Registro das respostas em arquivo.
- Geração do gabarito oficial da prova.
- Cálculo da nota de cada aluno.
- Penalização automática para respostas inteiramente marcadas como V ou F.
- Geração de dois relatórios:
  - Um ordenado por nome dos alunos.
  - Outro ordenado pela nota, com a média final da turma ao final.
- Exibição dos resultados no console.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- `model/`
  - `Student.java`: representa um aluno, contendo nome, respostas e nota.
  - `Subject.java`: representa uma disciplina, contendo os alunos e o gabarito.
  
- `controller/`
  - `AppController.java`: responsável pela interação com o usuário e coordenação geral da aplicação.

- `Main.java`: ponto de entrada da aplicação.

Durante a execução, uma pasta `data/` será criada, contendo subpastas com os dados de cada disciplina.
```
data/
└── Matematica/
    ├── student_answers.txt
    ├── answer_key.txt
    ├── results_by_name.txt
    └── results_by_score.txt
```

## Conceitos Utilizados

O projeto foi desenvolvido utilizando os princípios de Programação Orientada a Objetos, com foco em:

- **Encapsulamento**: cada classe controla seu próprio estado e comportamento.
- **Responsabilidade única**: as classes possuem funções bem definidas.
- **Separação de responsabilidades**: a lógica de controle (fluxo da aplicação) está separada da lógica de domínio (modelo de dados).

A classe `AppController` também pode ser vista como uma aplicação do padrão de projeto *Facade*, pois concentra e simplifica a interação com o sistema, encapsulando operações mais complexas de leitura, escrita e processamento dos dados.


## Observações

Este projeto tem fins acadêmicos e foi desenvolvido para fins de prática e avaliação dos conceitos de POO. A estrutura pode ser expandida com novas funcionalidades, como exportação em outros formatos ou interface gráfica.
