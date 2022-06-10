package PaqueteEnvio;
import java.io.Serializable;

public class Paquete implements Serializable{
    private static final long serialVersionUID=2;
    private String nombreArchivo;
    private String[] datosArchivo;
    private long tamanio;
    public Paquete(String[] datos, String nombreArchivo, long tamanio){
        this.nombreArchivo=nombreArchivo;
        this.tamanio=tamanio;
        datosArchivo=datos;
    }
    public String[] getdatos(){
        return datosArchivo;
    }
    public String getNombre(){
        return nombreArchivo;
    }
    public long getTamanio(){
        return tamanio;
    }
}
