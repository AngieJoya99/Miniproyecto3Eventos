package Vista;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;


public class GUICliente extends JFrame 
{
    private ArrayList <JToggleButton> numeroPregunta;
    JTabbedPane tpPestanas;
    JScrollPane jsPestanaExamen, jsResultado, jsExamen;
    JButton bResponder, bVerResultado, bCancelarPreg, bOK, bResponderPreg;
    JRadioButton rbOpcA, rbOpcB, rbOpcC, rbOpcD;
    JTextField tfTempoRestante, tfPregRes;
    ButtonGroup bgGrupoOpc;
    JTextArea areaExamen, areaResultado, areaPregunta;

    JPanel pExamen, pResultado, pPregunta, pTextArea, pBotonesPreg, pSurExamen, pNorteExamen, pUsuarioConectado, pInformacion, pOpcMultiple, pBotones, pOpc1, pOpc2;

    JLabel lExamen, lTempoRestante, lPregRespondidas, lClienteConectado1, lClienteConectado2, lClienteConectado3,lNumPreg, lEnunciadoPreg, lInforme, lPregCorrecta, lCalificacion; 

    /**
     *Constructor de la clase GUIServidor
     */
    public GUICliente()
    {
        setTitle("Cliente");
        setSize(480, 560);
        crearGUI();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    
    public void crearGUI()
    {

        //----------------pestaña previsualizacion examen----------------
        
        //Crear componentes
        tpPestanas = new JTabbedPane();

        pExamen = new JPanel(new BorderLayout());
        pResultado = new JPanel();
        pPregunta = new JPanel();

        numeroPregunta = new ArrayList<>();
        jsPestanaExamen = new JScrollPane(pExamen);
    

        tpPestanas.addTab("Examen",pExamen);
        tpPestanas.addTab("Pregunta",pPregunta);
        tpPestanas.addTab("Resultado",pResultado);
        add(tpPestanas);
        
        pTextArea = new JPanel(new BorderLayout());
        pInformacion = new JPanel(new GridLayout(2,2));
        pBotonesPreg = new JPanel(new GridLayout(11,0));
        pUsuarioConectado = new JPanel();

        pNorteExamen = new JPanel();
        pSurExamen = new JPanel(new BorderLayout());
        
        

        areaExamen = new JTextArea(10, 28);
        jsExamen = new JScrollPane(areaExamen);
        jsExamen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 5),""));
        
        areaResultado = new JTextArea();
        jsResultado = new JScrollPane(areaResultado);

        lExamen = new JLabel();
        lCalificacion = new JLabel("Calificacion");
        lTempoRestante = new JLabel("Tiempo Restante");
        lPregRespondidas = new JLabel("Preguntas Respondidas");
        lNumPreg = new JLabel();
        lEnunciadoPreg = new JLabel();
        lClienteConectado1 = new JLabel();
        lClienteConectado2 = new JLabel();
        lClienteConectado3 = new JLabel();

        tfPregRes = new JTextField();
        tfTempoRestante = new JTextField();

        bVerResultado = new JButton("Ver Resultado");
        bResponder = new JButton("Responder");
        bOK = new JButton();


    
        //panel 
        pTextArea.add(jsExamen, BorderLayout.NORTH); 
        pTextArea.add(bResponder, BorderLayout.CENTER);

        pInformacion.add(lTempoRestante);pInformacion.add(lPregRespondidas);
        pInformacion.add(tfTempoRestante);pInformacion.add(tfPregRes);

        pUsuarioConectado.add(lClienteConectado1);pUsuarioConectado.add(lClienteConectado2);pUsuarioConectado.add(lClienteConectado3);

       pNorteExamen.add(pBotonesPreg);pNorteExamen.add(pTextArea);
       pSurExamen.add(bVerResultado, BorderLayout.NORTH);
       pSurExamen.add(pInformacion, BorderLayout.CENTER);
       pSurExamen.add(pUsuarioConectado, BorderLayout.SOUTH);

        
        //Se añaden los paneles a la pestaña examen
        pExamen.add(pNorteExamen, BorderLayout.NORTH);
        pExamen.add(pSurExamen, BorderLayout.CENTER);

        //---------pestaña pregunta-----------------

        //Crear componentes
        
        areaPregunta = new JTextArea(15, 35);
        areaPregunta.setEditable(false);
        bCancelarPreg = new JButton("Cancelar");
        bResponderPreg = new JButton("Responder");

        rbOpcA = new JRadioButton("Opción A");
        rbOpcB = new JRadioButton("Opción B");
        rbOpcC = new JRadioButton("Opción C");
        rbOpcD = new JRadioButton("Opción D");
        bgGrupoOpc = new ButtonGroup();
        bgGrupoOpc.add(rbOpcA);bgGrupoOpc.add(rbOpcB);
        bgGrupoOpc.add(rbOpcC);bgGrupoOpc.add(rbOpcD);



        pBotones = new JPanel();
        pBotones.add(bCancelarPreg); pBotones.add(bResponderPreg);

        pOpc1 = new JPanel();
        pOpc2 = new JPanel();
        pOpc1.add(rbOpcA);  pOpc1.add(rbOpcB); 
        pOpc2.add(rbOpcC); pOpc2.add(rbOpcD); 

        pOpcMultiple = new JPanel(new GridLayout(3,0));
        pOpcMultiple.add(pOpc1);
        pOpcMultiple.add(pOpc2);
        pOpcMultiple.add(pBotones);

        pPregunta.add(areaPregunta, BorderLayout.CENTER);
        pPregunta.add(pOpcMultiple, BorderLayout.SOUTH);
        
       





    
    }

    public void crearBotones(int numPreguntas)
    {
        for (int i=0; i < numPreguntas ; i++ )
        {
            JToggleButton botonPreg = new JToggleButton(Integer.toString(i+1));
            numeroPregunta.add(botonPreg);
        }
    }

    public void redimensionarPanel(int numPreguntas)
    {
        pBotonesPreg.setLayout(new GridLayout(numPreguntas,0));
    }

    public void establecerBotones (int numPreg)
    {   
        crearBotones(numPreg);
        redimensionarPanel(numPreg);
        for (int i=0; i< numeroPregunta.size(); i++)
        {
             pBotonesPreg.add(numeroPregunta.get(i));
        }
    } 
}



