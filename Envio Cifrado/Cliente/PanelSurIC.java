package Cliente;
import Cliente.PanelCentralIC;
import javax.swing.*;

public class PanelSurIC extends JPanel{
    public PanelSurIC(PanelCentralIC panel){
        this.setBackground(VentanaInicialC.FONDO);
        this.add(new BotonSurIC(panel));
    }
}