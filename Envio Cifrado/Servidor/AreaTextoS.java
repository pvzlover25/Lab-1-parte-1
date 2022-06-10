package Servidor;
import java.awt.Font;
import javax.swing.JTextArea;
import PaqueteEnvio.Paquete;

public class AreaTextoS extends JTextArea{
    public AreaTextoS(int x, int y, int ancho, int alto){
        this.setBounds(x, y, ancho, alto);
        this.setFont(new Font("Arial",Font.PLAIN,18));
        this.setBackground(VentanaInicialS.FONDO);
        this.setText("Datos del archivo recibido:");
        this.setEditable(false);
    }
    public void setText(Paquete p){
        String nuevo="Datos del archivo recibido:\n\n";
        nuevo+="Nombre: "+p.getNombre()+'\n';
        nuevo+="Tamanio: "+p.getTamanio()+" bytes\n";
        this.setText(nuevo);
    }
}
