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

import Controlador.ControladorServidor;

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
    Multicast multicast;
    boolean coneccion;

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
        this.coneccion=true;
    }

    /**
     * Métdo que corre el hilo
     */
    @Override
    public void run() 
    {
        try
        {
            procesarConexion();
        }
        catch(IOException e)
        {
           this. coneccion=false;
        }
        finally
        {
            cerrarConexion();
        }
        
    }

    /**
     * Crea los flujos de entrada y de salida
     */
    public void obtenerFlujos()
    {
        try 
        {
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
            System.out.println("Se obtuvieron los flujos correctamente");
        } catch (IOException e) {
            System.out.println("Error al obtener los flujos");
        }
    }

    /**
     * Permite enviar mensajes a un cliente 
     * en especifico
     * @param texto
     */
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
     * Cierra los flujos de entrada, salida y el socket
     */
    public void cerrarConexion()
    {
        try {
            entrada.close();
            salida.close();
            socket.close();
            System.out.println("La conexión con el estudiante "+idCliente+" ha terminado");
            ControladorServidor.escucharClientes(-1);
            this.coneccion=false;
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexion");
        }
    }

    /**
     * Obtiene la identidicacion del cliente
     * @return Retorna la identificacion del cliente
     */
    public int getIdCliente()
    {
        return this.idCliente;
    }

    /**
     * Obtiene la identificacion del cliente
     * @param id
     */
    public void setIdCliente(int id)
    {
        this.idCliente = id;
    }

    /**
     * Retorna si el cliente esta conectado
     * @return Retorna la conexion
     */
    public boolean getConeccion()
    {
        return this.coneccion;
    }

    /**
     * Metodo que permite procesar la conexion
     * @throws IOException
     */
    public void procesarConexion() throws IOException
    {
        String mensaje = "Conexion exitosa";
        do
        {
            try
            {
             mensaje = (String) entrada.readObject(); 
             if(mensaje.contains("RESPONDIDA"))
             {
                System.out.println(mensaje);
                System.out.println("Corta el texto");
                System.out.println("Add a preguntas Hilo cliente");
                System.out.println("Add a clientes Hilo cliente");
                System.out.println("Add a respuestas Hilo cliente");
                ControladorServidor.procesarRespuesta(mensaje);
                System.out.println("Se proceso la respuesta desde el hilo cliente");
             }

             if(mensaje.contains("Bloquear"))
             {
                System.out.println("Bloquear pregunta");
                String[] texto = mensaje.trim().split("\n");
                System.out.println(texto[1]);
                ControladorServidor.enviarMulti("Bloquear\n"+texto[1]+"\n");
                System.out.println("Recibi bloqueo");
             }
             
             if(mensaje.contains("Desbloquear"))
             {
                System.out.println("Desbloquear pregunta");
                String[] texto = mensaje.trim().split("\n");
                System.out.println(texto[1]);
                ControladorServidor.enviarMulti("Desbloquear\n"+texto[1]);
                System.out.println("Recibi desbloqueo");
             }

            }
            catch(ClassNotFoundException e)
            {
                break;
            }catch(SocketException ex)
            {
                System.out.println("El cliente "+Integer.toString(getIdCliente())+" se fue");
                this.coneccion = false;
                break;
            }
            
        }while(socket.isConnected());
    }
}
