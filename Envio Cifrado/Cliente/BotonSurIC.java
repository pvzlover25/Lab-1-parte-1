package Cliente;
import Cliente.PanelCentralIC;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import PaqueteEnvio.Paquete;

public class BotonSurIC extends JButton implements ActionListener{
    private PanelCentralIC panel;
    private int indice;
    public BotonSurIC(PanelCentralIC panel){
        super("Elegir Archivo");
        this.panel=panel;
        indice=0;
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        if(indice==0) elegirArchivo();
        if(indice==1) cargarArchivo();
        if(indice==2) enviarArchivo();
        indice++;
    }
    private void elegirArchivo(){
        JFileChooser select=new JFileChooser();
        int eleccion=select.showOpenDialog(panel);
        if(eleccion==JFileChooser.APPROVE_OPTION){
            panel.setArchivoAleer(select.getSelectedFile(),this);
            this.setText("Cargar archivo");
        }else indice--;
    }
    private void cargarArchivo(){
        panel.iniciarTimer();
        this.setEnabled(false);
        this.setText("Enviar archivo");
    }
    private void enviarArchivo(){
        //JOptionPane.showMessageDialog(null,"Soy un mensaje");
        String ip=JOptionPane.showInputDialog("Ingresar la IP del servidor a donde enviar el archivo");
        try{
            Socket sc=new Socket(ip,9999);
            Paquete enviar=panel.crearPaquete();
            ObjectOutputStream flujoDatos=new ObjectOutputStream(sc.getOutputStream());
            flujoDatos.writeObject(enviar);
            flujoDatos.close();
            sc.close();
            panel.reiniciarCarga();
            this.setText("Elegir archivo");
            JOptionPane.showMessageDialog(null,"Archivo enviado con exito");
            indice=-1;
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"No se ingresó una IP valida","Error",0);
            indice--;
        }
    }
}
