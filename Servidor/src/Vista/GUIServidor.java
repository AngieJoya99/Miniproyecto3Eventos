package Vista;

import javax.swing.*;
import java.awt.*;

public class GUIServidor extends JFrame 
{
    JTabbedPane pestanas;   
    JPanel pCrear, pVisualizar, pInformes, pIniciar, pCrearNombre,
        pCrearTiempo, pHoras, pVerSeleccionar, pInformeSeleccionar,
        pClientes;

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
    Font fuente1, fuente2;

    /**
     * Constructor de la clase GUIServidor
     */
    public GUIServidor()
    {
        setTitle("Servidor");
        setSize(500, 500);
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
        lIniciarCliente1 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarCliente2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarCliente3 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarPreguntas = new JLabel("Cantidad de preguntas");
        lIniciarTiempo = new JLabel("Tiempo");
        lIniciarTiempoRestante = new JLabel("Tiempo restante");
        lIniciarRespondidas = new JLabel("Preguntas respondidas");
        lIniciarTimer = new JLabel();
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
        pIniciar = new JPanel();
        pInformes = new JPanel(new BorderLayout());
        pVisualizar = new JPanel(new BorderLayout());

        pCrearNombre = new JPanel(new GridLayout(1,2));
        pCrearTiempo = new JPanel(new GridLayout(1,2));
        pHoras = new JPanel();

        pVerSeleccionar = new JPanel();
        pInformeSeleccionar = new JPanel();

        pClientes = new JPanel(new GridLayout(1,3));

        pestanas = new JTabbedPane();


        fuente1 = new Font("Lato", Font.BOLD, 24);
        fuente2 = new Font("Lato", Font.PLAIN, 20);

        //Dar formato a elementos
        lCrearNombre.setFont(fuente1);
        tfCrearNombre.setFont(fuente2);


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

        //Posicionar elementos del panel Informe
        pIniciar.add(cbIniciar);
        pClientes.add(lIniciarCliente1);
        pClientes.add(lIniciarCliente2);
        pClientes.add(lIniciarCliente3);
        pIniciar.add(pClientes);
        pIniciar.add(lIniciarCantidad);
        pIniciar.add(lIniciarTiempo);
        pIniciar.add(bIniciarIniciar);
        pIniciar.add(lIniciarTiempoRestante);
        pIniciar.add(lIniciarTimer);
        pIniciar.add(lIniciarPreguntas);
        pIniciar.add(lIniciarRespondidas);

        pestanas.addTab("Crear examen",pCrear);
        pestanas.addTab("Visualizar examen",pVisualizar);
        pestanas.addTab("Ver informes",pInformes);
        pestanas.addTab("Realizar examen",pIniciar);

        add(pestanas);
        //Hola
    }
}
