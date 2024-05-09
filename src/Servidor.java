import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;

public class Servidor {
    private static int PUERTO=6000;
    public Servidor(){
        try {
            ServerSocket servidor=new ServerSocket(PUERTO);//creamos el servidor
            //va estar siempre esperando clientes
            while(true){
                System.out.println("Esperando cliente...");
                 Socket cliente =servidor.accept();//aceptamos el cliente y creamos y lanzamos un hilo
                //gemeramos el humero aleatorio y generamos un hilo que recibe el cliente y el numero aleatorio
                int aleatorio=numAleatorio();
                new HiloAleatorio(aleatorio,cliente).start();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el servidor");
        }
    }
    private int numAleatorio(){
        Random rdn=new Random();
        return rdn.nextInt(100);
    }

    public static void main(String[] args) {
        Servidor servidor=new Servidor();
    }
}
