package Cliente;
import java.awt.*;
import javax.swing.JFrame;;

public class VentanaInicialC extends JFrame{
    public static final Color FONDO=new Color(255,252,183);
    private PanelCentralIC pcentral;
    private PanelSurIC psur;
    public VentanaInicialC(){
        this.setTitle("Cliente");
        this.setLayout(new BorderLayout());
        pcentral=new PanelCentralIC();
        this.add(pcentral,BorderLayout.CENTER);
        psur=new PanelSurIC(pcentral);
        this.add(psur,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100,300,500,500);
        this.setVisible(true);
    }
}
