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
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Clase que procesa la conexión con el usuario, procesa los flujos
 * y cierra la conexión cuando esta termina.
 */
public class HiloCliente extends Thread
{
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    ArrayList<String> preguntas,cliente,respuesta;
    Socket socket;
    
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
        ArrayList <String> preguntas = new ArrayList<>();
        ArrayList <String> cliente = new ArrayList<>();
        ArrayList <String> respuesta = new ArrayList<>();
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
        catch(IOException e)
        {
            
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

    public void procesarConexion() throws IOException
    {
        String mensaje = "Conexion exitosa";
        enviarTexto(mensaje);
        do
        {
            try
            {
             mensaje = (String) entrada.readObject(); 
             if(mensaje.contains("RESPONDIDA"))
             {
                System.out.println(mensaje);
                String[] respuesta = mensaje.trim().split("||");
                

             }    
            }
            catch(ClassNotFoundException e)
            {

            }catch(SocketException ex)
            {
                
            }
            
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
