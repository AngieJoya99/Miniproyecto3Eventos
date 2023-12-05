/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Controlador;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Modelo.Archivo;
import Modelo.ConexionServidor;
import Modelo.ExamenServidor;
import Modelo.HiloCliente;
import Modelo.Multicast;
import Vista.GUIServidor;

/**
 * Clase que enlaza la GUI y el backend
 */
public class ControladorServidor {
    static GUIServidor gui;
    static Archivo archivo;
    static ArrayList<ExamenServidor> examen;
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
        examen = new ArrayList<ExamenServidor>();
        conexionServidor = new ConexionServidor(12345);
        tiempoHoras = Integer.parseInt(gui.leerHoras());
        tiempoMin = Integer.parseInt(gui.leerMinutos());
        multicast = new Multicast();

    }

    public static Multicast getMulticast()
    {
        return multicast;
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
        String nombreExamen = gui.leerNombreExamen();
        int horas = Integer.parseInt(gui.leerHoras());
        int minutos = Integer.parseInt(gui.leerMinutos());
        String preguntasArchivo = archivo.getPreguntas();
        mensaje+="\n"+nombreExamen+"\n"+horas+"\n"+minutos+"\n"+preguntasArchivo;
        gui.agregarExamen(mensaje);
        gui.agregarNombreExamen(gui.leerNombreExamen());
        gui.agregarExamenLista(gui.leerNombreExamen());
        gui.agregarInforme("El examen no ha sido respondido. No es posible generar un informe");
        String preguntas = separarPreguntas(mensaje);
        gui.agregarVisualizar(preguntas);
        examen.add(new ExamenServidor(nombreExamen ,horas ,minutos , preguntasArchivo));
    }

    
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

    public static void mostrarVisualizar()
    {
        String texto = gui.cualVisualizar();
        gui.escribirVisualizar(texto);
    }

    public static void mostrarInforme()
    {
        String texto = gui.cualInforme();
        gui.escribirInforme(texto);
    }

    public static void limpiarVisualizar()
    {
        gui.escribirVisualizar("");   
    }

    public static void limpiarInforme()
    {
        gui.escribirInforme("");   
    }

    public static void cargarExamenIniciar()
    {
        if(gui.getExamenIniciar() != null)
        {
            String seleccion = gui.getExamenIniciar(), nombreEx, tiempo="";
            int cantPreg=0;
            for(int i=0; i<examen.size();i++)
            {
                nombreEx = examen.get(i).getNombre();
                if(nombreEx.trim().equals(seleccion.trim()))
                {
                    System.out.println("Encontrado");
                    cantPreg=examen.get(i).cantidadPreguntas();
                    tiempo = examen.get(i).getHoras()+" : "+examen.get(i).getMinutos();
                }
            }
            gui.setIniciarPreguntas("Cantidad de preguntas:     "+cantPreg);
            gui.setIniciarTiempo("Tiempo (hh:mm):     "+tiempo);
        }
    }

    public static void enviarExamen()
    {
        if(gui.getExamenIniciar() != null)
        {
            String seleccion = gui.getExamenIniciar(), enviar = "Examen\n", nombreEx="";
            for(int i=0; i<examen.size();i++)
            {
                nombreEx = examen.get(i).getNombre();
                if(nombreEx.trim().equals(seleccion.trim()))
                {
                    enviar+=examen.get(i).examenEnviar();
                }
            }
            multicast.enviarTextoMulti(enviar);
            System.out.println(enviar);
        }
    }

    
    
    
    public void verificarPregunta(String respuesta, int numPregunta, int examenIndice)
    {
        if(examen.get(examenIndice).getResCorrecta(numPregunta) == respuesta)
            examen.get(examenIndice).setCorrectas();
    }

    
       
}
