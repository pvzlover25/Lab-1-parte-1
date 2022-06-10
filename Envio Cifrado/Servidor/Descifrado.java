package Servidor;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Descifrado {
    public static String llave="maraguayo2019";
    private static SecretKeySpec crearClave(String llave) throws Exception{
        byte[] cadena=llave.getBytes("UTF-8");
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        cadena=md.digest(cadena);
        cadena=Arrays.copyOf(cadena,16);
        SecretKeySpec ret=new SecretKeySpec(cadena,"AES");
        return ret;
    }
    public static String desencriptar(String st){
        try{
            SecretKeySpec sks=crearClave(llave);
            Cipher c=Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE,sks);
            byte[] cadena=Base64.decode(st);
            byte[] desencriptado=c.doFinal(cadena);
            String ret=new String(desencriptado);
            return ret;
        }catch(Exception ex){return "";}
    }
}
