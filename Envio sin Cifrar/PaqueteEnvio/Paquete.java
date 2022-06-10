package PaqueteEnvio;
import java.io.Serializable;

public class Paquete implements Serializable{
    private static final long serialVersionUID=1;
    private String nombreArchivo;
    private byte[] datosArchivo;
    private long tamanio;
    public Paquete(byte[] datos, String nombreArchivo, long tamanio){
        this.nombreArchivo=nombreArchivo;
        this.tamanio=tamanio;
        datosArchivo=datos;
    }
    public byte[] getdatos(){
        return datosArchivo;
    }
    public String getNombre(){
        return nombreArchivo;
    }
    public long getTamanio(){
        return tamanio;
    }
}
