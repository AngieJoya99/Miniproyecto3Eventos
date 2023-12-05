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

public class ControladorCliente 
{
    static GUICliente gui;
    static ConexionCliente conexionCliente;
    static MulticastCliente multicastCliente;
    static Examen examen;

    public static void iniciar()
    {
        gui = new GUICliente();
       
        gui.establecerBotones(10);
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
<<<<<<< HEAD

=======
>>>>>>> 2f434bedb0fac9e768c5ce91acd2a9ae402e30b8
    
    /**
     * Recibe un String, lo divide por cada linea que contenga y establece un objeto de la clase Examen
     * que a su vez establece un objeto de la clase pregunta
     * @param examenString
     */
    public static  void establecerPreguntas(String examenString )
    {  
            String[] cadena = examenString.trim().split("\n");

            if (cadena.length >= 4) {
                Examen examen = new Examen(cadena[0], Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]));
                for(int i=4; i<cadena.length; i++)
                {
                    examen.addPregunta(cadena[i]);
                    System.out.println("Se adicionó correctamente la pregunta " + Integer.toString(i));
                }
            } else 
                System.out.println("Formato de entrada incorrecto");
    }

    public static void mostrarPregunta(int numPregunta)
    {  
        gui.setTextAreaPreg(examen.getPreguntas(numPregunta));
        gui.labelNumeroPregunta(Integer.toString(numPregunta));
        gui.setTextAreaPreg(examen.getPreguntas(numPregunta));
    }

    public static void responderPregunta(String respuesta)
    {
        String mensaje = "RESPONDIDA||"+gui.getNumPreg()+"||"+conexionCliente.getNombreCliente()+"||"+respuesta;
        conexionCliente.enviarDatos(mensaje);
    }
    
<<<<<<< HEAD
    public static boolean respuestaCorrecta()
=======

    public static void clienteConectadoC()
>>>>>>> 2f434bedb0fac9e768c5ce91acd2a9ae402e30b8
    {
        
        return false;
    }
<<<<<<< HEAD
    
=======

>>>>>>> 2f434bedb0fac9e768c5ce91acd2a9ae402e30b8
}