package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}

%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
("\"") {return new Symbol(sym.Comillas, yychar, yyline, yytext());}
( byte | int | char | long | float | double ) {return new Symbol(sym.TipoDato, yychar, yyline, yytext());}
( String ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}
"=" {return new Symbol(sym.Igual, yychar, yyline, yytext());}
"+" {return new Symbol(sym.Suma, yychar, yyline, yytext());}
"-" {return new Symbol(sym.Resta, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
"/" {return new Symbol(sym.Division, yychar, yyline, yytext());}
( "&&" | "||" | "!" | "&" | "|" ) {return new Symbol(sym.OperadoresLogico, yychar, yyline, yytext());}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return new Symbol(sym.Operadoresrelacional, yychar, yyline, yytext());}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return new Symbol(sym.Operadoresatribucion, yychar, yyline, yytext());}
( "++" | "--" ) {return new Symbol(sym.Operadoresincremento, yychar, yyline, yytext());}
(true | false) {return new Symbol(sym.Operadoresbooleano, yychar, yyline, yytext());}
"(" {return new Symbol(sym.ParentesisAbierto, yychar, yyline, yytext());}
")" {return new Symbol(sym.ParentesisCerrado, yychar, yyline, yytext());}
"{" {return new Symbol(sym.LlavesAbierto, yychar, yyline, yytext());}
"}" {return new Symbol(sym.LLavesCerrado, yychar, yyline, yytext());}
"[" {return new Symbol(sym.CorchetesAbierto, yychar, yyline, yytext());}
"]" {return new Symbol(sym.CorchetesCerrado, yychar, yyline, yytext());}
"main" {return new Symbol(sym.Main, yychar, yyline, yytext());}
";" {return new Symbol(sym.PuntoComa, yychar, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
