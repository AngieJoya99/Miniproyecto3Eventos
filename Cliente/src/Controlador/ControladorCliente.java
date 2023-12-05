package Controlador;

import java.io.IOException;

import Modelo.ConexionCliente;
import Modelo.MulticastCliente;
import Vista.GUICliente;

public class ControladorCliente 
{
    static GUICliente gui;
    static ConexionCliente conexionCliente;
    static MulticastCliente multicastCliente;

    public static void iniciar()
    {
        gui = new GUICliente();
        gui.establecerBotones(10);
        try 
        {
            clienteConectadoC();
            conexionCliente = new ConexionCliente(12345);
            conexionCliente.obtenerFlujos();
            conexionCliente.procesarConexion();
        } catch (IOException ex) {
            System.out.println("Error al inicial la conexion del cliente");
        }finally{
            conexionCliente.cerrarConexion();
        }
    }

    public static void clienteConectadoC()
    {
        gui.clienteConectado(1); //No se como obener el numero de cliente
    }

    public static void escucharMensaje(String mensaje)
    {
        //Aqui van las funciones que necesitan los mensajes
    }
}