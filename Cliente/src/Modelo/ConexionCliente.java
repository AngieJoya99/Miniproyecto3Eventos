package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionCliente 
{
    MulticastCliente miMulticastCliente;

    Socket cliente;
    ObjectOutputStream salida;
    ObjectInputStream entrada;
   
    public ConexionCliente(int puerto) throws IOException
    {
        System.out.println("\nConcectando por el puerto "+puerto+". Por favor espere");
        cliente = new Socket("127.0.0.1", 12345);
        System.out.println("Conectado a: "+cliente.getInetAddress());
        miMulticastCliente = new MulticastCliente(this);
    }
    
    public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        System.out.println("Se obtuvieron los flujos E/S");
    }

    public void procesarConexion() throws IOException
    {
        String mensaje = "";
        //campoIntroducir.setEnabled(true);
        do
        {
            try 
            {
                mensaje = (String) entrada.readObject();
                //mostrarMensaje("\n"+mensaje);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("error tipo de dato incorrecto");
            }
            
        }while(!mensaje.equals("SERVIDOR>>> TERMINAR"));
    }
    
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
}
