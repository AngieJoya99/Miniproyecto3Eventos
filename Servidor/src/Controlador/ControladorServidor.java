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
        conexionServidor = new ConexionServidor(12345);
    }

    /**
     * Método que lee el archivo del nombre indicado
     */
    //No se cómo hacer que la GUI sepa que archivos existen
    public static void leerArchivo()
    {
        String nombre = "Preguntas.txt";
        archivo = new Archivo(nombre);        
    }

    //¿Cómo envia algo por multicast? D:
    public static void enviarExamen()
    {
        String mensaje = "Examen\n";
        mensaje+="\n"+gui.leerNombreExamen();
        mensaje+="\n"+gui.leerHoras();
        mensaje+="\n"+gui.leerMinutos();
        mensaje+="\n"+gui.leerSegundos();
        mensaje+="\n"+archivo.getPreguntas();
        

    }
    
}
