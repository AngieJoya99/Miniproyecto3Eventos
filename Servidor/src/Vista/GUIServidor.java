package Vista;

import javax.swing.*;

import Controlador.ControladorServidor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIServidor extends JFrame 
{
    JTabbedPane pestanas;   
    JPanel pCrear, pVisualizar, pInformes, pIniciar, pCrearNombre,
        pCrearArchivo, pCrearTiempo, pHoras, pVerSeleccionar, 
        pInformeSeleccionar, pClientes;

    JScrollPane spVisualizar, spInforme;

    JLabel lCrearNombre, lCrearArchivo, lCrearDuracion, 
        lIniciarCantidad, lIniciarTiempo, lIniciarTiempoRestante, 
        lIniciarTimer, lIniciarPreguntas, lIniciarRespondidas, 
        lIniciarCliente1, lIniciarCliente2, lIniciarCliente3, lDosPuntos;

    JButton  bCrearCrear, bVerVer, bVerLimpiar, 
        bInformeVer, bInformeLimpiar, bIniciarIniciar;

    JTextField tfCrearNombre;
    JTextArea taVisualizar, taInforme;
    JComboBox<String> cbVisualizar, cbInforme, cbIniciar, cbCrear;
    JSpinner horas, minutos;
    Font fuente1, fuente2;
    SpinnerListModel modeloHora, modeloMin; 

    ArrayList<String> examenes, nombresExamen;

    /**
     * Constructor de la clase GUIServidor
     */
    public GUIServidor()
    {
        setTitle("Servidor");
        setSize(440, 500);
        crearGUI();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Crea e inicializa los elementos de la GUI
     */
    public void crearGUI()
    {
        //Crear componentes

        examenes = new ArrayList<String>();
        nombresExamen = new ArrayList<String>();

        lCrearNombre = new JLabel("Nombre del examen");
        lCrearArchivo= new JLabel("Seleccione el archivo a cargar");
        lCrearDuracion = new JLabel("Duración del examen (hh:mm)");

        lIniciarCantidad = new JLabel("Cantidad de preguntas");
        lIniciarCliente1 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarCliente2 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarCliente3 = new JLabel(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        lIniciarPreguntas = new JLabel("Cantidad de preguntas");
        lIniciarTiempo = new JLabel("Tiempo");
        lIniciarTiempoRestante = new JLabel("Tiempo restante");
        lIniciarRespondidas = new JLabel("Preguntas respondidas");
        lIniciarTimer = new JLabel();
        lDosPuntos = new JLabel(":");

        tfCrearNombre = new JTextField();
        
        bCrearCrear = new JButton("Crear");

        bVerVer = new JButton("Ver");
        bVerLimpiar = new JButton("Limpiar");

        bInformeVer = new JButton("Ver");
        bInformeLimpiar = new JButton("Limpiar");
        bIniciarIniciar = new JButton("Iniciar");

        taInforme = new JTextArea();
        taVisualizar = new JTextArea();

        cbCrear = new JComboBox<String>();        
        cualesArchivos();
        cbInforme = new JComboBox<>();
        cbIniciar = new JComboBox<>();
        cbVisualizar = new JComboBox<>();

        modeloHora = new SpinnerListModel(listaHoras());
        modeloMin = new SpinnerListModel(listaMin());

        horas = new JSpinner(modeloHora);
        minutos = new JSpinner(modeloMin);

        spInforme = new JScrollPane();
        spVisualizar= new JScrollPane();

        pCrear = new JPanel(new FlowLayout(FlowLayout.CENTER,1,15));
        pIniciar = new JPanel();
        pInformes = new JPanel(new BorderLayout());
        pVisualizar = new JPanel(new BorderLayout());

        pCrearNombre = new JPanel(new GridLayout(1,2,20,10));
        pCrearArchivo = new JPanel();
        pCrearTiempo = new JPanel();
        pHoras = new JPanel();

        pVerSeleccionar = new JPanel();
        pInformeSeleccionar = new JPanel();

        pClientes = new JPanel(new GridLayout(1,3));

        pestanas = new JTabbedPane();


        fuente1 = new Font("Lato", Font.BOLD, 20);
        fuente2 = new Font("Lato", Font.PLAIN, 16);

        //Dar formato a elementos
        lCrearNombre.setFont(fuente1);
        tfCrearNombre.setFont(fuente2);
        lCrearArchivo.setFont(fuente1);
        lCrearDuracion.setFont(fuente1);
        horas.setFont(fuente2);
        minutos.setFont(fuente2);
        bCrearCrear.setFont(fuente1);

        horas.setPreferredSize(new Dimension(40,30));
        minutos.setPreferredSize(new Dimension(40,30));

        taInforme.setText("Prueba del area de texto");

          


        // Posicionar elementos del panel Crear
        pCrearNombre.add(lCrearNombre);
        pCrearNombre.add(tfCrearNombre);
        pCrear.add(pCrearNombre);
        pCrearArchivo.add(lCrearArchivo);
        pCrearArchivo.add(cbCrear);
        pCrear.add(pCrearArchivo);
        pCrearTiempo.add(lCrearDuracion);
        pHoras.add(horas);
        pHoras.add(lDosPuntos);
        pHoras.add(minutos);
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
        spInforme.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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

        //Añadir páneles a pestañas

        pestanas.addTab("Crear examen",pCrear);
        pestanas.addTab("Visualizar examen",pVisualizar);
        pestanas.addTab("Ver informes",pInformes);
        pestanas.addTab("Realizar examen",pIniciar);

        add(pestanas);

        //Escuchas de Botones
        ManejarEventos evento = new ManejarEventos();
        bCrearCrear.addActionListener(evento);
    }

    /**
     * Clase que determina las funciones que realizan los eventos
     */
    class ManejarEventos implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == bCrearCrear)
            {
                ControladorServidor.leerArchivo();
                ControladorServidor.crearExamen();
            }
            if(e.getSource()== bIniciarIniciar)
            {
                ControladorServidor.enviarExamen();
                //Iniciar timer
            }
        }
        
    }

    /**
     * Crea una arreglo de String y lo llena con los números
     * del 0 al 24
     * @return Arreglo de números
     */
    public String[] listaHoras()
    {
        String[] arreglo = new String[25];  
        for (int i=0; i<=24; i++)
        {
            arreglo[i] = ""+i;
        }
        return arreglo; 
    }

    /**
     * Crea una arreglo de String y lo llena con los números
     * del 0 al 60
     * @return Arreglo de números
     */
    public String[] listaMin()
    {
        String[] arreglo = new String[61];  
        for (int i=0; i<=60; i++)
        {
            arreglo[i] = ""+i;
        }
        return arreglo; 
    }

    /**
     * Cambia el ícono del label que representa la conexión
     * del cliente 1 dependiendo de un valor booleano que recibe
     * como parámetro
     * @param color Icono rojo si es false, icono verde
     * si es true
     */
    public void cambiarIcono1( boolean color)
    {
        if (!color)
        {
            lIniciarCliente1.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        }
        else
        {
            lIniciarCliente1.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Verde.png")));
        }
    }

    /**
     * Cambia el ícono del label que representa la conexión
     * del cliente 2 dependiendo de un valor booleano que recibe
     * como parámetro
     * @param color Icono rojo si es false, icono verde
     * si es true
     */
    public void cambiarIcono2( boolean color)
    {
        if (!color)
        {
            lIniciarCliente2.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        }
        else
        {
            lIniciarCliente2.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Verde.png")));
        }
    }

    /**
     * Cambia el ícono del label que representa la conexión
     * del cliente 3 dependiendo de un valor booleano que recibe
     * como parámetro
     * @param color Icono rojo si es false, icono verde
     * si es true
     */
    public void cambiarIcono3( boolean color)
    {
        if (!color)
        {
            lIniciarCliente3.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Rojo.png")));
        }
        else
        {
            lIniciarCliente3.setIcon(new ImageIcon(getClass().getResource("../Imagenes/Verde.png")));
        }
    }

    public String leerNombreExamen()
    {
        return tfCrearNombre.getText();
    }

    public String leerHoras()
    {
        return ""+horas.getValue();
    }

    public String leerMinutos()
    {
        return ""+minutos.getValue();
    }

    /**
     * Método que lee el nombre de los archivos guardados en el paquete Archivos
     * Y los añade al Combo Box cbCrear
     */
    public void cualesArchivos()
    {
        File carpeta = new File("Servidor"+File.separator+"src"+File.separator+"Archivos");
        File[] archivos = carpeta.listFiles();

        for (int i = 0; i < archivos.length; i++) {
            if (archivos[i].isFile()) {
                cbCrear.addItem(archivos[i].getName());
            }
        }   
    }

    public void agregarExamen(String texto)
    {
        examenes.add(texto);
    }

    public void agregarNombreExamen(String texto)
    {
        nombresExamen.add(texto);
    }

    public String getExamen(int pos)
    {
        return examenes.get(pos);
    }

    public String getNombreExamen(int pos)
    {
        return nombresExamen.get(pos);
    }

    public String leerNombreArchivo()
    {
        return cbCrear.getSelectedItem().toString();
    }

}
