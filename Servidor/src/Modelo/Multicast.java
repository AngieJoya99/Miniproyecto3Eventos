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
import java.net.UnknownHostException;

/**
 * Clase que permite enviar información a todos los 
 * clientes conectados al servidor
 */
public class Multicast 
{
    private MulticastSocket socketMulticast;
    private DatagramPacket datagrama;
    private byte [] dato;
    private InetAddress grupo;

    /**
     * Constructor de la clase Multicast
     */
    public Multicast()
    {
        try {
            socketMulticast = new MulticastSocket();
            dato = new byte[0];
            //No es localhost
            grupo = InetAddress.getByName("231.0.0.1");
            datagrama =  new DatagramPacket(dato, 0, grupo, 10000);
        } catch (UnknownHostException e) {
            System.out.println("Error al conectar con host");
        } catch (IOException e) {
            System.out.println("Error al crear socket multicast");
        }
    }

    /**
     * Envía a todos los usuarios conectados al servidor un mensaje
     * que recibe como parámetro
     * @param mensaje Texto a enviar
     * @return True si el mensaje fue enviado correctamente.
     * False si el mensaje no pudo ser enviado
     */
    public boolean enviarTextoMulti (String mensaje)
    {
        if (mensaje.isEmpty())
        {
            System.out.println("El mensaje es vacío");
            return false;
        }

        try {
            byte [] cadena = mensaje.getBytes();
            datagrama.setData(cadena);
            datagrama.setLength(cadena.length);
            socketMulticast.send(datagrama);
            return true;
        } catch (IOException e) {
            System.out.println("Error al enviar información a todos los usuarios");
            return false;
        }
    }
}