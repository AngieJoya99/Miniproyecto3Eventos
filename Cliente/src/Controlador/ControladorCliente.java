/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Controlador;

import java.io.IOException;

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

    public static void iniciar()
    {
        gui = new GUICliente();
        try 
        {
            //clienteConectadoC();
            conexionCliente = new ConexionCliente(12345);
            conexionCliente.obtenerFlujos();
            conexionCliente.procesarConexion();
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
        System.out.println(infoMulti);
        System.out.println(entradaCadena[0]);
        //establecerPreguntas(infoMulti);
        if (entradaCadena[0].equals("Examen"))
        {
            System.out.println("entro a if examen");
            establecerPreguntas(infoMulti);
        }
        if (entradaCadena[0].equals("Informe"))
        {
            //Llamar a la funcion que crea el informe
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
                    for(int i=4; i<cadena.length; i++)
                    {
                        examen.addPregunta(cadena[i]);
                        System.out.println("Se adicionó correctamente la pregunta " + Integer.toString(i-4));
                        System.out.println("Arreglo: "+ examen.getNumPreg());
                        
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
            gui.bloquearPestaña(0,false);
            establecerTiempo();
            
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
        else{
            conexionCliente.enviarDatos("Desbloquear\n"+pregunta);
            System.out.println("Se envio el mensaje de desbloquear");}
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
    public static void establecerTiempo()
    {
        
        gui.setTiempoRestante(examen.getDuracion());

    }
    
    //public static void enviarRespuesta()

    /*
    //cambia el estado de la pregunta a contestada
    public static void preguntaRespondida(int numPreg)
    {
        examen.preguntaContestada(numPreg);
    }*/

    /*
    public static boolean examenCompleto()
    {
        return examen.respuestaCompletas();
    }
    public static boolean respuestaCorrecta()
    {
        
        return false;
    }
    */

}