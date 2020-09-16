package trabalho1_lex;

import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Marcos Vitti
 */
public class Generator {
    public static void main(String[] args) {
        String rootPath = Paths.get("").toAbsolutePath().toString();

        String subPath = "\\src\\trabalho1_lex\\";

        String file = rootPath + subPath + "linguagem.lex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);
    }
}
