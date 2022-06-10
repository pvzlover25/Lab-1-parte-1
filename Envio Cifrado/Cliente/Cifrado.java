package Cliente;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
    public static String llave="maraguayo2019";
    private static SecretKeySpec crearClave(String llave) throws Exception{
        byte[] cadena=llave.getBytes("UTF-8");
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        cadena=md.digest(cadena);
        cadena=Arrays.copyOf(cadena,16);
        SecretKeySpec ret=new SecretKeySpec(cadena,"AES");
        return ret;
    }
    public static String encriptar(String st){
        try{
            SecretKeySpec sks=crearClave(llave);
            Cipher c=Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE,sks);
            byte[] cadena=st.getBytes("UTF-8");
            byte[] encriptada=c.doFinal(cadena);
            String ret=Base64.encode(encriptada);
            return ret;
        }catch(Exception ex){return "";}
    }
}
