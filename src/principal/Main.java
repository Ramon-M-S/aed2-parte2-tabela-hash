package principal;

import util.LeitorArquivo;
public class Main {
    public static void main(String[] args) {
        TabelaHash tabela = new TabelaHash();
        LeitorArquivo leitor = new LeitorArquivo(tabela);
        leitor.processarArquivo("src/principal/th.txt", "src/principal/saida.txt");
    }
}