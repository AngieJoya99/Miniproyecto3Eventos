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
    private static int cantClientes;
    

    /**
     * Constructor de la clase ControladorServidor
     */
    public static void iniciar()
    {
        gui = new GUIServidor();
        examen = new ArrayList<ExamenServidor>();
        conexionServidor = new ConexionServidor(12345);
        multicast = new Multicast();
        cantClientes =0;
    }

    /**
     * Funcion que retorna el multicast
     * @return multicast
     */
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

    /**
     * Recibe un texto como parametro y separa las prenguntas 
     * ingresandolas a datos
     * @param texto
     * @return
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

    /**
     * Permite visualizar en la gui el examen seleccionao
     */
    public static void mostrarVisualizar()
    {
        String texto = gui.cualVisualizar();
        gui.escribirVisualizar(texto);
    }

    /**
     * Permite mostrar el informe que se selecciono en la gui
     */
    public static void mostrarInforme()
    {
        String texto = gui.cualInforme();
        gui.escribirInforme(texto);
    }

    /**
     * Permite limpiar el campo visualizar
     */
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
            //System.out.println(enviar);
            gui.enableIniciar(false);
        }
    }
    
   public void procesarRespuesta(String respuesta)
    {
        String[] entradaCadena = respuesta.trim().split("\n");
         try
        {
            for(int i=0; i<examen.size(); i++)
            {
                if(examen.get(i).getNombre().equals(entradaCadena[3]))
                verificarPregunta(entradaCadena[3], Integer.parseInt(entradaCadena[1]), Integer.parseInt(entradaCadena[4]));            
            }

        }catch(NumberFormatException e)
        {
            System.out.println("Error al convertir cadena a número: " + e.getMessage());
        }
        
    }

    
    public void verificarPregunta(String respuesta, int numPregunta, int examenIndice)
    {
         examen.get(examenIndice).setPregRespondidas();
        if(examen.get(examenIndice).getResCorrecta(numPregunta) == respuesta)
            examen.get(examenIndice).setCorrectas();  
        else
        {
            examen.get(examenIndice).setIncorrectas();
        }
            
    }

    public void verificarExamenCompleto(int examenIndice)
    {
       // if(examen.get(enviarExamen))
    }
    public static void getHoras()
    {
        if(gui.getExamenIniciar() != null)
        {
            String seleccion = gui.getExamenIniciar(), nombreEx;
            for(int i=0; i<examen.size();i++)
            {
                nombreEx = examen.get(i).getNombre();
                if(nombreEx.trim().equals(seleccion.trim()))
                {
                    tiempoHoras = Integer.parseInt(examen.get(i).getHoras());
                }
            }
        }
        //return tiempoHoras;
    }

    public static void getMin()
    {
        if(gui.getExamenIniciar() != null)
        {
            String seleccion = gui.getExamenIniciar(), nombreEx;
            for(int i=0; i<examen.size();i++)
            {
                nombreEx = examen.get(i).getNombre();
                if(nombreEx.trim().equals(seleccion.trim()))
                {
                    tiempoMin = Integer.parseInt(examen.get(i).getMinutos());
                }
            }
        }
        //return tiempoMin;
    }

    public static void tiempoRestanteHoras() 
    {
        getHoras();
        gui.setHorasRestantes(Integer.toString(tiempoHoras));
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
        getMin();
        getHoras();
        gui.setHorasRestantes(Integer.toString(tiempoHoras));
        gui.setMinRestantes(Integer.toString(tiempoMin));
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
                     if(tiempoHoras>0)
                    {
                        gui.setHorasRestantes(Integer.toString(tiempoHoras-1));
                        tiempoMin=59;
                        gui.setMinRestantes(Integer.toString(59));
                    }
                    if(tiempoHoras==0)
                    {
                        gui.setHorasRestantes(Integer.toString(0));
                        tiempoMin=59;
                        gui.setMinRestantes(Integer.toString(59));
                    }
                    if(tiempoMin==0)
                    {
                        this.cancel();
                    }
                }   
            }
        };
        cuentaAtras.scheduleAtFixedRate(tarea, 1000*60, 1000*60);
    }

    public static void escucharClientes(int num)
    {
        cantClientes+=num;
        gui.cambiarIconos(cantClientes);
        
    }

    public static void enviarMulti(String texto)
    {
        multicast.enviarTextoMulti(texto);
    }
    
       
}
