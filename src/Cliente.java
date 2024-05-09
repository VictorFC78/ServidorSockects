import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final int PUERTO=6000;
    private static final String HOST="localhost";
    private static final String ACIERTO="ENHORABUENA";
    private Scanner sc;
    public Cliente() {
        //declaramos el cliente
        try {
            Socket cliente =new Socket(HOST,PUERTO);
            sc=new Scanner(System.in);
            String respuesta;
            String enviar;
            DataOutputStream dos=new DataOutputStream(cliente.getOutputStream());
            DataInputStream dis=new DataInputStream((cliente.getInputStream()));
            do {
                respuesta= dis.readUTF();
                if (!comprobarRespuesta(respuesta)) {
                    System.out.println(respuesta);
                    enviar = sc.next();
                    dos.writeUTF(enviar);
                }else{
                    System.out.println(respuesta);
                }
            }while(!comprobarRespuesta(respuesta));
            cliente.close();
        } catch (IOException e) {
            System.out.println("Error al crear el socket cliente");
        }

    }

    private boolean comprobarRespuesta(String datos){
        String [] totalDatos=datos.split(" ");
        for (String s:totalDatos){
            if (s.equals(ACIERTO)) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Cliente cliente=new Cliente();
    }
}
