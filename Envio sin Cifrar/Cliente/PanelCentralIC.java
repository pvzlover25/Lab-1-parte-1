package Cliente;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import PaqueteEnvio.Paquete;

public class PanelCentralIC extends JPanel{
    private File archivoAleer;
    private int largo;
    private byte[] datos;
    private boolean pintarCarga;
    private Timer t;
    public PanelCentralIC(){
        this.setBackground(VentanaInicialC.FONDO);
        archivoAleer=null;
        largo=0;
        pintarCarga=false;
        datos=null;
        t=null;
    }
    public void paint(Graphics gr){
        super.paint(gr);
        if(pintarCarga){
            gr.setColor(Color.black);
            gr.fillRect(100,100,310,75);
            gr.setColor(VentanaInicialC.FONDO);
            gr.fillRect(105,105,300,65);
            gr.setColor(Color.green);
            gr.fillRect(105,105,largo,65);
            gr.setColor(Color.black);
            Graphics2D gd=(Graphics2D)gr;
            gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            gd.setFont(new Font("Arial",Font.BOLD,20));
            gd.drawString(largo/3+"%",100,95);
        }
    }
    public void setArchivoAleer(File f, BotonSurIC boton){
        archivoAleer=f;
        datos=new byte[(int)f.length()];
        pintarCarga=true;
        repaint();
        t=new Timer(200,new AccionTimer(boton));
    }
    public void iniciarTimer(){
        t.start();
    }
    public Paquete crearPaquete(){
        Paquete ret=new Paquete(datos,archivoAleer.getName(),archivoAleer.length());
        return ret;
    }
    public void reiniciarCarga(){
        largo=0;
        repaint();
    }
    private class AccionTimer implements ActionListener{
        private FileInputStream lector;
        private BotonSurIC boton;
        private int[] partes;
        private int indice;
        public AccionTimer(BotonSurIC boton){
            lector=null;
            int size=(int)archivoAleer.length();
            partes=new int[11];
            partes[0]=0;
            for(int i=1;i<=10;i++){
                double proporcion=(double)i/10;
                partes[i]=(int)(proporcion*size);
            }
            indice=0;
            this.boton=boton;
        }
        public void actionPerformed(ActionEvent ae){
            try{
                if(lector==null) lector=new FileInputStream(archivoAleer);
                for(int i=partes[indice];i<partes[indice+1]-1;i++){
                    datos[i]=(byte)lector.read();
                }
                largo+=30;
                repaint();
                indice++;
                if(indice==10){
                    lector.close();
                    boton.setEnabled(true);
                    t.stop();
                }
            }catch(IOException ioe){
                System.err.println("Error en la lectura del archivo");
            }
        }
    }
}
