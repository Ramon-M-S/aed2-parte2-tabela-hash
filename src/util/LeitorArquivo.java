package util;

import principal.TabelaHash;
import java.io.*;

public class LeitorArquivo {
    private final TabelaHash tabela;

    public LeitorArquivo(TabelaHash tabela) {
        this.tabela = tabela;
    }


    public void processarArquivo(String caminhoEntrada, String caminhoSaida) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada));
                BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoSaida))
        ) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim().toUpperCase();
                if (linha.isEmpty()) continue;

                String resultado = "";

                if (linha.startsWith("I-")) {
                    int valor = Integer.parseInt(linha.substring(2));
                    resultado = tabela.inserir(valor);

                } else if (linha.startsWith("R-")) {
                    int valor = Integer.parseInt(linha.substring(2));
                    boolean removido = tabela.remover(valor);
                    resultado = removido ? "Removido com sucesso" : "Elemento não encontrado";

                } else if (linha.startsWith("B-")) {
                    int valor = Integer.parseInt(linha.substring(2));
                    int posicao = tabela.buscar(valor);
                    resultado = (posicao != -1)
                            ? "Valor " + valor + " encontrado no bucket " + posicao
                            : "Valor " + valor + " não encontrado";

                } else if (linha.equals("P")) {
                    resultado = tabela.gerarSaidaFormatada();

                } else {
                    resultado = "Comando inválido: " + linha;
                }

                if (!resultado.isBlank()) {
                    for (String linhaSaida : resultado.strip().split("\n")) {
                        linhaSaida = linhaSaida.strip();
                        bw.write(linhaSaida);
                        bw.newLine();

                        System.out.println(linhaSaida);
                    }
                    bw.newLine();
                    System.out.println();
                }
            }

            bw.flush();
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }

}
