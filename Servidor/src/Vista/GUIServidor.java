package Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

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

    JButton  bCrearCrear, bVerVer, bVerLimpiar, 
        bInformeVer, bInformeLimpiar, bIniciarIniciar;

    JTextField tfCrearNombre;
    JTextArea taVisualizar, taInforme;
    JComboBox<String> cbVisualizar, cbInforme, cbIniciar, cbCrear;
    JSpinner horas, minutos, segundos;
    Font fuente1, fuente2;
    SpinnerListModel modeloHora, modeloMin, modeloSeg; 

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
        lCrearNombre = new JLabel("Nombre del examen");
        lCrearArchivo= new JLabel("Seleccione el archivo a cargar");
        lCrearDuracion = new JLabel("Duración del examen ");

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
        modeloMin = new SpinnerListModel(listaMinSeg());
        modeloSeg= new SpinnerListModel(listaMinSeg());

        horas = new JSpinner(modeloHora);
        minutos = new JSpinner(modeloMin);
        segundos = new JSpinner(modeloSeg);

        spInforme = new JScrollPane();
        spVisualizar= new JScrollPane();

        pCrear = new JPanel(new FlowLayout(FlowLayout.CENTER,1,15));
        pIniciar = new JPanel();
        pInformes = new JPanel(new BorderLayout());
        pVisualizar = new JPanel(new BorderLayout());

        pCrearNombre = new JPanel(new GridLayout(1,2,20,10));
        pCrearTiempo = new JPanel(new GridLayout(1,2));
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
        segundos.setFont(fuente2);
        bCrearCrear.setFont(fuente1);

        horas.setPreferredSize(new Dimension(40,30));
        minutos.setPreferredSize(new Dimension(40,30));
        segundos.setPreferredSize(new Dimension(40,30));

        taInforme.setText("Prueba del area de texto");

          


        // Posicionar elementos del panel Crear
        pCrearNombre.add(lCrearNombre);
        pCrearNombre.add(tfCrearNombre);
        pCrear.add(pCrearNombre);
        pCrear.add(lCrearArchivo);
        pCrear.add(cbCrear);
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

        pestanas.addTab("Crear examen",pCrear);
        pestanas.addTab("Visualizar examen",pVisualizar);
        pestanas.addTab("Ver informes",pInformes);
        pestanas.addTab("Realizar examen",pIniciar);

        add(pestanas);
        //Hola
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
    public String[] listaMinSeg()
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

    public String leerSegundos()
    {
        return ""+segundos.getValue();
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

}
