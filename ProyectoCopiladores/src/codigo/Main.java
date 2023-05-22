
package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        
        String ruta1 = "D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/Lexer.flex";
        String ruta2 = "D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/Sintax.cup"};
        
        generar(ruta1, ruta2, rutaS);
    }
    
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        
        java_cup.Main.main(rutaS);
        
        
        Path rutaSym = Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/sym.java"), 
                Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/sym.java")
        );
        Path rutaSin = Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/Sintax.java"), 
                Paths.get("D:/Documentos/NetBeansProjects/ProyectoCopiladores/src/codigo/Sintax.java")
        );
    }
}