/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessor;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.Attribute;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//import org.jdom2.input.SAXBuilder;
import java.awt.Color;
import java.awt.Container;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author oscar
 */
public class XmlProcessor extends JFrame {
    SAXBuilder archivo;
    String busqueda;
    SAXBuilder builder = new SAXBuilder();
    Document docXml = null;
    
    public XmlProcessor() {

        initUI();
    }

    private void initUI() {
        
        createMenuBar();
        archivo = new SAXBuilder();
        setTitle("Simple menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        JPopupMenu menupop = new JPopupMenu();
        
        JMenu file = new JMenu("Archivo");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu buscarMi = new JMenu("Buscar");
        buscarMi.setMnemonic(KeyEvent.VK_B);
        
        JMenuItem nuevoMi = new JMenuItem("Nuevo");
        JMenuItem abrirMi = new JMenuItem("Abrir");
        JMenuItem buscarEt = new JMenuItem("Buscar Etiqueta");
        
        JMenuItem eMenuItem = new JMenuItem("Salir");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Salir de la aplicacion");
        
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        abrirMi.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try{ 
                  docXml = archivo.build(fileChooser.getSelectedFile());
                }catch(JDOMException e){
                    System.err.println(e.getMessage());
                }catch(FileNotFoundException e){
                    System.err.println(e.getMessage());
                }catch(IOException e){
                    System.err.println(e.getMessage());
                }
                
                  JLabel label = new JLabel("aqui debe mostrar el xml");
                  label.setFont(new Font("Serif", Font.PLAIN, 14));
                  label.setForeground(new Color(50, 50, 25));
                  createLayout(label);
            }
        });
        buscarEt.addActionListener((ActionEvent event) -> {
            if(this.hasArchivo()){
                System.out.println("Tiene archivo");
                busqueda = JOptionPane.showInputDialog(buscarEt,"Etiqueta a buscar", null);
                System.out.println(busqueda);
            }
        });
        
        file.add(nuevoMi);
        file.add(abrirMi);
        file.addSeparator();
        file.add(eMenuItem);
        
        buscarMi.add(buscarEt);
        menubar.add(file);
        menubar.add(buscarMi);
        setJMenuBar(menubar);
    }
    
    private void createLayout(JComponent... arg) {
        
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);        

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );

        pack();
    }  
    
    public File getArchivo(){
       return this.archivo;
    }
    
    public Boolean hasArchivo(){
        return this.archivo != null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
    
    EventQueue.invokeLater(() -> {
         XmlProcessor ventana=new XmlProcessor();
           ventana.setVisible(true);
           
    });

       
 }
    
}
