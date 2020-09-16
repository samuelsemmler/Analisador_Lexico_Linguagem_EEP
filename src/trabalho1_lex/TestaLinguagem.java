package trabalho1_lex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 *
 * @author Marcos Vitti
 */
public class TestaLinguagem {

    public static void main(String[] args) throws IOException {
        //String teste = "eep {!#ra INT:#nome STR:&setAluno(@idade; @nome) [~idade => @idade:~nome => @nome:]*setAluno(22; %Marcos%):!}";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o caminho do arquivo: ");
        String path = scanner.nextLine();
        String content = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while (( line = br.readLine()) != null) {
                content += line;
            }

            System.out.println(content);
            Lexico lexico = new Lexico(new StringReader(content));
            lexico.yylex();
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
