/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */

package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionServidor extends Thread{
    ServerSocket servidor;
    HiloCliente cliente;
    int canticadClientes;
    Multicast multicast;
    boolean sendExam;
    Archivo archivo;

    public ConexionServidor(int puerto)
    {
        try {
            System.out.println("\nConcectando por el puerto "+puerto+". Por favor espere");
            servidor = new ServerSocket(12345);
            System.out.println("\nServidor iniciado "+servidor);
            start();
        } catch (IOException e) {
            System.out.println("Error al crear socket del servidor");
        }
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                System.out.println("\nEsperando un cliente");
                addCliente(servidor.accept());
                System.out.println("Cliente conectado");
            } catch (IOException e) {
                System.out.println("Error al aceptar cliente");
            }
        }
    }

    public boolean addCliente(Socket socket)
    {
        if (canticadClientes <3)
        {
            canticadClientes++;
            System.out.println("\nCliente número "+canticadClientes+" conectado");
            cliente = new HiloCliente(socket, canticadClientes, multicast);
            cliente.obtenerFlujos();
            cliente.start();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void enviarExamenMulti (String mensaje)
    {
        multicast.enviarTextoMulti(mensaje);   
    }
    
    public int getCantClientes()
    {
        return this.canticadClientes;
    }
}
