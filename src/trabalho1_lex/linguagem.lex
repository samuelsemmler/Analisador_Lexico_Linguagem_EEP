package trabalho1_lex;

import java.io.FileWriter;
import java.io.IOException;

%%

%{

private void imprimir(String token, String lexema) throws IOException {
    System.out.println(lexema + " - " + token + "\n\n");

    FileWriter myWriter = null;
        try {
            String so = String.valueOf(System.getProperty("os.name"));

            if (so.substring(0, 1).equals("L")) {
                myWriter = new FileWriter("src/trabalho1_lex/resultado.txt", true);
            } else {
                myWriter = new FileWriter("src\\trabalho1_lex\\resultado.txt", true);
            }
        } catch (IOException ex) {
            System.out.println("Erro saindo...");
        }
    myWriter.write(lexema + " - " + token + "\n\n");
    myWriter.close();
}

%}

%class Lexico
%type void

BRANCO = " "
BRANCOS = [\t\n]+
BLOCO_MAIN_INICIO = "{!" 
BLOCO_MAIN_FIM = "!}"
BLOCO_FUNCAO_INICIO = "["
BLOCO_FUNCAO_FIM = "]"
PARAMETROS_FUNCAO_INICIO = "("
PARAMETROS_FUNCAO_FIM = ")"
INICIO_FUNCAO = \&[a-z|A-Z][a-z|A-Z|0-9]*
FINAL_INSTRUCAO = ":"
SEPARA_PARAMETROS_FUNCAO = ";"
ATRIBUICAO = "=>"
STRING = %.*%
INTEIRO = [0-9][0-9]*
CHAMA_FUNCAO = \*[a-z|A-Z][a-z|A-Z|0-9]*
INT = " INT"
STR = " STR"
DECLARA_VARIAVEL = \#[a-z|A-Z][a-z|A-Z|0-9]*
DECLARA_VARIAVEL_FUNCAO = \@[a-z|A-Z][a-z|A-Z|0-9]*
ACESSAR_VARIAVEL = \~[a-z|A-Z][a-z|A-Z|0-9]*


%%
"if"                            {imprimir("Comando Condicional", yytext());}
"while"                         {imprimir("Comando looping", yytext());}
"eep"                           {imprimir("Palavra reservada EEP", yytext());}
{CHAMA_FUNCAO}                  {imprimir("Chama a função", yytext());}
{BLOCO_MAIN_INICIO}             {imprimir("Bloco principal inicio", yytext());}
{BLOCO_MAIN_FIM}                {imprimir("Bloco principal fim", yytext());}
{DECLARA_VARIAVEL}({INT}|{STR}) {imprimir("Declaração de variável", yytext());}
{FINAL_INSTRUCAO}               {imprimir("Final de instrução", yytext());}
{INICIO_FUNCAO}                 {imprimir("Declaração de função", yytext());}
{DECLARA_VARIAVEL_FUNCAO}       {imprimir("Declaração de parametros em função", yytext());}
{SEPARA_PARAMETROS_FUNCAO}      {imprimir("Separação de parametros em função", yytext());}
{BLOCO_FUNCAO_INICIO}           {imprimir("Bloco função inicio", yytext());}
{BLOCO_FUNCAO_FIM}              {imprimir("Bloco função fim", yytext());}
{ATRIBUICAO}                    {imprimir("Atribuição de valor", yytext());}
{PARAMETROS_FUNCAO_INICIO}      {imprimir("Passagem de parametros para função inicio", yytext());}
{ACESSAR_VARIAVEL}              {imprimir("Acessa a variável", yytext());}
{PARAMETROS_FUNCAO_FIM}         {imprimir("Passagem de parametros para função fim", yytext());}
{INTEIRO}                       {imprimir("Número inteiro", yytext());}
{STRING}                        {imprimir("String", yytext());}
{BRANCOS}                       {}
{BRANCO}                        {}
.                               {imprimir("Caractere não reconhecido", yytext());}