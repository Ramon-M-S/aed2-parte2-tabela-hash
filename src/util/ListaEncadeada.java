package util;

import java.io.Serializable;

/**
 * Lista duplamente encadeada genérica otimizada para uso em Tabela Hash (endereçamento separado).
 * @param <E> Tipo dos elementos armazenados
 */
public class ListaEncadeada<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static class No<E> {
        E item;
        No<E> proximo;
        No<E> anterior;

        No(No<E> anterior, E item, No<E> proximo) {
            this.item = item;
            this.anterior = anterior;
            this.proximo = proximo;
        }
    }

    private transient No<E> primeiro;
    private transient No<E> ultimo;
    private int tamanho;
    protected transient int modCount = 0;

    // Construtor padrão
    public ListaEncadeada() {}

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean add(E e) {
        if (e == null) throw new IllegalArgumentException("Elemento não pode ser nulo.");
        adicionarUltimo(e);
        return true;
    }

    private void adicionarUltimo(E e) {
        final No<E> novo = new No<>(ultimo, e, null);
        if (ultimo == null) {
            primeiro = novo;
        } else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
        tamanho++;
        modCount++;
    }

    public boolean remove(Object o) {
        for (No<E> atual = primeiro; atual != null; atual = atual.proximo) {
            if (igual(o, atual.item)) {
                desvincular(atual);
                return true;
            }
        }
        return false;
    }

    private E desvincular(No<E> no) {
        final E elemento = no.item;
        final No<E> ant = no.anterior;
        final No<E> prox = no.proximo;

        if (ant == null) {
            primeiro = prox;
        } else {
            ant.proximo = prox;
            no.anterior = null;
        }

        if (prox == null) {
            ultimo = ant;
        } else {
            prox.anterior = ant;
            no.proximo = null;
        }

        no.item = null;
        tamanho--;
        modCount++;
        return elemento;
    }

    private boolean igual(Object a, Object b) {
        return (a == null) ? b == null : a.equals(b);
    }

    public MeuIteradorDeLista iterador() {
        return new MeuIteradorDeLista();
    }

    public class MeuIteradorDeLista {
        private No<E> proximo = primeiro;
        private No<E> ultimoRetornado = null;
        private int esperadoModCount = modCount;

        public boolean temProximo() {
            verificarModificacao();
            return proximo != null;
        }

        public E proximo() {
            verificarModificacao();
            if (proximo == null) return null;
            ultimoRetornado = proximo;
            E valor = proximo.item;
            proximo = proximo.proximo;
            return valor;
        }

        public boolean temAnterior() {
            verificarModificacao();
            return (ultimoRetornado != null && ultimoRetornado.anterior != null)
                    || (proximo != null && proximo.anterior != null);
        }

        public E anterior() {
            verificarModificacao();
            if (ultimoRetornado != null && ultimoRetornado.anterior != null) {
                proximo = ultimoRetornado;
                ultimoRetornado = ultimoRetornado.anterior;
                return ultimoRetornado.item;
            } else if (proximo != null && proximo.anterior != null) {
                ultimoRetornado = proximo.anterior;
                proximo = proximo.anterior;
                return ultimoRetornado.item;
            }
            return null;
        }

        public void remove() {
            verificarModificacao();
            if (ultimoRetornado == null) {
                throw new IllegalStateException("Nenhum elemento retornado para remoção.");
            }
            ListaEncadeada.this.desvincular(ultimoRetornado);
            ultimoRetornado = null;
            esperadoModCount = modCount;
        }

        private void verificarModificacao() {
            if (modCount != esperadoModCount) {
                throw new IllegalStateException("Modificação externa detectada durante iteração.");
            }
        }
    }

    @Override
    public String toString() {
        if (primeiro == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        No<E> atual = primeiro;
        while (atual != null) {
            sb.append(atual.item);
            if (atual.proximo != null) sb.append(", ");
            atual = atual.proximo;
        }
        sb.append("]");
        return sb.toString();
    }
}
