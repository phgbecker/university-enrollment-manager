# University enrollment manager
 
## Objetivo

Desenvolver um módulo de matrícula de alunos, buscando comprovar o funcionamento com testes unitários, confomre proposta pela [Ideia no Ar](https://www.ideianoar.com.br/).

## Regras de negócio

- Cada aluno pode se matricular em até 2 cursos por vez;
- Ao se matricular, o aluno pode ganhar descontos conforme as regras:
    - Se tiver feito 1 curso dentro dos últimos 90 dias, terá 5% de desconto;
    - Se tiver feito mais de 1 curso no mesmo período, terá 10% de desconto.


## Linha de construção da solução

A partir das regras de negócio o desenvolvimento iniciou-se mapeando as entidades envolvidas no processo de matrícula, tais como: _Curso_, e _Aluno_. 

A responsabilidade da classe matrícula, então, foi a de criar um relacionamento entre ambas, assim como adicionar _estratégias de descontos_ e _regras para o vínculo de disciplinas_. 

Com o objetivo de permitir extensão, a implementação das _estratégias de descontos_ e _regras de vínculo_ foram abstraídas da matrícula sendo representadas por interfaces, modelo utilizado pela implementação para saber como lidar com novas instâncias do modelo concreto. 

O controle de _cursos matriculadas_ x _cursos a matricular_ foi realizado utilizando duas listas, que são consolidadas caso todas as regras de vínculo tenham sido atendidas.

O feedback dos testes demonstraram que a classe matrícula apresenta mais de uma responsabilidade, sendo de representar e gerenciar seu estado. Sendo assim, a título de melhoria, as responsabilidades deveriam ser classes distintas, permitindo uma melhor evolução do modelo.
