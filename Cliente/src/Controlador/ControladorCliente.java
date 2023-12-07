/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Controlador;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Modelo.ConexionCliente;
import Modelo.MulticastCliente;
import Vista.GUICliente;
import Modelo.Examen;
import Controlador.ControladorCliente;

public class ControladorCliente 
{
    static GUICliente gui;
    static ConexionCliente conexionCliente;
    static MulticastCliente multicastCliente;
    static Examen examen;
    private static int tiempoMin;
    private static int tiempoSec;
    private static int totalSegundos;
    private static int segundosR;

    public static void iniciar()
    {
        gui = new GUICliente();
        try 
        {
            //clienteConectadoC();
            conexionCliente = new ConexionCliente(12345);
            System.out.println("Cliente conectado\n");
            conexionCliente.obtenerFlujos();
            System.out.println("Flujos obtenidos\n");
            conexionCliente.procesarConexion();
            System.out.println("Conexion procesada\n");
        } catch (IOException ex) {
            System.out.println("Error al inicial la conexion del cliente");
        }finally{
            conexionCliente.cerrarConexion();
        }
    }
    
    /**
     * Recibe el mensaje enviado por el multicast y lo envia a la 
     * funcion correspondiente
     * @param infoMulti Mensaje del Multicast
     */
    public static void informacioEntrada(String infoMulti)
    {
        String[] entradaCadena = infoMulti.trim().split("\n");
        System.out.println("Ha entrado a la funcion informacionEntrada");
        System.out.println("ESTO ES LO QUE ENTRA DEL MULTICAST: "+infoMulti);
        System.out.println(entradaCadena[0]);
        //establecerPreguntas(infoMulti);
        if (entradaCadena[0].equals("Examen"))
        {
            System.out.println("entro a if examen");
            establecerPreguntas(infoMulti);
        }
        if (entradaCadena[0].equals("Informe"))
        {
            
            System.out.println("Entro al if del informe");
            crearInforme(infoMulti);
            System.out.println("se ha creado el informe enviado desde Servidor");
        }
        if (entradaCadena[0].equals("Bloquear"))
        {
            System.out.println("Entre al if de bloquear");
            System.out.println(entradaCadena[0]);
            System.out.println(entradaCadena[1]);
            String numPregunta = entradaCadena[1];
            gui.bloquearPregunta(numPregunta, true);
        }
        if (entradaCadena[0].equals("Desbloquear"))
        {
            System.out.println("Entre al if de desbloquear");
            System.out.println(entradaCadena[0]);
            System.out.println(entradaCadena[1]);
            String numPregunta = entradaCadena[1];
            gui.bloquearPregunta(numPregunta, false);
        }
        
    }

    /**
     * Recibe un informe y lo envia a la clase examen
     */
    public static void crearInforme(String informe)
    {
        String[] entradaCadena = informe.trim().split("\n");
        informe = entradaCadena[0] +"\n"+ entradaCadena[1] +"\n"+ entradaCadena[2] +"\n"+  entradaCadena[3] +"\n"+  entradaCadena[4];
        examen.setInforme(informe);
    }

    /**
     * Obtiene el informe que se encuentra en el examen
     * y lo envia a la gui
     */
    public static void obtenerInforme()
    {
        gui.areaInforme(examen.getInforme());
    }


    /**
     * Recibe un String, lo divide por cada linea que contenga y establece un objeto de la clase Examen
     * que a su vez establece un objeto de la clase pregunta
     * @param examenString
     */
    public static  void establecerPreguntas(String examenString)
    {  
            
            System.out.println("Aqui comienza a divir string");
            String[] cadena = examenString.trim().split("\n");
            System.out.println("Lineas examen: "+Integer.toString(cadena.length));
            System.out.println("String en la posicion 1 arreglo: "+cadena[1]);
            System.out.println("String en la posicion 2 arreglo: "+cadena[2]);
            System.out.println("String en la posicion 3 arreglo: "+cadena[3]);
            
            int horas = Integer.parseInt(cadena[2]);
            int minutos = Integer.parseInt(cadena[3]);

            if (cadena.length >= 4) 
            {
                try{
                    examen = new Examen(cadena[1], horas, minutos);
                    tiempoMin = examen.getHoras();
                    tiempoSec = examen.getMin();
                    for(int i=4; i<cadena.length; i++)
                    {
                        examen.addPregunta(cadena[i]);
                        //System.out.println("Se adicionó correctamente la pregunta " + Integer.toString(i-4));
                        //System.out.println("Arreglo: "+ examen.getNumPreg());
                        
                    }
                }catch(NumberFormatException e)
                {
                    System.out.println("Error al convertir cadena a número: " + e.getMessage());
                }
            } else 
            {
                System.out.println("El examen no cuenta con las lineas necesarias para adicionar las preguntas");
            }

            establecerBotonesPreg(examen.getNumPreg());
            tiempoRestanteHoras();
            //gui.bloquearPestaña(0,true);
            //tiempoRestanteMinutos();
            
    }

    /**
     * Recibe el numero de pregunta que debe bloquear y lo envia al servidor
     * @param pregunta Pregunta a bloquear
     */
    public static void enviarBloqueada(String pregunta, boolean estado)
    {
        if (estado == true)
        {
            conexionCliente.enviarDatos("Bloquear\n"+pregunta);
            System.out.println("Se envio el mensaje bloquear");
        }
        else
        {
            conexionCliente.enviarDatos("Desbloquear\n"+pregunta);
            System.out.println("Se envio el mensaje de desbloquear");
        }
    }


    public static void mostrarPregunta(int numPregunta)
    {  
        gui.setTextAreaPreg(examen.getPreguntas(numPregunta-1));
        gui.labelNumeroPregunta(Integer.toString(numPregunta));
        gui.setTextPestPreg(examen.getPreguntas(numPregunta-1), examen.getOpciones(numPregunta-1));
    }

    /**
     * Manda la respuesta al servidor
     * @param respuesta
     */
    public static void responderPregunta(String respuesta)
    {
        String mensaje = "RESPONDIDA\n"+gui.getNumPreg()+"\n"+Thread.currentThread().getName()+"\n"+respuesta+"\n"+examen.getNombre();
        System.out.println("Antes de enviar datos");
        conexionCliente.enviarDatos(mensaje);
        System.out.println("Oprime responder pregunta");
        gui.setTextAreaPreg("Se ha contestado la pregunta!");
        gui.bResponderSetEnabled(false);
    }

    /**
     * Funcion que permite estableces los botones, depentiendo
     * de la cantidad de preguntas
     * @param num Cantidad botones
     */
    public static void establecerBotonesPreg(int num)
    {
        gui.establecerBotones(num);
    }

    /**
     * Establece el tiempo restante del examen
     */
    /** 
    public static void establecerTiempo()
    {
        
        gui.setTiempoRestante(examen.getDuracion());

    }

    */

    public static void getTiempo()
    {
        totalSegundos = (tiempoMin*60)+tiempoSec;
        segundosR = totalSegundos; 
    }

    public static void tiempoRestanteHoras() 
    {
        //getMin();
        //getSec();
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
                    gui.bloquearPestaña(0,false);
                    gui.bloquearPestaña(1,false);
                    gui.bloquearPestaña(2,true);

                }   
            }
        };
        cuentaAtras.scheduleAtFixedRate(tarea, 1000, 1000);
    }

   /* public static void tiempoRestanteMinutos(int tiempoHoras, int tiempoMin) 
    {
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
    }*/
    
    
  

}