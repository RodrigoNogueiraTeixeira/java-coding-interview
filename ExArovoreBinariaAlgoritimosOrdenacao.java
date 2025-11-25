import java.util.Scanner;

public class ExArovoreBinariaAlgoritimosOrdenacao {

    // Classe simples para criar no da arvore
    static class No {
        int valor;
        No esquerda;
        No direita;

        public No(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    //  arvore binaria de Pesquisa 
    static class ArvoreBST {
        No raiz;

        public ArvoreBST() {
            raiz = null;
        }

        // Método que chama a inserção recursiva
        public void inserir(int valor) {
            raiz = inserirNo(raiz, valor);
        }

        // Aqui acontece a mágica da recursão para inserir
        // Se o valor for menor, vai pra esquerda. Se for maior, vai pra direita.
        private No inserirNo(No atual, int valor) {
            if (atual == null) {
                return new No(valor); // Achei um lugar vazio, crio o nó aqui
            }

            if (valor < atual.valor) {
                atual.esquerda = inserirNo(atual.esquerda, valor);
            } else if (valor > atual.valor) {
                atual.direita = inserirNo(atual.direita, valor);
            }
            // Se for igual, não faz nada (não aceita duplicados)
            return atual;
        }

        // Método para buscar um número
        public boolean buscar(int valor) {
            return buscarNo(raiz, valor);
        }

        // Busca recursiva
        private boolean buscarNo(No atual, int valor) {
            if (atual == null) {
                return false; // Chegou no fim e não achou
            }
            if (valor == atual.valor) {
                return true; // Achei o valor!
            }

            // Se o valor que eu quero é menor, procuro na esquerda, senão na direita
            if (valor < atual.valor) {
                return buscarNo(atual.esquerda, valor);
            } else {
                return buscarNo(atual.direita, valor);
            }
        }

        // --- Percursos (Formas de andar pela árvore) ---

        // Pré-ordem: Raiz primeiro, depois esquerda, depois direita
        public void preOrdem(No no) {
            if (no != null) {
                System.out.print(no.valor + " ");
                preOrdem(no.esquerda);
                preOrdem(no.direita);
            }
        }

        // Em-ordem: Esquerda, Raiz, Direita (imprime ordenado)
        public void emOrdem(No no) {
            if (no != null) {
                emOrdem(no.esquerda);
                System.out.print(no.valor + " ");
                emOrdem(no.direita);
            }
        }

        // Pós-ordem: Esquerda, Direita, depois a Raiz
        public void posOrdem(No no) {
            if (no != null) {
                posOrdem(no.esquerda);
                posOrdem(no.direita);
                System.out.print(no.valor + " ");
            }
        }
    }

    // Estrutura Linear: Pilha 
    // Usei um vetor simples para simular a pilha
    static class MinhaPilha {
        int[] vetor;
        int topo;
        int tamanho;

        public MinhaPilha(int tamanho) {
            this.tamanho = tamanho;
            vetor = new int[tamanho];
            topo = -1; // -1 significa que está vazia
        }

        // Adicionar na pilha (Push)
        public void empilhar(int valor) {
            if (topo < tamanho - 1) {
                topo++;
                vetor[topo] = valor;
                System.out.println("Pilha: Coloquei o " + valor);
            } else {
                System.out.println("Erro: A pilha encheu!");
            }
        }

        // Tirar da pilha (Pop)
        public int desempilhar() {
            if (topo >= 0) {
                int valorRemovido = vetor[topo];
                topo--;
                return valorRemovido;
            } else {
                System.out.println("Erro: A pilha já está vazia!");
                return -1;
            }
        }
    }

    //Método de Ordenação: BubbleSort
    // O jeito mais clássico de ordenar trocando os vizinhos
    public static void ordenarBubbleSort(int[] lista) {
        int n = lista.length;
        int aux; // Variavel auxiliar para troca

        // Esse primeiro loop garante que vamos passar pelo vetor várias vezes
        for (int i = 0; i < n; i++) {
            // Esse segundo loop vai comparando os pares um do lado do outro
            // O "- i" serve para não verificar de novo o que já está ordenado no final
            for (int j = 0; j < n - 1 - i; j++) {
                // Se o da esquerda for maior que o da direita, a gente troca
                if (lista[j] > lista[j + 1]) {
                    aux = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = aux;
                }
            }
        }
    }

    // Main para testar tudo
    public static void main(String[] args) {
        System.out.println("=== Rodando meu Projeto Final ===");

        // 1. Testando a Árvore
        System.out.println("\n1. Teste da Árvore BST:");
        ArvoreBST arvore = new ArvoreBST();
        // Inserindo alguns números bagunçados
        arvore.inserir(45);
        arvore.inserir(10);
        arvore.inserir(7);
        arvore.inserir(90);
        arvore.inserir(12);
        arvore.inserir(50);

        System.out.print("Ordem Crescente (Em-Ordem): ");
        arvore.emOrdem(arvore.raiz);
        
        System.out.print("\nPré-Ordem: ");
        arvore.preOrdem(arvore.raiz);

        System.out.print("\nPós-Ordem: ");
        arvore.posOrdem(arvore.raiz);

        System.out.println("\n\nBuscando o numero 90: " + arvore.buscar(90));
        System.out.println("Buscando o numero 100: " + arvore.buscar(100));

        // 2. Testando a Pilha
        System.out.println("\n2. Teste da Pilha:");
        MinhaPilha pilha = new MinhaPilha(5);
        pilha.empilhar(100);
        pilha.empilhar(200);
        pilha.empilhar(300);
        System.out.println("Tirei o: " + pilha.desempilhar()); // Deve tirar o 300

        // Testando o BubbleSort
        System.out.println("\n3. Teste do BubbleSort:");
        int[] numeros = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.print("Vetor bagunçado: ");
        for (int n : numeros) System.out.print(n + " ");
        
        ordenarBubbleSort(numeros);
        
        System.out.print("\nVetor arrumado: ");
        for (int n : numeros) System.out.print(n + " ");
        
        System.out.println("\n\nFim dos testes.");
    }
}