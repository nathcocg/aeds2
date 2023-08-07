import java.util.Scanner;
import java.lang.String; //classe de strings

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            int v = 0;
            int verifica = 1;
            String f = new String("FIM");
            do {
                String entrada = sc.nextLine();// char entarda[tamanho] = sc.nextLine();
                int tamanho = entrada.length();
                for (int i = 0; i < tamanho; i++) {
                    if (entrada == f) {
                        System.out.println("jesus");
                        verifica = 0;
                        System.exit(0);
                    }
                    if (entrada.charAt(i) != entrada.charAt(tamanho - i - 1)) {
                        v = 1;
                    }
                }
                if (v == 0) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NÃO");
                }

            } while (verifica != 0);
        }

    }
}
