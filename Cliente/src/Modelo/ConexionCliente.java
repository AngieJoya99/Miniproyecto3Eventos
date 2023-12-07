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
import Controlador.ControladorCliente;

/**
 * Clase que permite establecer la conexion con el servidor
 */
public class ConexionCliente 
{
    MulticastCliente miMulticastCliente;

    Socket cliente;
    ObjectOutputStream salida;
    ObjectInputStream entrada;
    String nombreCliente;
   
    /**
     * Constructor de la clase conexion cliente
     * @param puerto
     * @throws IOException
     */
    public ConexionCliente(int puerto) throws IOException
    {
        System.out.println("\nConcectando por el puerto "+puerto+". Por favor espere");
        cliente = new Socket("127.0.0.1", 12345);
        System.out.println("Conectado a: "+cliente.getInetAddress());
        miMulticastCliente = new MulticastCliente(this);
    }
    
    /**
     * Metodo que permite obtener los flujos
     * @throws IOException
     */
    public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        System.out.println("Se obtuvieron los flujos E/S");
    }

    /**
     * Metodo que permite procesar y obtener mensajes desde el servidor
     * @throws IOException
     */
    public void procesarConexion() throws IOException
    {
        String mensaje = "";
        //campoIntroducir.setEnabled(true);
        do
        {
            try 
            {
                mensaje = (String) entrada.readObject();
                System.out.println("Desde clase Controlador Cliente se recibe el siguiente mensaje del servidor: "+mensaje);
                ControladorCliente.informacioEntrada(mensaje);
                //mostrarMensaje("\n"+mensaje);
                System.out.println(mensaje + "no pasa nada");
                //ControladorCliente.establecerPreguntas(mensaje);
                //ControladorCliente.mostrarPregunta(2);
                if(mensaje.contains("CLIENTE"))
                {
                    System.out.println("mensaje recibido" + mensaje );
                    this.nombreCliente=mensaje;
                }
                
            } catch (ClassNotFoundException ex) {
                System.out.println("error tipo de dato incorrecto");
            }
            
        }while(cliente.isConnected());
    }
    
    /**
     * Metodo que cierra la conexion con el servidor
     */
    public void cerrarConexion()
    {
        System.out.println("cerrando conexion...");
        try {
            salida.close();
            entrada.close();
            cliente.close();
            //campoIntroducir.setEnabled(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite enviar mensajes hacia el servidor
     * @param mensaje mensaje que se quiere enviar al servidor
     */
    public void enviarDatos(String mensaje)
    {
        try 
        {
            
            salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            System.out.println("Error al mandar daatos al servidor");
        }
        
    }

    /**
     * Proporciona el nombre del cliente
     * @return nombre del cliente
     */
    public String getNombreCliente()
    {
        return this.nombreCliente;
    }
}