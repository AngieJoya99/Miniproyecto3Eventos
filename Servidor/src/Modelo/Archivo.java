/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase que lee un archivo de texto y guarda la información en un arreglo de String 
 */
public class Archivo 
{
    private File archivo;
    private String preguntas;
    String nombreArchivo;

    /**
     * Constructor de la clase Archivo
     */
    public Archivo(String nombreArchivo)
    {
        this.preguntas = "";
        this.nombreArchivo = nombreArchivo;
        boolean existe =cargarArchivo();
        if(existe)
            generarPreguntas();
    }

    /**
     * Método que carga el archivo de texto que se utilizará
     */
    public boolean cargarArchivo()
    {
        String location = "Servidor"+File.separator+"src"+File.separator+"Archivos"+File.separator+nombreArchivo+".txt";
        this.archivo = new File(location);
        if(this.archivo.exists())
        {
            System.out.println("Archivo cargado correctamente");
            return true;
        }            
        else
        {
            System.out.println("El archivo no pudo ser cargado");
            return false;
        }
    }

    /**
     * Método que escanea un archivo,
     * lee el texto separado por un delimitador, 
     * y lo guarda en un String
     */
    public void generarPreguntas()
    {
        try {
            Scanner buscador = new Scanner(this.archivo);
            buscador.useDelimiter("}");
            while(buscador.hasNext())
            {
                preguntas+=buscador.next().trim();
                //buscador.nextLine();
            }
            buscador.close();
            System.out.println("Se establecieron las preguntas correctamente");                 
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo");
        }
    }

    /**
     * Método que obtiene el valor de la variable preguntas y lo
     * retorna
     * @return valor de la variable
     */
    public String getPreguntas()
    {
        return this.preguntas;
    }

}
