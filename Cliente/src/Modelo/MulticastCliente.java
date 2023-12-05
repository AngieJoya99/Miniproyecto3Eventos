package Modelo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
            //Mismo puerto del servidor
            socketMulticast = new MulticastSocket(10000); //Se debe cambiar, para que el puerto llegue como parametro?
            grupoVincular = InetAddress.getByName("231.0.0.1");
            socketMulticast.joinGroup(grupoVincular); //Todo lo que se suba por este grupo lo va a escuchar el cliente
            run();
        } catch (IOException ex) 
        {
            System.out.println("Error al crear el multicast del cliente");
        } 
    }

    @Override
    public void run()
    {
        byte [] mensajeRecibir = new byte[300];
        DatagramPacket dataCliente = new DatagramPacket(mensajeRecibir, mensajeRecibir.length);
        String salida;
        while(true)
        {
            try 
            {
                socketMulticast.receive(dataCliente); //El mensaje que llega
                salida = new String(dataCliente.getData()); //Aqui tengo el dato que llega
                ControladorCliente.establecerPreguntas(salida); //Cambiar, esto va en el controlador
            } catch (IOException ex) 
            {
                System.out.println("Problema al recir el mensaje");
                cerrarMulti();
                break;
            } 
        }
    }
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
