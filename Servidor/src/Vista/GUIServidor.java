package Vista;

import javax.swing.*;
import java.awt.*;

public class GUIServidor extends JFrame 
{
    JTabbedPane pestanas;   
    JPanel pCrear, pVisualizar, pInformes, pHacer, pCrearNombre,
        pCrearTiempo, pHoras, pVerSeleccionar, pInformeSeleccionar;

    JScrollPane spVisualizar, spInforme;

    JLabel lCrearNombre, lCrearArchivo, lCrearDuracion, 
        lIniciarCantidad, lIniciarTiempo, lIniciarTiempoRestante, 
        lIniciarTimer, lIniciarPreguntas, lIniciarRespondidas, 
        lIniciarCliente1, lIniciarCliente2, lIniciarCliente3, lDosPuntos1, lDosPuntos2;

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
        lDosPuntos1 = new JLabel(":");
        lDosPuntos2 = new JLabel(":");

        tfCrearNombre = new JTextField();
        
        bCrearCargar = new JButton("Cargar Preguntas");
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
        pInformes = new JPanel(new BorderLayout());
        pVisualizar = new JPanel(new BorderLayout());

        pCrearNombre = new JPanel(new GridLayout(1,2));
        pCrearTiempo = new JPanel(new GridLayout(1,2));
        pHoras = new JPanel();

        pVerSeleccionar = new JPanel();
        pInformeSeleccionar = new JPanel();

        pestanas = new JTabbedPane();

        // Posicionar elementos del panel Crear
        pCrearNombre.add(lCrearNombre);
        pCrearNombre.add(tfCrearNombre);
        pCrear.add(pCrearNombre);
        pCrear.add(bCrearCargar);
        pCrear.add(lCrearArchivo);
        pCrearTiempo.add(lCrearDuracion);
        pHoras.add(horas);
        pHoras.add(lDosPuntos1);
        pHoras.add(minutos);
        pHoras.add(lDosPuntos2);
        pHoras.add(segundos);
        pCrearTiempo.add(pHoras);
        pCrear.add(pCrearTiempo);
        pCrear.add(bCrearCrear);

        //Posicionar elementos del panel Visualizar
        pVerSeleccionar.add(cbVisualizar);
        pVerSeleccionar.add(bVerVer);
        pVisualizar.add(pVerSeleccionar, BorderLayout.NORTH);
        spVisualizar.add(taVisualizar);
        pVisualizar.add(spVisualizar, BorderLayout.CENTER);
        pVisualizar.add(bVerLimpiar, BorderLayout.SOUTH);

        //Posicionar elementos del panel Informe
        pInformeSeleccionar.add(cbInforme);
        pInformeSeleccionar.add(bInformeVer);
        pInformes.add(pInformeSeleccionar, BorderLayout.NORTH);
        spInforme.add(taInforme);
        pInformes.add(spInforme, BorderLayout.CENTER);
        pInformes.add(bInformeLimpiar, BorderLayout.SOUTH);





        pestanas.addTab("Crear examen",pCrear);
        pestanas.addTab("Visualizar examen",pVisualizar);
        pestanas.addTab("Ver informes",pInformes);
        pestanas.addTab("Realizar examen",pHacer);

        add(pestanas);
        //Hola
    }
}
