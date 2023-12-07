/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */

package Modelo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

import Controlador.ControladorCliente;

/**
 * Clase que escucha el multicast
 */
public class MulticastCliente extends Thread
{
    MulticastSocket socketMulticast;
    InetAddress grupoVincular;

    ConexionCliente miCliente; 
    
    /**
     * Constructor de la clase
     * @param gui
     */
    public MulticastCliente(ConexionCliente cliente)
    {
        this.miCliente = cliente;
        try 
        {
            socketMulticast = new MulticastSocket(10000); //Se debe cambiar, para que el puerto llegue como parametro?
            grupoVincular = InetAddress.getByName("231.0.0.1");
            socketMulticast.joinGroup(grupoVincular); //Todo lo que se suba por este grupo lo va a escuchar el cliente
            start();
        } catch (IOException ex) 
        {
            System.out.println("Error al crear el multicast del cliente");
        } 
    }

    /**
     * Metodo run del multicast cliente
     */
    @Override
    public void run()
    {
        byte [] mensajeRecibir = new byte[3000];
        DatagramPacket dataCliente = new DatagramPacket(mensajeRecibir, mensajeRecibir.length);
        String salida = "";
        while(true)
        {
            try 
            {
                socketMulticast.receive(dataCliente); //El mensaje que llega
                salida = new String(dataCliente.getData()); //Aqui tengo el dato que llega
                System.out.println("llega del servidor" + dataCliente.getData());
                ControladorCliente.informacioEntrada(salida); 
                Arrays.fill(mensajeRecibir, (byte) 0);

                System.out.println("Llego el mensaje");
            } catch (IOException ex) 
            {
                System.out.println("Problema al recir el mensaje");
                cerrarMulti();
                break;
            } 
        }
    }

    /**
     * Metodo que cierra el multicast
     */
    public void cerrarMulti()
    {
        try 
        {
            socketMulticast.leaveGroup(grupoVincular);
            socketMulticast.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el socket");
        }
    }
}
