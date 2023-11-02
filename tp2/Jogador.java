import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jogador {
    private String conteudoCSV;
    private String[][] elementos = new String[3922][8];
    private String[] linhas = new String[3922];
    private int[] linha = new int[3922];
    private int log2 = 0;
    private int log3 = 0;
    private static Scanner sc = new Scanner(System.in);


    // Método para escrever no arquivo de log
    /*public void log(long log1, int log2) {
        try {
            FileWriter writer = new FileWriter("matricula_heapsort.txt");
            writer.write("815331\t" + log2 + "\t" + log3 + "\t" + log1 + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }*/

    // Método lerArquivo
    public void lerArquivo(String arquivo) {
        try {
            Scanner fileReader = new Scanner(new File(arquivo));
            StringBuilder conteudo = new StringBuilder();
    
            // Adiciona um condicional para pular a primeira linha
            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }
    
            while (fileReader.hasNextLine()) {
                conteudo.append(fileReader.nextLine()).append("\n");
            }
    
            fileReader.close();
            this.conteudoCSV = conteudo.toString(); // Atualiza a variável conteudoCSV da classe
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + arquivo);
        }
    }
    
    // Método concatenaLinha
    public void concatenaLinha() {
        linhas = conteudoCSV.split("\n");
        for (int i = 0; i < linhas.length && i < 3922; i++) {
            String[] dadosLinha = linhas[i].split(",", -1);
            for (int j = 0; j < dadosLinha.length && j < 8; j++) {
                elementos[i][j] = dadosLinha[j].isEmpty() ? "nao informado" : dadosLinha[j];
            }
        }
    }

    public void buildMaxHeap(int linha[], int cont) {
        for (int i = cont / 2 - 1; i >= 0; i--) {
            heapify(linha, cont, i);
        }
    }
    
    public void heapify(int linha[], int cont, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
    
        if (left < cont) {
            log2++; // Incrementa o contador de comparações
            if (compare(linha[left], linha[largest])) {
                largest = left;
            }
        }
    
        if (right < cont) {
            log2++; // Incrementa o contador de comparações
            if (compare(linha[right], linha[largest])) {
                largest = right;
            }
        }
    
        if (largest != i) {
            int swap = linha[i];
            linha[i] = linha[largest];
            linha[largest] = swap;
    
            log3++; // Incrementa o contador de movimentações
    
            heapify(linha, cont, largest);
        }
    }
    
    public void heapSort(int linha[], int cont) {
        buildMaxHeap(linha, cont);
    
        for (int i = cont - 1; i >= 0; i--) {
            int temp = linha[0];
            linha[0] = linha[i];
            linha[i] = temp;
    
            log3++; // Incrementa o contador de movimentações
    
            heapify(linha, i, 0);
        }
    }
    
    public boolean compare(int a, int b) {
        int alturaA = Integer.parseInt(elementos[a][2]);
        int alturaB = Integer.parseInt(elementos[b][2]);
    
        return (alturaA > alturaB || (alturaA == alturaB && elementos[a][1].compareTo(elementos[b][1]) > 0));
    }

    // Método Entrada
    public void entrada() {
        concatenaLinha();
        String numeroLinha;
        linha = new int[3922];
        int cont = 0;
        do {
            numeroLinha = sc.nextLine();
            if (!numeroLinha.equals("FIM")) {
                linha[cont] = Integer.parseInt(numeroLinha);
                cont++;
            }
        } while (!numeroLinha.equals("FIM"));
        heapSort(linha, cont);
        imprimirLinhas(linha, cont);
    }

    // Método imprimirLinhas
    public void imprimirLinhas(int linha[], int cont) {
        for(int i = 0; i < 10; i++)
            System.out.println("[" + elementos[linha[i]][0] + " ## " + elementos[linha[i]][1] + " ## " + elementos[linha[i]][2] + " ## " + elementos[linha[i]][3] + " ## " + elementos[linha[i]][5] + " ## " + elementos[linha[i]][4] + " ## " + elementos[linha[i]][6] + " ## " + elementos[linha[i]][7] + "]");
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Captura o tempo de início
        Jogador jogador = new Jogador();
        jogador.lerArquivo("/tmp/players.csv");
        jogador.entrada();
        long endTime = System.currentTimeMillis(); // Captura o tempo de término
        long log1 = endTime - startTime; // Calcula o tempo total de execução
        //jogador.log(log1,jogador.log2);
        sc.close();
    }
}
