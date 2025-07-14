  
TABELA HASH (TDA EM JAVA)  
==========================  

🌐 Projeto Tabela Hash - Algoritmos e Estruturas de Dados II (IFMA - Monte Castelo)

🎯 Sobre o Projeto
Este projeto representa a Parte 2 da Nota 3 da disciplina Algoritmos e Estruturas de Dados II
, do curso de Sistemas de Informação – IFMA Campus Monte Castelo.

Dando continuidade à Parte 1 (implementação de árvores balanceadas AVL e Rubro-Negra), 
esta atividade tem como foco a construção manual de uma Tabela Hash, respeitando os
princípios de Tipo de Dado Abstrato (TDA), sem o uso de bibliotecas nativas da linguagem Java.

O projeto simula as operações básicas de inserção, remoção, busca e impressão, com leitura de 
comandos a partir de um arquivo th.txt, registrando os resultados e eventos (como colisões e redimensionamentos)
em saida.txt.
------------------------------------
REQUISITOS E FUNCIONALIDADES
------------------------------------

✔️ Tamanho inicial da tabela: 10 buckets  
✔️ Colisões tratadas por listas encadeadas próprias (não Java Collections)  
✔️ Funções obrigatórias:
- Inserir (I-valor)
- Remover (R-valor)
- Buscar (B-valor): retorna a posição
- Imprimir (P): imprime os buckets da tabela
  ✔️ Redimensionamento automático da tabela (dobro)
  ✔️ Todas as mensagens também são gravadas em 'saida.txt'
  ✔️ Exibição na tela e gravação no arquivo de saída

----------------------------
FORMATO DO ARQUIVO DE ENTRADA
----------------------------

Arquivo: `th.txt`

Cada linha representa uma operação:

- I-50     → Inserir o valor 50
- R-30     → Remover o valor 30
- B-22     → Buscar o valor 22
- P        → Imprimir a tabela atual

Exemplo:
---------
I-10
I-20
I-30
P
R-20
B-10
P

---------------------------
FORMATO DO ARQUIVO DE SAÍDA
---------------------------

Arquivo: `saida.txt`

- Colisões e redimensionamentos são informados:
  Colisão na posição 5, nova posição 5

- Saída de impressão da tabela:
  --; --; --; 10; --; 15; --; --; 12; --;

- Saída com uma linha em branco entre os blocos:
  Redimensionando de 10 para 20

----------------------------
SOBRE A COLISÃO
----------------------------

Uma colisão ocorre quando dois ou mais valores diferentes geram o mesmo índice (ou "posição") na tabela hash.
Isso é esperado em tabelas hash com tamanho fixo, pois a função de dispersão (hash) mapeia um número potencialmente
infinito de chaves para um número limitado de buckets (neste projeto, começa com 10).

🔎 Exemplo de colisão:
Se a função hash de dois valores diferentes (como 25 e 75) resultar na mesma posição, isso é uma colisão.

✔️ Neste projeto, as colisões são tratadas por **encadeamento separado (endereçamento fechado)**,
ou seja, cada posição da tabela contém uma **lista encadeada própria** onde os elementos são inseridos.

🧾 Quando uma colisão acontece:
- A posição onde a colisão ocorreu é mostrada na tela e no `saida.txt`
- Exemplo:
  `Colisão na posição 5, nova posição 5`

📌 Importante:
Mesmo que o valor seja inserido no mesmo bucket, ele ainda é armazenado corretamente na lista ligada correspondente.
Essa é uma estratégia eficaz para manter o desempenho da estrutura mesmo quando múltiplas colisões ocorrem.


----------------------------
COMO EXECUTAR O PROJETO
----------------------------

1. Compile todos os arquivos `.java`:  
   Exemplo:  
   javac Main.java  
2. Execute o programa principal:  
   java Main

3. Verifique o conteúdo do `saida.txt` gerado na raiz do projeto.

--------------------------------
ESTRUTURA DO PROJETO (EXEMPLO)
--------------------------------

src/  
├── principal/  
│ ├── Main.java // Classe principal  
│ └── TabelaHash.java // Implementação da Tabela Hash  
├── util/  
│ ├── ListaEncadeada.java // Lista duplamente encadeada (TDA)  
│ └── LeitorArquivo.java // Leitura e execução de comandos do arquivo  
├── th.txt // Arquivo de entrada com comandos (I-, R-, P, B-)  
├── saida.txt // Arquivo de saída gerado automaticamente  
├── config.md // Este arquivo   

-------------------------
DESENVOLVIDO POR
-------------------------
Ramon M. Silva  
IFMA  / Sistemas de Informação
