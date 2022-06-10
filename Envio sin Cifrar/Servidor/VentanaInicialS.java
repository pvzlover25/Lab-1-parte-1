package Servidor;
import java.awt.*;
import javax.swing.JFrame;

public class VentanaInicialS extends JFrame{
    public static final Color FONDO=new Color(255,252,183);
    private PanelCentralIS pcentral;
    private PanelSurIS psur;
    public VentanaInicialS(){
        this.setTitle("Servidor");
        this.setLayout(new BorderLayout());
        psur=new PanelSurIS();
        pcentral=new PanelCentralIS(psur.getBoton());
        this.add(pcentral,BorderLayout.CENTER);
        this.add(psur,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100,300,500,500);
        this.setVisible(true);
    }
}
