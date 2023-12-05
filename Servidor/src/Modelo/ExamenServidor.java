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
public class ExamenServidor
{
    private ArrayList<PreguntaServidor> preguntas;
    private String nombre, informe, stringPreguntas;
    private int[] tiempo; 
    private int correctas, incorrectas;  

    /**
     * Constructor de la clase ExamenServidpr
     */
    public ExamenServidor(String nombre, int horas, int minutos, String preguntas)
    {
        this.preguntas = new ArrayList<PreguntaServidor>();
        this.tiempo= new int[]{0,0};
        this.nombre = nombre;
        this.tiempo[0] = horas;
        this.tiempo[1] = minutos;
        this.stringPreguntas = preguntas;
        generarPreguntas();
        this.correctas=0;
        this.incorrectas=0;
    }

    public String examenEnviar()
    {
        return nombre+"\n"+tiempo[0]+"\n"+tiempo[1]+"\n"+stringPreguntas;
    }

    public void generarPreguntas()
    {
        String[] datos; 
        datos = stringPreguntas.trim().split("\n");
        for(int i=0; i<datos.length; i++)
        {
            preguntas.add(new PreguntaServidor(datos[i]));
        }
    }

    public void setInforme(String texto)
    {
        this.informe = texto;
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
     * 
     */
    public void setCorrectas()
    {
        this.correctas++;
    }

    /**
     * 
     */
    public void setIncorrectas()
    {
        this.incorrectas++;
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

    public int cantidadPreguntas()
    {
        return this.preguntas.size();
    }

    public String getHoras()
    {
        return ""+this.tiempo[0];
    }

    public String getMinutos()
    {
        return ""+this.tiempo[1];
    }

    public String getResCorrecta(int numPreg)
    {
        return preguntas.get(numPreg).getRespuestaCorrecta();
    }    
}

