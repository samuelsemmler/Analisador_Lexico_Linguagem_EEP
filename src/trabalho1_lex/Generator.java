package trabalho1_lex;

import java.io.File;
import java.nio.file.Paths;

public class Generator {
    public static void main(String[] args) {
        boolean isLinux = true; //True if your operating system is linux, false if your system is windows

        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "";

        if(isLinux) {
             subPath = "/src/trabalho1_lex/";
        }
        else {
            subPath = "\\src\\trabalho1_lex\\";
        }

        String file = rootPath + subPath + "linguagem.lex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);
    }
}
