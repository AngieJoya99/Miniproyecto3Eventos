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
import java.util.ArrayList;

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

    /**
     * Envía a todos los usuarios conectados al servidor un examen en forma
     * de cadena de bytes que recibe como parámetro
     * @param cadena Examen a enviar
     */
    public void enviarExamen (byte[] cadena)
    {
        
        Examen examen = new Examen("examen1", 2, 4,3);
        ArrayList datos = new ArrayList<String>();
        datos.add("1");
        datos.add("2");
        datos.add("3");
        byte[] cadena2 = new byte[datos.size()];
        datos.
        for (int i=0; i<datos.size(); i++)
        {
            cadena2[i] = ((String) datos.get(i)).getBytes();
        }

        try {
            
            datagrama.setData(cadena);
            datagrama.setLength(cadena.length);
            socketMulticast.send(datagrama);
        } catch (IOException e) {
            System.out.println("Error al enviar información a todos los usuarios");
        }
    }
}
