package Servidor;
import PaqueteEnvio.Paquete;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelCentralIS extends JPanel implements Runnable{
    private boolean pintarCarga;
    private int largo;
    private AreaTextoS areaTXT;
    private Paquete datosArchivo;
    private BotonSurIS boton;
    private File archivoAguardar;
    private Timer t;
    public PanelCentralIS(BotonSurIS botonsur){
        this.setBackground(VentanaInicialS.FONDO);
        this.setLayout(null);
        areaTXT=new AreaTextoS(20,20,440,100);
        this.add(areaTXT);
        datosArchivo=null;
        archivoAguardar=null;
        boton=botonsur;
        pintarCarga=true;
        largo=0;
        t=null;
        Thread hilo=new Thread(this);
        hilo.start();
    }
    public void paint(Graphics gr){
        super.paint(gr);
        if(pintarCarga){
            gr.setColor(Color.black);
            gr.fillRect(25,175,310,75);
            gr.setColor(VentanaInicialS.FONDO);
            gr.fillRect(30,180,300,65);
            gr.setColor(Color.green);
            gr.fillRect(30,180,largo,65);
            gr.setColor(Color.black);
            Graphics2D gd=(Graphics2D)gr;
            gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            gd.setFont(new Font("Arial",Font.BOLD,20));
            gd.drawString(largo/3+"%",25,170);
        }
    }
    public FileNameExtensionFilter crearFiltro(){
        String[] partes=datosArchivo.getNombre().split("[.]");
        String extension=partes[partes.length-1];
        String descripcion="Archivo "+extension;
        FileNameExtensionFilter ret=new FileNameExtensionFilter(descripcion,extension);
        return ret;
    }
    public String nombreArchivo(){
        return datosArchivo.getNombre();
    }
    public void setArchivoAguardar(File nuevo){
        archivoAguardar=nuevo;
    }
    public void iniciarTimer(){
        boton.setEnabled(false);
        t=new Timer(200,new AccionTimer());
        t.start();
    }
    private void paqueteRecibido(){
        areaTXT.setText(datosArchivo);
        if(!boton.tienePanel()) boton.setPanel(this);
        boton.setEnabled(true);
        pintarCarga=true;
        largo=0;
        repaint();
    }
    public void run(){
        try{
            ServerSocket server=new ServerSocket(9999);
            while(true){
                Socket sc=server.accept();
                ObjectInputStream flujoDatos=new ObjectInputStream(sc.getInputStream());
                datosArchivo=(Paquete)flujoDatos.readObject();
                paqueteRecibido();
                flujoDatos.close();
                sc.close();
            }
        }catch(Exception ex){
            
        }
    }
    private class AccionTimer implements ActionListener{
        private FileOutputStream escritor;
        private int[] partes;
        private byte[] datos;
        private int indice;
        public AccionTimer(){
            escritor=null;
            indice=0;
            int tam=(int)datosArchivo.getTamanio();
            partes=new int[11];
            partes[0]=0;
            partes[10]=tam;
            for(int i=1;i<=9;i++){
                double proporcion=(double)i/10;
                partes[i]=(int)(proporcion*tam);
            }
            datos=datosArchivo.getdatos();
        }
        public void actionPerformed(ActionEvent ae){
            try{
                if(escritor==null) escritor=new FileOutputStream(archivoAguardar);
                //if(indice==0) System.out.println(datos.length);
                for(int i=partes[indice];i<partes[indice+1]-1;i++){
                    escritor.write(datos[i]);
                }
                largo+=30;
                repaint();
                indice++;
                //System.out.println(indice);
                if(indice==10){
                    escritor.close();
                    t.stop();
                    JOptionPane.showMessageDialog(null,"Archivo guardado con exito");
                }
            }catch(IOException ioe){
                System.err.println("Error al guardar el archivo");
            }
        }
    }
}
