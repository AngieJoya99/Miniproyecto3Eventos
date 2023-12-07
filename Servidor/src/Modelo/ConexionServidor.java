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

/**
 * Clase que permite establecer una conexion con los clientes
 */
public class ConexionServidor extends Thread
{
    ServerSocket servidor;
    HiloCliente cliente;
    ArrayList<HiloCliente> arregloClientes;
    int canticadClientes;
    Multicast multicast;
    boolean sendExam;
    //Archivo archivo;

    /**
     * Contructor del servidor
     */
    public ConexionServidor(int puerto)
    {
        canticadClientes=0;
        try {
            System.out.println("\nConcectando por el puerto "+puerto+". Por favor espere");
            servidor = new ServerSocket(puerto);
            System.out.println("\nServidor iniciado "+servidor);
            this.multicast = ControladorServidor.getMulticast();
            this.arregloClientes = new ArrayList<>();
            start();
        } catch (IOException e) {
            System.out.println("Error al crear socket del servidor");
        }
    }

    /**
     * Espera y añade clientes
     */
    @Override
    public void run() 
    {
        while (true)
        {
            try {
                System.out.println("\nEsperando un cliente");
                if(canticadClientes<3)
                {   
                    addCliente(servidor.accept());
                }
                else
                {
                    break;
                }
                System.out.println("Cliente conectado");
            } catch (IOException e) {
                System.out.println("Error al aceptar cliente");
            }
        }
    }

    /**
     * Agrega los clientes
     * @param socket
     */
    public void addCliente(Socket socket)
    {
        this.canticadClientes = (contadorActual())+1;
        System.out.println("\nCliente número "+this.canticadClientes+" conectado");
        ControladorServidor.escucharClientes(1);
        cliente = new HiloCliente(socket, this.canticadClientes, multicast);
        arregloClientes.add(cliente);

        
        cliente.obtenerFlujos();
        cliente.start();
    }
    
    /**
     * Permite conocer la cantidad de clientes
     * @return cantidad de clientes
     */
    public int getCantClientes()
    {
        return this.canticadClientes;
    }
    
    /**
     * Revisa que el cliente este conectado y
     * si no esta conectado lo remueve el arreglo de clientes
     */
    public int contadorActual()
    {
        int cant = this.canticadClientes;
        for (int i = 0; i<this.arregloClientes.size();i++)
        {
            if(arregloClientes.get(i).getConeccion())
            {
                arregloClientes.get(i).setIdCliente(cant);
            }
            if(!arregloClientes.get(i).getConeccion())
            {
                this.canticadClientes = this.canticadClientes-1;
                arregloClientes.remove(i);
            }
        }
        return cant;
    }
}