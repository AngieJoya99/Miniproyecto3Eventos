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
    private static int tiempoMin;
    private static int tiempoSec;
    private static int totalSegundos;
    private static int segundosR;
    //private static int 
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
     * Permite visualizar en la gui el examen seleccionado
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
     * Permite limpiar el campo visualizar examen
     */
    public static void limpiarVisualizar()
    {
        gui.escribirVisualizar("");   
    }

    public static void limpiarInforme()
    {
        gui.escribirInforme("");   
    }

    /**
     * Inicia el examen, tomando los valores ingresados
     */
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

    /**
     * Permite enviar el examen a los estudiantes por medio
     * del multicast
     */
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
    
    /**
     * Recibe la respuesta del cliente y obtiene el nombre del examen 
     * y verifica a que examen pertenece
     * @param respuesta
     */
   public static void procesarRespuesta(String respuesta)
    {
        System.out.println("Se entro a procesor respuesta desde CS");
        String[] entradaCadena = respuesta.trim().split("\n");
        String[] opcion = entradaCadena[3].trim().split(" ");
        System.out.println(opcion[1]);
        System.out.println(entradaCadena[1]);
        System.out.println(entradaCadena[3]);
        System.out.println(entradaCadena[4]);


        try
        {
            System.out.println("Se entro al try de procesar respuesta");
            for(int i=0; i<examen.size(); i++)
            {
                if(examen.get(i).getNombre().equals(entradaCadena[4]))
                {
                    System.out.println("Se entro al if de procesar respuesta");
                    verificarPregunta(opcion[1], Integer.parseInt(entradaCadena[1]), i);
                    System.out.println("Se ha verificado correctamente al pregunta");
                }            
            }

        }catch(NumberFormatException e)
        {
            System.out.println("Error al convertir cadena a número: " + e.getMessage());
        }
        
    }

    
    public static void verificarPregunta(String respuesta, int numPregunta, int examenIndice)
    {
        System.out.println("Se entro a verificar pregunta");
        examen.get(examenIndice).setPregRespondidas();
        System.out.println("Respuesta correcta: "+examen.get(examenIndice).getResCorrecta(numPregunta));
        //System.out.println(examen.get);
        System.out.println("Respuesta que evio_ "+respuesta);
        System.out.println("Indice: "+examenIndice);
        System.out.println("Numero de pregunta: "+numPregunta);
        
        try{
            if(examen.get(examenIndice).getResCorrecta(numPregunta).equals(respuesta))
            {
                System.out.println("La respuesta correcta es: "+(examen.get(examenIndice).getResCorrecta(numPregunta)));
                System.out.println("Se entro al if de respuesta correcta");
                examen.get(examenIndice).setCorrectas();  
                System.out.println("La pregunta "+Integer.toString(numPregunta)+"es correcta");
            }
            else
            {
                System.out.println("Se entro al if de respuesta incorrecta");
                examen.get(examenIndice).setIncorrectas();
                System.out.println("La pregunta "+Integer.toString(numPregunta)+"es incorrecta");
            }
        }catch(NullPointerException e)
        {
            System.out.println("fuera del arreglo");
        }
        System.out.println("Ahora se verifica examen completo");
        verificarExamenCompleto(examenIndice);
            
    }

    public static void verificarExamenCompleto(int examenIndice)
    {
        String informe = "Informe\n'"+ examen.get(examenIndice).getNombre() +"' :\n";
       if(examen.get(examenIndice).getPregRespondida() ==  examen.get(examenIndice).cantidadPreguntas())
       {
            String correctas = "Numero de respuestas correctas: "+ Integer.toString(examen.get(examenIndice).getCorrectas())+"\n";
            String incorrectas = "Numero de respuestas incorrectas: "+ Integer.toString(examen.get(examenIndice).getIncorrectas())+"\n";
            String puntaje = "El puntaje obtenido es: "+Double.toString(calcularPuntaje(examenIndice))+"\n";
            informe += correctas + incorrectas + puntaje;
            
            System.out.println("El informe que se va a enviar es: "+informe);
            System.out.println("Entro a if examen respondido completamente");
            //hiloCliente.enviarTexto(informe);
            enviarMulti(informe);
            System.out.println("Se ha enviado el informe");
       }
       else
       {
            System.out.println("El examen no se ha contestado completamente");
        
       }
    

    }

    /**
     * Obtiene la cantidad de preguntas correstas y las preguntas total 
     * y calcula la n
at
     */
    public static double calcularPuntaje(int examenIndice)
    {
        double correctas =(double) examen.get(examenIndice).getCorrectas();
        double totalPreg =(double) examen.get(examenIndice).cantidadPreguntas();
        System.out.println("Total preguntas"+totalPreg);
        System.out.println("Estas son las preguntas correctas: "+correctas);
        System.out.println("Estas son las preguntas acumuladas: "+cantClientes);
        double puntaje = (5/totalPreg)*correctas;
        double intento = 5/totalPreg;
        System.out.println(puntaje);
        System.out.println("5/totalPreg es: "+intento);
        System.out.println(intento*correctas);

        return puntaje;

    }

    /**
     * Obtiene la cantidad de horas del examen
     */
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
                    tiempoMin = Integer.parseInt(examen.get(i).getHoras());
                }
            }
        }
        //return tiempoHoras;
    }

    /**
     * Permite obtener los minutos que va a durar el examen
     */
    public static void getSec()
    {
        if(gui.getExamenIniciar() != null)
        {
            String seleccion = gui.getExamenIniciar(), nombreEx;
            for(int i=0; i<examen.size();i++)
            {
                nombreEx = examen.get(i).getNombre();
                if(nombreEx.trim().equals(seleccion.trim()))
                {
                    tiempoSec = Integer.parseInt(examen.get(i).getMinutos());
                }
            }
        }
        //return tiempoMin;
    }

    public static void getTiempo()
    {
        totalSegundos = (tiempoMin*60)+tiempoSec;
        segundosR = totalSegundos; 
    }

    /**
     * Determina las horas restantes del examen
     */
    public static void tiempoRestanteHoras() 
    {
        getMin();
        getSec();
        getTiempo();
        gui.setHorasRestantes(Integer.toString(tiempoMin));
        gui.setMinRestantes(Integer.toString(tiempoSec));
        Timer cuentaAtras = new Timer();
        TimerTask tarea = new TimerTask()
        {
            @Override
            public void run()
            { 
                segundosR = segundosR -1;
                gui.setHorasRestantes(Integer.toString(segundosR/60));
                gui.setMinRestantes(Integer.toString(segundosR%60));
                if (segundosR == 0)
                {
                    this.cancel();
                }   
            }
        };
        cuentaAtras.scheduleAtFixedRate(tarea, 1000, 1000);
    }
    
    public static void escucharClientes(int num)
    {
        cantClientes+=num;
        gui.cambiarIconos(cantClientes);
        
    }

    /**
     * Envia por el multicast un texto que recibe como parametro
     * @param texto
     */
    public static void enviarMulti(String texto)
    {
        multicast.enviarTextoMulti(texto);
    }
    
       
}
