/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */

package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Controlador.ControladorServidor;

public class ConexionServidor extends Thread{
    ServerSocket servidor;
    HiloCliente cliente;
    ArrayList<HiloCliente> arregloClientes;
    int canticadClientes;
    Multicast multicast;
    boolean sendExam;
    Archivo archivo;

    public ConexionServidor(int puerto)
    {
        canticadClientes=0;
        try {
            System.out.println("\nConcectando por el puerto "+puerto+". Por favor espere");
            servidor = new ServerSocket(puerto);
            System.out.println("\nServidor iniciado "+servidor);
            this.multicast = ControladorServidor.getMulticast();
            start();
        } catch (IOException e) {
            System.out.println("Error al crear socket del servidor");
        }
    }

    @Override
    public void run() {
        //Multicast multicast = new Multicast();
        System.out.println("Creo el multicast\n");
            try {
                System.out.println("\nEsperando un cliente");
                Boolean aceptado = addCliente(servidor.accept());
                System.out.println("Cliente conectado");
            } catch (IOException e) {
                System.out.println("Error al aceptar cliente");
            }
    }

    public boolean addCliente(Socket socket)
    {
        while(true)
        {
            if (canticadClientes <3)
            {
                canticadClientes++;
                System.out.println("\nCliente número "+canticadClientes+" conectado");
                //cliente.enviarTexto("CLIENTE"+canticadClientes);
                //cliente.enviarTexto("CLIENTE");
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
    }

    public void enviarExamenMulti (String mensaje)
    {
        multicast.enviarTextoMulti(mensaje);   
    }
    
    public int getCantClientes()
    {
        return this.canticadClientes;
    }

    public ArrayList<String> preguntasRes()
    {
        return cliente.getPreguntas();
    }

    public ArrayList<String> clientesRes()
    {
        return cliente.getClientes();
    }

    public ArrayList<String> respuestaRes()
    {
        return cliente.getRespuestas();
    }
}
