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
    public static void leerArchivo()
    {
        archivo = new Archivo(gui.leerNombreArchivo());  
    }

    public static void crearExamen()
    {
        //String mensaje = "Examen";
        String mensaje="";
        mensaje+="\n"+gui.leerNombreExamen();
        mensaje+="\n"+gui.leerHoras();
        mensaje+="\n"+gui.leerMinutos();
        mensaje+="\n"+archivo.getPreguntas();
        gui.agregarExamen(mensaje);
        gui.agregarNombreExamen(gui.leerNombreExamen());
        System.out.println(mensaje);
        //conexionServidor.enviarTextoMulti(mensaje);
        //System.out.println("Examen creado exitosamente");
    }

    public static void enviarExamen()
    {
        if(conexionServidor.getCantClientes()==3)
        {
            multicast.enviarTextoMulti(archivo.getPreguntas());
            System.out.println("Se enviaron las preguntas");
        }
        
    }
       
}
