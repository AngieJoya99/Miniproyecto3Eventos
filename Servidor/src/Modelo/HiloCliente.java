/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */

package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Clase que procesa la conexión con el usuario, procesa los flujos
 * y cierra la conexión cuando esta termina.
 */
public class HiloCliente extends Thread
{
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    Socket socket;
    Multicast multicast;
    
    int idCliente;

    /**
     * Constructor de la clase HiloServidor
     * @param socket
     * @param id
     */
    public HiloCliente(Socket socket, int idCliente, Multicast multicast)
    {
        this.socket = socket;
        this.idCliente = idCliente;
        this.multicast = multicast;
    }

    @Override
    /**
     * Métdo que corre el hilo
     */
    public void run() 
    {
        try
        {
            procesarConexion();
        }
        finally
        {
            cerrarConexion();
        }
        
    }

    public void obtenerFlujos()
    {
        try {
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
            System.out.println("Se obtuvieron los flujos correctamente");
        } catch (IOException e) {
            System.out.println("Error al obtener los flujos");
        }
    }

    public void procesarConexion()
    {
        String mensaje = "Conexion exitosa";
        enviarTexto(mensaje);
        do
        {
            //No sé que debe ir aquí :(
        }while(!mensaje.equals("cerrar"));
    }

    public void enviarTexto (String texto)
    {
        try {
            salida.writeObject(texto);
            salida.flush();
            System.out.println("La información se envió correctamente");
        } catch (IOException e) {
            System.out.println("Error al enviar La información");
        }
    }

    public void enviarTextoMulti (String texto)
    {
        multicast.enviarTextoMulti(texto);   
    }

    /**
     * Cierra los flujos de entrada y salida y el socket
     */
    public void cerrarConexion()
    {
        try {
            entrada.close();
            salida.close();
            socket.close();
            System.out.println("La conexión con el estudiante "+idCliente+" ha terminado");
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexion");
        }
    }

    public int getIdCliente()
    {
        return idCliente;
    }
}
