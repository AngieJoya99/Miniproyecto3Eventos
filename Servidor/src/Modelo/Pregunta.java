/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Modelo;

/**
 * Clase que selecciona el enunciado, las opciones y la respuesta correcta de una
 * pregunta que recibe como parámetro
 */
public class Pregunta 
{
    private String enunciado;
    private String[] opciones;
    private String respuesta;

    /**
     * Constructor de la clase pregunta
     */
    public Pregunta(String texto)
    {
        this.opciones = new String[]{"","","",""};
        setEnunciado(texto);       
        setRespuesta(texto);
        setOpciones(texto);
    }

    /**
     * Separa un String que recibe como parámetro usando el delimitador "-",
     * Y guarda la primera iteración en la variable enunciado
     * @param texto Texto a procesar
     */
    public void setEnunciado(String texto)
    {
        String[] datos; 
        datos = texto.trim().split("-");
        this.enunciado = datos[0];
        System.out.println("El enunciado es: "+enunciado);
    }

    /**
     * Separa un String que recibe como parámetro usando el delimitador "+",
     * Y guarda la segunda iteración en la variable respuesta
     * @param texto Texto a procesar
     */
    public void setRespuesta(String texto)
    {
        String[] datos; 
        datos = texto.trim().split("\\+");
        this.respuesta = datos[1];
        System.out.println("La respuesta correcta es: "+respuesta);
    }

    /**
     * Separa un String que recibe como parámetro usando el delimitador ";",
     * Y guarda a partir de la segunda iteración en el arreglo Opciones
     * @param texto Texto a procesar
     */
    public void setOpciones(String texto)
    {
        String[] datos; 
        datos = texto.trim().split(";");
        for (int i=1; i<=4; i++)
        {
            this.opciones[i-1] = datos[i];
            System.out.println("La opción "+(i-1)+" es: "+this.opciones[i-1]);
        }        
    }

    /**
     * Retorna el texto almacenado en la variable enunciado
     * @return Valor almacenado en la variable
     */
    public String getEnunciado()
    {
        return this.enunciado;
    }

    /**
     * Retorna el texto almacenado en la variable respuesta
     * @return Valor almacenado en la variable
     */
    public String getRespuesta()
    {
        return this.respuesta;
    }

    /**
     * Retorna el texto almacenado en el arreglo opciones, en una posición
     * que recibe como parámetro
     * @param i Posición del arreglo
     * @return Valor almacenado en la posición i del arreglo
     */
    public String getOpcion(int i)
    {
        return this.opciones[i];
    }

}
