
package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Interfaz extends javax.swing.JFrame {
    
// Crear instancia de JFileChooser para seleccionar archivo JFileChooser seleccionar = new JFileChooser();
// Declarar variables de archivo y streams File archivo; FileInputStream entrada; FileOutputStream salida;
// Resto del código depende del uso específico de estas variables
    
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    
    public Interfaz() {
        initComponents();
    }
    
    public String GuardarArchivo(File archivo,String documento){
        String mensaje = null;
        try {
            // se crea un objeto FileOutputStream con el archivo especificado
            salida = new FileOutputStream(archivo);
            //se convierte el documento a bytes y se escribe en el archivo
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            //Se indica que la operacion fue exitosa
            mensaje = "Archivo Guardado";
        } catch (Exception e) {
            //Si ocurre un erro, se deja mensaje como null 
        }
        return mensaje;
    }

    
    private void analizarLexico() throws IOException{
// Se obtiene el contenido del archivo de texto a analizar. String expr = (String) txtArchivo.getText(); 
// Se crea un objeto Lexer a partir del contenido del archivo de texto. Lexer lexer = new Lexer(new StringReader(expr)); 
// Se inicializa el resultado con la línea y el símbolo. String resultado = "LINEA " + cont + "\t\tSIMBOLO
          
        int cont = 1;
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";
        // Ciclo para iterar a través de cada token del Lexer.
        while (true) {
            Tokens token = lexer.yylex();
        // Si no hay más tokens, se agrega el resultado al campo txtResultadoAL y se retorna.
            if (token == null) {
                txtResultadoAL.setText(resultado);
                return;
            }
        // Se utiliza un switch para agregar el símbolo correspondiente al resultado.
            switch (token) {
                // Si se encuentra un salto de línea, se incrementa el contador de línea y se agrega una nueva línea al resultado.
                case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    break;
                case Comillas:
                    resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                    break;
                case Cadena:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;
                case TipoDato:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;
                case If:
                    resultado += "  <Reservada if>\t" + lexer.lexeme + "\n";
                    break;
                case Else:
                    resultado += "  <Reservada else>\t" + lexer.lexeme + "\n";
                    break;
                case Do:
                    resultado += "  <Reservada do>\t" + lexer.lexeme + "\n";
                    break;
                case While:
                    resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                    break;
                case For:
                    resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                    break;
                case Igual:
                    resultado += "  <Operador igual>\t" + lexer.lexeme + "\n";
                    break;
                case Suma:
                    resultado += "  <Operador suma>\t" + lexer.lexeme + "\n";
                    break;
                case Resta:
                    resultado += "  <Operador resta>\t" + lexer.lexeme + "\n";
                    break;
                case Multiplicacion:
                    resultado += "  <Operador multiplicacion>\t" + lexer.lexeme + "\n";
                    break;
                case Division:
                    resultado += "  <Operador division>\t" + lexer.lexeme + "\n";
                    break;
                case OperadoresLogico:
                    resultado += "  <Operador logico>\t" + lexer.lexeme + "\n";
                    break;
                case Operadoresincremento:
                    resultado += "  <Operador incremento>\t" + lexer.lexeme + "\n";
                    break;
                case Operadoresrelacional:
                    resultado += "  <Operador relacional>\t" + lexer.lexeme + "\n";
                    break;
                case Operadoresatribucion:
                    resultado += "  <Operador atribucion>\t" + lexer.lexeme + "\n";
                    break;
                case Operadoresbooleano:
                    resultado += "  <Operador booleano>\t" + lexer.lexeme + "\n";
                    break;
                case ParentesisAbierto:
                    resultado += "  <Parentesis de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case ParentesisCerrado:
                    resultado += "  <Parentesis de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case LlavesAbierto:
                    resultado += "  <Llave de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case LLavesCerrado:
                    resultado += "  <Llave de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case CorchetesAbierto:
                    resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case CorchetesCerrado:
                    resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case Main:
                    resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                    break;
                case PuntoComa:
                    resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                    break;
                case Numero:
                    resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                    break;
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    break;
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultadoAL = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        btnAnalizadorL = new javax.swing.JButton();
        btnNuevoAL = new javax.swing.JButton();
        btnGuardarAL = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResultadoAS = new javax.swing.JTextArea();
        btnAnalizadorS = new javax.swing.JButton();
        btnNuevoAS = new javax.swing.JButton();
        btnGuardarAS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Analizador Lexico");

        btnExaminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExaminar.setText("Examinar");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        txtResultadoAL.setEditable(false);
        txtResultadoAL.setColumns(20);
        txtResultadoAL.setRows(5);
        jScrollPane2.setViewportView(txtResultadoAL);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("----->");

        btnAnalizadorL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAnalizadorL.setText("Analizar");
        btnAnalizadorL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizadorLActionPerformed(evt);
            }
        });

        btnNuevoAL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevoAL.setText("Nuevo Analisis");
        btnNuevoAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoALActionPerformed(evt);
            }
        });

        btnGuardarAL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarAL.setText("Guardar");
        btnGuardarAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarALActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Analizador Sintactica");

        txtResultadoAS.setEditable(false);
        txtResultadoAS.setColumns(20);
        txtResultadoAS.setRows(5);
        jScrollPane3.setViewportView(txtResultadoAS);

        btnAnalizadorS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAnalizadorS.setText("Analizar");
        btnAnalizadorS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizadorSActionPerformed(evt);
            }
        });

        btnNuevoAS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevoAS.setText("Nuevo Analisis");
        btnNuevoAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoASActionPerformed(evt);
            }
        });

        btnGuardarAS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarAS.setText("Guardar");
        btnGuardarAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarASActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(358, 358, 358)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel3))
                                .addComponent(btnExaminar))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAnalizadorL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevoAL))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(115, 115, 115)
                            .addComponent(btnGuardarAL)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGuardarAS)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnAnalizadorS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNuevoAS))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExaminar)
                    .addComponent(btnAnalizadorL)
                    .addComponent(btnNuevoAL))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarAL)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizadorS)
                    .addComponent(btnNuevoAS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarAS)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizadorLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizadorLActionPerformed
       try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }//GEN-LAST:event_btnAnalizadorLActionPerformed

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
    //Crea una instancia de la clase JFileChooser, permitiendo al usuario seleccionar un archivo  
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        //Crea un objeto File basado en la selección del usuario
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
        //Lee todo el contenido del archivo seleccionado y lo guarda en una variable de cadena llamada ST
            String ST = new String(Files.readAllBytes(archivo.toPath()));
        //Muestra el contenido del archivo en un área de texto con la ayuda del objeto txtArchivo
            txtArchivo.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnNuevoALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoALActionPerformed
        txtResultadoAL.setText(null); // Limpia el contenido del campo de texto
    }//GEN-LAST:event_btnNuevoALActionPerformed

    private void btnNuevoASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoASActionPerformed
        txtResultadoAS.setText(null); // Limpia el contenido del campo de texto
    }//GEN-LAST:event_btnNuevoASActionPerformed

    private void btnGuardarALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarALActionPerformed
        // if lo que haces es que si el usuario ha seleccionado "Guardar" en el diálogo del selector de archivos 
        if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
            // Obtener el archivo seleccionado
           archivo = seleccionar.getSelectedFile();
           // Aqui verificamos si la extensión del archivo es .txt
           if(archivo.getName().endsWith("txt")){
               // Obtener el texto del campo "txtResultado"
               String Documento=txtResultadoAL.getText();
               // Guarda el texto en el archivo seleccionado
               String mensaje=GuardarArchivo(archivo, Documento);
               // Si hubo un error al guardar el archivo, mostrar un mensaje de error
               if(mensaje!=null){
                   JOptionPane.showMessageDialog(null, mensaje);
                // Si la extensión del archivo no es .txt, mostrar un mensaje de error  
               }else{
                   JOptionPane.showMessageDialog(null, "Archivo No Compatible");
               }
               // De lo contrario, muestra un mensaje de éxito
           }else{
               JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
           }
       }

    }//GEN-LAST:event_btnGuardarALActionPerformed

    private void btnGuardarASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarASActionPerformed
        if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
            // Obtener el archivo seleccionado
           archivo = seleccionar.getSelectedFile();
           // Aqui verificamos si la extensión del archivo es .txt
           if(archivo.getName().endsWith("txt")){
               // Obtener el texto del campo "txtResultado"
               String Documento=txtResultadoAS.getText();
               // Guarda el texto en el archivo seleccionado
               String mensaje=GuardarArchivo(archivo, Documento);
               // Si hubo un error al guardar el archivo, mostrar un mensaje de error
               if(mensaje!=null){
                   JOptionPane.showMessageDialog(null, mensaje);
                // Si la extensión del archivo no es .txt, mostrar un mensaje de error  
               }else{
                   JOptionPane.showMessageDialog(null, "Archivo No Compatible");
               }
               // De lo contrario, muestra un mensaje de éxito
           }else{
               JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
           }
       }
    }//GEN-LAST:event_btnGuardarASActionPerformed

    private void btnAnalizadorSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizadorSActionPerformed
        // Obtener el texto del "txtArchivo"
        String ST = txtArchivo.getText();
        // Construir un analizador sintáctico con el texto obtenido
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        
        try {
            // Realizar el análisis sintáctico 
            s.parse();
            // Establecer el texto del "txtResultadoAS" y su color a verde
            txtResultadoAS.setText("Analisis realizado correctamente");
            txtResultadoAS.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            // Si se produce una excepción, obtener la información del símbolo que causó el error 
            Symbol sym = s.getS();
            txtResultadoAS.setText("Error en el sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            // Establecer el texto del "txtResultadoAS" a la mensaje de error y su color a rojo 
            txtResultadoAS.setForeground(Color.red);
        }
        
    }//GEN-LAST:event_btnAnalizadorSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizadorL;
    private javax.swing.JButton btnAnalizadorS;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnGuardarAL;
    private javax.swing.JButton btnGuardarAS;
    private javax.swing.JButton btnNuevoAL;
    private javax.swing.JButton btnNuevoAS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtResultadoAL;
    private javax.swing.JTextArea txtResultadoAS;
    // End of variables declaration//GEN-END:variables
}
