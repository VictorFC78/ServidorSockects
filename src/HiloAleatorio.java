import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class HiloAleatorio extends Thread{
    private int numero;
    private Socket cliente;


    public HiloAleatorio(int numero, Socket cliente) {
        this.numero = numero;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        //generamo los flujos de salida para el servidor
        try {
            String respuesta;
            boolean acierto=false;
            DataOutputStream dos= new DataOutputStream(cliente.getOutputStream());
            DataInputStream dis=new DataInputStream(cliente.getInputStream());
            //oinicialmete mandamos lo que queremos que nos manda el cliente
            dos.writeUTF("Inserte un numero entero entre el 0 y 100:");
            //vamos a estar leyendo del cliente hasta que acierte con el numero
            do{
                respuesta=dis.readUTF();
                if(Integer.parseInt(respuesta)<numero){
                    dos.writeUTF("El numero es mayor,introduzca numero:");
                }else if(Integer.parseInt(respuesta)>numero){
                    dos.writeUTF("El numero es menor,introduzca numero:");
                }else{
                    dos.writeUTF("ENHORABUENA ha acertado el numero era: "+numero);
                    acierto=true;
                }
            }while(!acierto);

        } catch (IOException e) {
            System.out.println("Error al abrir el flujo de comunicacion");
        }
    }
}
