package Controlador;

import Modelo.Archivo;
import Modelo.ConexionServidor;
import Modelo.HiloCliente;
import Modelo.Multicast;
import Vista.GUIServidor;

public class ControladorServidor {
    static GUIServidor gui;
    static Archivo archivo;
    static ConexionServidor conexionServidor;
    static HiloCliente hiloCliente;
    static Multicast multicast;

    public static void iniciar()
    {
        gui = new GUIServidor();
    }

    public static void crearArchivo()
    {
        
    }
    
}
