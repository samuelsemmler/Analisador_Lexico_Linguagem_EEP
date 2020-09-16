package trabalho1_lex;

import java.io.File;
import java.nio.file.Paths;

public class Generator {
    public static void main(String[] args) {
        String so = String.valueOf(System.getProperty("os.name"));

        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath;

        if (so.substring(0, 1).equals("L")) {
            subPath = "/src/trabalho1_lex/";
        } else {
            subPath = "\\src\\trabalho1_lex\\";
        }

        String file = rootPath + subPath + "linguagem.lex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);
    }
}
