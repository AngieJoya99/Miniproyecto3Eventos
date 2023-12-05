/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase que crea un examen a partir de un banco de preguntas
 */
public class Examen 
{
    private ArrayList<Pregunta> preguntas;
    private String nombre, informe, textoPreguntas;
    private int[] tiempo; 
    private int correctas, incorrectas;  

    /**
     * Constructor de la clase examen
     */
    public Examen(String nombre, int horas, int minutos, int segundos)
    {
        this.preguntas = new ArrayList<Pregunta>();
        this.tiempo= new int[]{0,0,0};
        this.nombre = nombre;
        this.tiempo[0] = horas;
        this.tiempo[1] = minutos;
        this.tiempo[2] = segundos;
        this.textoPreguntas="";
    }

    /**
     * A partir de una cadena de texto que recibe como parámetro,
     * la adiciona al contenido actual de la variable textoPreguntas,
     * Crea un objeto de la clase Pregunta con ella 
     * y se lo adiciona a la colección preguntas
     * @param texto Cadena de texto a procesar
     */
    public void addPregunta(String texto)
    {
        this.preguntas.add(new Pregunta(texto));
        this.textoPreguntas+=(texto+"\n");
    }

    /**
     * Retorna el texto almacenado en la variable textoPreguntas
     * @return Valor almacenado en la variable
     */
    public String getTextoPreguntas()
    {
        return this.textoPreguntas;
    }

    /**
     * Retorna el una cadena de texto con los valores del arreglo tiempo
     * Separados por ":"
     * @return Cadeana de texto
     */
    public String getDuracion()
    {
        String duracion = ""+tiempo[0]+":"+tiempo[1]+":"+tiempo[2];
        return duracion;
    }

    /**
     * Retorna el texto almacenado en la variable nombre
     * @return Valor almacenado en la variable
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * Retorna el texto almacenado en la variable informe
     * @return Valor almacenado en la variable
     */
    public String getInforme()
    {
        return this.informe;
    }

    /**
     * Le asigna a la variable correctas un valor que recibe como 
     * parámetro
     * @param cantidad valor a asignar
     */
    public void setCorrectas(int cantidad)
    {
        this.correctas = cantidad;
    }

    /**
     * Le asigna a la variable incorrectas un valor que recibe como 
     * parámetro
     * @param cantidad valor a asignar
     */
    public void setIncorrectas(int cantidad)
    {
        this.incorrectas = cantidad;
    }

    /**
     * Retorna el valor almacenado en la variable correctas
     * @return Valor almacenado en la variable
     */
    public int getCorrectas()
    {
        return this.correctas;
    }

    /**
     * Retorna el valor almacenado en la variable incorrectas
     * @return Valor almacenado en la variable
     */
    public int getIncorrectas()
    {
        return this.incorrectas;
    }

    /**
     * Retorna el enunciado de la pregunta
     * @param numPreg
     * @return  enunciado pregunta y las respuestas
     */
    public String getPreguntas(int numPreg)
    {
        return this.preguntas.get(numPreg).getEnunciado() + this.preguntas.get(numPreg).getRespuesta();

    }

    /**
     * funcion que cambia el estado de la pregunta a true (contestada)
     */
    /*public void preguntaContestada(int numPreg)
    {

       preguntas.get(numPreg-1).setEstadoPregunta();
    }

    public boolean respuestaCompletas()
    {
        int preguntasContestadas = 0;
        for(int i=0; i<preguntas.size();i++)
        {
            if(preguntas.get(i).getEstadoPregunta() == true)
                preguntasContestadas+=1;
        }
       if(preguntas.size() == preguntasContestadas)
        return true;
        else
            return false;
    }
    */


    


}
