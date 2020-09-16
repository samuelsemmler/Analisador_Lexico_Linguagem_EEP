package trabalho1_lex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.showMessageDialog;
import java.io.*;

public class TestaLinguagem {

    public static void main(String[] args) throws IOException {
        //Creating the frame
        JFrame frame = new JFrame("Lexical Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel chooseFileLabel = new JLabel("Select program file");

        JTextField chooseFileTextField = new JTextField();
        chooseFileTextField.setVisible(false);

        JButton chooseFileButton = new JButton("Choose");
        JButton generateFileButton = new JButton("Generate Lexical File");
        generateFileButton.setVisible(false);

        JButton creditsButton = new JButton("Credits");
        panel.add(chooseFileLabel);
        panel.add(chooseFileTextField);
        panel2.add(chooseFileButton);
        panel2.add(generateFileButton);
        panel3.add(creditsButton);

        // Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel3);
        frame.setVisible(true);

        //Adding events
        chooseFileButton.addActionListener((ActionEvent actionEvent) -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    chooseFileTextField.setText(selectedFile.getAbsolutePath());
                    generateFileButton.setVisible(true);
                }
            } catch (HeadlessException ex) {
                showMessageDialog(null, ex.getMessage());
            }
        });

        generateFileButton.addActionListener((ActionEvent actionEvent) -> {
            generateLexicalFile(chooseFileTextField.getText());
            showMessageDialog(null, "Process finished");
        });

        creditsButton.addActionListener((ActionEvent actionEvent) -> {
            String credits = "" +
                    "MARCOS MENGHINI VITTI\n" +
                    "ROBERWAL JUNIOR\n" +
                    "SAMUEL AUGUSTO SEMMLER\n";
            showMessageDialog(null, credits);
        });
    }

    private static void generateLexicalFile(String filePath) {

        FileWriter myWriter = null;

        String so = String.valueOf(System.getProperty("os.name"));

        try {
            if (so.substring(0, 1).equals("L")) {
                myWriter = new FileWriter("src/trabalho1_lex/resultado.txt");
            } else {
                myWriter = new FileWriter("src\\trabalho1_lex\\resultado.txt");
            }
        } catch (IOException ex) {
            showMessageDialog(null, "Error");
        }

        String content = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                content += line;
            }

            System.out.println(content);
            Lexico lexico = new Lexico(new StringReader(content));
            lexico.yylex();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
