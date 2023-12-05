package Controlador;

import java.util.Timer;
import java.util.TimerTask;

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
    private static int tiempoHoras = Integer.parseInt(gui.leerHoras());
    private static int tiempoMin = Integer.parseInt(gui.leerMinutos());
    

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
        gui.agregarExamenLista(gui.leerNombreExamen());
        separarPreguntas(mensaje);
        gui.agregarInforme("El examen no ha sido respondido. No es posible generar un informe");
        gui.agregarVisualizar(separarPreguntas(mensaje));
    }

    public static String separarPreguntas(String texto)
    {
        String mensaje="";
        String[] datos, preguntas; 
        datos = texto.trim().split("\n");
        mensaje+="Nombre del examen: "+datos[0]+"\n"+
        "Duración del examen: "+datos[1]+"horas "+datos[2]+" minutos\n\n";
        for (int i=3; i<=datos.length; i++)
        {
            preguntas = datos[i].split("\n");
            mensaje+=preguntas[0];
        }
        return mensaje;
    }

    public static void enviarExamen()
    {
        if(conexionServidor.getCantClientes()==3)
        {
            multicast.enviarTextoMulti(archivo.getPreguntas());
            System.out.println("Se enviaron las preguntas");
        }
        
    }

    public static void tiempoRestanteHoras() 
    {
        Timer cuentaAtras = new Timer();
        TimerTask tarea = new TimerTask()
        {
            @Override
            public void run()
            { 
                tiempoHoras = tiempoHoras -1;
                gui.setHorasRestantes(Integer.toString(tiempoHoras));
                if (tiempoHoras == 0)
                {
                    this.cancel();
                }   
            }
        };
        cuentaAtras.scheduleAtFixedRate(tarea, 1000*3600, 1000*3600);
    }

    public static void tiempoRestanteMinutos() 
    {
        Timer cuentaAtras = new Timer();
        TimerTask tarea = new TimerTask()
        {
            @Override
            public void run()
            { 
                tiempoMin = tiempoMin -1;
                gui.setMinRestantes(Integer.toString(tiempoMin));
                if (tiempoMin == 0)
                {
                    this.cancel();
                }   
            }
        };
        cuentaAtras.scheduleAtFixedRate(tarea, 1000*60, 1000*60);
    }
       
}
