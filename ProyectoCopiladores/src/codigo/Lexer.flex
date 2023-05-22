/*
 * Este archivo obtiene tokens de un lenguaje de programación mediante el uso de JFlex, una herramienta para crear analizadores léxicos.
 * Se encarga de identificar palabras clave, números, identificadores y símbolos especiales en un programa.
 * Los tokens son palabras que se utilizan en la sintaxis de un lenguaje de programación, como "if", "else", "while", "+" o "=".
 */

package codigo;
import static codigo.Tokens.*;

// Definición de las clases y tipos de tokens que se utilizarán.
// La primera línea define la clase del analizador léxico,
// y la segunda línea define el tipo de dato que se utilizará para cada token.

%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+

// Se definen las acciones que se realizarán para cada token reconocido.
// En este caso, se guarda el valor del token en una variable llamada "lexeme"
// y luego se devuelve el tipo de token correspondiente utilizando la palabra reservada "return".
// Las expresiones regulares se encierran en llaves para referirse a la cadena que coincide con la expresión regular.

%{
    public String lexeme; //esta variable almacena el valor del token cuando es encontrado
%}

// Definición de las reglas para reconocer tokens.
// Por ejemplo, la expresión "int" se reconocerá como una palabra reservada de tipo "Reservadas".
// La expresión "{espacio}" ignora los espacios en blanco.
// La expresión ""//".*" ignora los comentarios de una sola línea.
// Las expresiones "(", ")", "[", "]", "{", "}", "=", "+", "-", "*" y "/" corresponden a símbolos especiales.
// La expresión "({L}|{D})*" reconoce identificadores que contienen letras y números.
// La expresión "((-{D}+))|{D}+" reconoce números enteros o negativos.
// La expresión "." reconoce cualquier otro carácter y devuelve un token de error.

%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"\n" {return Linea;}
("\"") {lexeme=yytext(); return Comillas;}
( byte | int | char | long | float | double ) {lexeme=yytext(); return TipoDato;}
( String ) {lexeme=yytext(); return Cadena;}
( if ) {lexeme=yytext(); return If;}
( else ) {lexeme=yytext(); return Else;}
( do ) {lexeme=yytext(); return Do;}
( while ) {lexeme=yytext(); return While;}
( for ) {lexeme=yytext(); return For;}
"=" {lexeme=yytext(); return Igual;}
"+" {lexeme=yytext(); return Suma;}
"-" {lexeme=yytext(); return Resta;}
"*" {lexeme=yytext(); return Multiplicacion;}
"/" {lexeme=yytext(); return Division;}
( "&&" | "||" | "!" | "&" | "|" ) {lexeme=yytext(); return OperadoresLogico;}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {lexeme = yytext(); return Operadoresrelacional;}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexeme = yytext(); return Operadoresatribucion;}
( "++" | "--" ) {lexeme = yytext(); return Operadoresincremento;}
(true | false) {lexeme = yytext(); return Operadoresbooleano;}
"(" {lexeme=yytext(); return ParentesisAbierto;}
")" {lexeme=yytext(); return ParentesisCerrado;}
"{" {lexeme=yytext(); return LlavesAbierto;}
"}" {lexeme=yytext(); return LLavesCerrado;}
"[" {lexeme=yytext(); return CorchetesAbierto;}
"]" {lexeme=yytext(); return CorchetesCerrado;}
"main" {lexeme=yytext(); return Main;}
";" {lexeme=yytext(); return PuntoComa;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}
