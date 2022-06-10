package Servidor;
import javax.swing.JPanel;

public class PanelSurIS extends JPanel{
    private BotonSurIS boton;
    public PanelSurIS(){
        this.setBackground(VentanaInicialS.FONDO);
        boton=new BotonSurIS();
        this.add(boton);
    }
    public BotonSurIS getBoton(){
        return boton;
    }
}
