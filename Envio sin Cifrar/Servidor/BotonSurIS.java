package Servidor;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class BotonSurIS extends JButton implements ActionListener{
    private PanelCentralIS panel;
    public BotonSurIS(){
        super("Guardar archivo");
        panel=null;
        this.setEnabled(false);
        this.addActionListener(this);
    }
    public boolean tienePanel(){
        return (panel!=null);
    }
    public void setPanel(PanelCentralIS nuevo){
        panel=nuevo;
    }
    public void actionPerformed(ActionEvent ae){
        JFileChooser save=new JFileChooser();
        save.setAcceptAllFileFilterUsed(false);
        save.addChoosableFileFilter(panel.crearFiltro());
        save.setSelectedFile(new File(panel.nombreArchivo()));
        if(save.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            panel.setArchivoAguardar(save.getSelectedFile());
            panel.iniciarTimer();
        }
    }
}
