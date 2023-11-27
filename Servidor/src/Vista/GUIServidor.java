package Vista;

import javax.swing.*;

public class GUIServidor extends JFrame 
{
    JTabbedPane pestanas;   
    JPanel pCrear, pVisualizar, pInformes, pHacer;
    JScrollPane spVisualizar, spInforme;

    JLabel lCrearNombre, lCrearArchivo, lCrearDuracion, 
        lIniciarCantidad, lIniciarTiempo, lIniciarTiempoRestante, 
        lIniciarTimer, lIniciarPreguntas, lIniciarRespondidas, 
        lIniciarCliente1, lIniciarCliente2, lIniciarCliente3;

    JButton bCrearCargar, bCrearCrear, bVerVer, bVerLimpiar, 
        bInformeVer, bInformeLimpiar, bIniciarIniciar;

    JTextField tfCrearNombre;
    JTextArea taVisualizar, taInforme;
    JComboBox cbVisualizar, cbInforme, cbIniciar;
    JSpinner horas, minutos, segundos;

    /**
     * Constructor de la clase GUIServidor
     */
    public GUIServidor()
    {
        setTitle("Servidor");
        setSize(480, 560);
        crearGUI();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Crea e inicializa los elementos de la GUI
     */
    public void crearGUI()
    {
        //Crear componentes
        lCrearNombre = new JLabel("Nombre del examen");
        lCrearArchivo= new JLabel("El archivo seleccionado fue: ");
        lCrearDuracion = new JLabel("Duración del examen");

        lIniciarCantidad = new JLabel("Cantidad de preguntas");
        lIniciarCliente1 = new JLabel();
        lIniciarCliente2 = new JLabel();
        lIniciarCliente3 = new JLabel();
        lIniciarPreguntas = new JLabel("Cantidad de preguntas");
        lIniciarTiempo = new JLabel("Tiempo");
        lIniciarTiempoRestante = new JLabel("Tiempo restante");
        lIniciarRespondidas = new JLabel("Preguntas respondidas");

        tfCrearNombre = new JTextField();
        
        bCrearCargar = new JButton("Cargar archivo");
        bCrearCrear = new JButton("Crear");

        bVerVer = new JButton("Ver");
        bVerLimpiar = new JButton("Limpiar");

        bInformeVer = new JButton("Ver");
        bInformeLimpiar = new JButton("Limpiar");
        bIniciarIniciar = new JButton("Iniciar");

        taInforme = new JTextArea();
        taVisualizar = new JTextArea();

        cbInforme = new JComboBox<>();
        cbIniciar = new JComboBox<>();
        cbVisualizar = new JComboBox<>();

        horas = new JSpinner();
        minutos = new JSpinner();
        segundos = new JSpinner();

        spInforme = new JScrollPane();
        spVisualizar= new JScrollPane();

        pCrear = new JPanel();
        pHacer = new JPanel();
        pInformes = new JPanel();
        pVisualizar = new JPanel();

        pestanas = new JTabbedPane();

        pestanas.addTab("Crear examen",pCrear);
        pestanas.addTab("Visualizar examen",pVisualizar);
        pestanas.addTab("Ver informes",pInformes);
        pestanas.addTab("Realizar examen",pHacer);

        add(pestanas);
        //Hola
    }
}
