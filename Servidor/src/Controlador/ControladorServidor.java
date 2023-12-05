/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Controlador;

import java.util.Timer;
import java.util.TimerTask;

import Modelo.Archivo;
import Modelo.ConexionServidor;
import Modelo.HiloCliente;
import Modelo.Multicast;
import Vista.GUIServidor;

/**
 * Clase que enlaza la GUI y el backend
 */
public class ControladorServidor {
    static GUIServidor gui;
    static Archivo archivo;
    static ConexionServidor conexionServidor;
    static HiloCliente hiloCliente;
    static Multicast multicast;
    private static int tiempoHoras;
    private static int tiempoMin;
    

    /**
     * Constructor de la clase ControladorServidor
     */
    public static void iniciar()
    {
        gui = new GUIServidor();
        conexionServidor = new ConexionServidor(12345);
        tiempoHoras = Integer.parseInt(gui.leerHoras());
        tiempoMin = Integer.parseInt(gui.leerMinutos());

    }

    /**
     * Método que lee el archivo del nombre indicado por la GUI
     */
    public static void leerArchivo()
    {
        archivo = new Archivo(gui.leerNombreArchivo());  
    }

    /**
     * Método que lee información respecto a un examen de la GUI
     * Lo almacena en una cadena de texto
     * e inicialzia valores de la GUI al respecto
     */
    public static void crearExamen()
    {
        String mensaje="";
        mensaje+="\n"+gui.leerNombreExamen();
        mensaje+="\n"+gui.leerHoras();
        mensaje+="\n"+gui.leerMinutos();
        mensaje+="\n"+archivo.getPreguntas();
        gui.agregarExamen(mensaje);
        gui.agregarNombreExamen(gui.leerNombreExamen());
        gui.agregarExamenLista(gui.leerNombreExamen());
        gui.agregarInforme("El examen no ha sido respondido. No es posible generar un informe");
        String preguntas = separarPreguntas(mensaje);
        gui.agregarVisualizar(preguntas);
    }

    /**
     * Método que separa una cadena de texto que recibe como parámetro
     * De acuerdo a delimitadores y los organiza 
     * @param texto Cadena de texto a separar
     * @return Texto organizado
     */
    public static String separarPreguntas(String texto)
    {
        String mensaje="";
        String[] datos, preguntas; 
        datos = texto.trim().split("\n");
        mensaje+="Nombre del examen: "+datos[0]+"\n"+
        "Duración del examen: "+datos[1]+" horas "+datos[2]+" minutos\n\n";
        for (int i=3; i<datos.length; i++)
        {
            preguntas = datos[i].split("-");
            mensaje+=preguntas[0]+"\n";    
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
