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
    private int correctas, incorrectas, respondidas;  

    /**
     * Constructor de la clase ExamenServidor
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
        this.informe="El examen no ha sido respondido\nNo es posible generar un informe";
    }

    /**
     * Convierte en cadena de texto los atributos del examen
     * y los retorna
     * @return string del examen 
     */
    public String examenEnviar()
    {
        System.out.println(nombre+"\n"+tiempo[0]+"\n"+tiempo[1]+"\n"+stringPreguntas);
        return nombre+"\n"+tiempo[0]+"\n"+tiempo[1]+"\n"+stringPreguntas;
    }

    /**
     * Crea objetos de la clase preguntas
     */
    public void generarPreguntas()
    {
        String[] datos; 
        datos = stringPreguntas.trim().split("\n");
        for(int i=0; i<datos.length; i++)
        {
            preguntas.add(new PreguntaServidor(datos[i]));
        }
    }

    /**
     * Le agrega al atributo informe el texto que recibe como parametro
     * @param texto texto procesar
     */
    public void iniciarInforme(String texto)
    {
        this.informe = texto;
    }

    /**
     * Adicionar texto al informe
     * @param texto texto procesar
     */
    public void setInforme(String texto)
    {
        this.informe += texto;
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
     * Incrementa el numero de preguntas correctas
     */
    public void setCorrectas()
    {
        this.correctas++;
    }

    /**
     * Incrementa el numero de preguntas incorrectas
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

    /**
     * Retorna la cantidad de preguntas del examen
     * @return cantidad de preguntas
     */
    public int cantidadPreguntas()
    {
        return this.preguntas.size();
    }

    /**
     * Retorna la cantidad de minutos que dura el examen
     * @return minutos del examen
     */
    public String getMinutos()
    {
        return ""+this.tiempo[0];
    }

    /**
     * Retorna la cantidad de segundos que dura el examen
     * @return segundos del examen
     */
    public String getSegundos()
    {
        return ""+this.tiempo[1];
    }

    /**
     * Restorna la respuesta correcta de la pregunta
     * @param numPreg el numero de la pregunta
     * @return
     */
    public String getResCorrecta(int numPreg)
    {
       
        return preguntas.get(numPreg-1).getRespuestaCorrecta();
        
        
    } 

    /**
     * Incrementa el numero de respuestas respondidas
     */
    public void setPregRespondidas()
    {
        respondidas++;
    } 

    /**
     * Retona la cantidad de preguntas respondidas
     */
    public int getPregRespondida()
    {
        return respondidas;
    }
}

