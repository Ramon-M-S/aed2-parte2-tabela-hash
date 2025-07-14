package principal;

import util.ListaEncadeada;

public class TabelaHash {
    private int tamanho;
    private int elementosInseridos;
    private ListaEncadeada<Integer>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        this.tamanho = 10;
        this.elementosInseridos = 0;
        this.tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
    }

    public String inserir(int valor) {
        return inserir(valor, true); // padrão: com log
    }

    public String inserir(int valor, boolean logar) {
        int indice = funcaoHash(valor, tamanho);
        ListaEncadeada<Integer> lista = tabela[indice];
        StringBuilder sb = new StringBuilder();

        if (!lista.isEmpty() && logar) {
            sb.append("Colisão na posição ").append(indice).append(", nova posição ").append(indice).append("\n");
        }

        lista.add(valor);
        elementosInseridos++;

        if ((double) elementosInseridos / tamanho >= 0.7 && logar) {
            sb.append("Redimensionando de ").append(tamanho).append(" para ").append(tamanho * 2).append("\n");
            redimensionarTabela();
        }

        return sb.toString().trim();
    }




    public boolean remover(int valor) {
        int indice = funcaoHash(valor, tamanho);
        ListaEncadeada<Integer> lista = tabela[indice];
        return lista.remove(valor);
    }

    public int buscar(int valor) {
        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Integer>.MeuIteradorDeLista it = tabela[i].iterador();
            while (it.temProximo()) {
                if (it.proximo().equals(valor)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void imprimir() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Integer> lista = tabela[i];
            ListaEncadeada<Integer>.MeuIteradorDeLista it = lista.iterador();
            if (it.temProximo()) {
                while (it.temProximo()) {
                    sb.append(it.proximo());
                    sb.append("; ");
                }
            } else {
                sb.append("--; ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    private void redimensionarTabela() {
        int novoTamanho = tamanho * 2;

        ListaEncadeada<Integer>[] novaTabela = new ListaEncadeada[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new ListaEncadeada<>();
        }

        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Integer>.MeuIteradorDeLista it = tabela[i].iterador();
            while (it.temProximo()) {
                int valor = it.proximo();
                int novoIndice = funcaoHash(valor, novoTamanho);
                novaTabela[novoIndice].add(valor); // insere sem gerar log
            }
        }

        tabela = novaTabela;
        tamanho = novoTamanho;
    }


    private int funcaoHash(int valor, int modulo) {
        double A = 0.6180339887;
        return Math.abs((int) (modulo * ((valor * A) % 1)));
    }

    public String gerarSaidaFormatada() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Integer>.MeuIteradorDeLista it = tabela[i].iterador();
            if (it.temProximo()) {
                while (it.temProximo()) {
                    sb.append(it.proximo()).append("; ");
                }
            } else {
                sb.append("--; ");
            }
        }
        return sb.toString().trim(); // remove o espaço final
    }


}
