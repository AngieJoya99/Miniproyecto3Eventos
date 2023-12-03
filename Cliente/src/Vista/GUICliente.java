package Vista;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GUICliente extends JFrame 
{
    private ArrayList <JButton> numeroPregunta;
    JTabbedPane tpPestanas;
    JScrollPane jsPestanaExamen, jsResultado, jsExamen, jsEnunciadoPregunta;
    JButton bResponder, bVerResultado, bCancelarPreg, bOK, bResponderPreg;
    JRadioButton rbOpcA, rbOpcB, rbOpcC, rbOpcD;
    JTextField tfTempoRestante, tfPregRes;
    ButtonGroup bgGrupoOpc;
    JTextArea areaExamen, areaResultado, areaPregunta;

    JPanel pExamen,pWestResultado, pEastResultado, pSouthResultado, pResultado, pPregunta, pTextArea, pBotonesPreg, pSurExamen, pNorteExamen, pUsuarioConectado, pInformacion, pOpcMultiple, pBotones, pOpc1, pOpc2, pInfoResultado;

    JLabel lExamen, lTempoRestante, lPregRespondidas, lClienteConectado1, lClienteConectado2, lClienteConectado3,lNumPreg, lInforme, lPregCorrecta, lCalificacion, lCalificacionNum, lPregCorrectaNum; 

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
        areaExamen.setEditable(false);
        jsExamen = new JScrollPane(areaExamen);
        jsExamen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 5),""));
        
        areaResultado = new JTextArea();
        jsResultado = new JScrollPane(areaResultado);

        lExamen = new JLabel();
        
        lTempoRestante = new JLabel("Tiempo Restante");
        lPregRespondidas = new JLabel("Preguntas Respondidas");
        
        
        lClienteConectado1 = new JLabel();
        lClienteConectado2 = new JLabel();
        lClienteConectado3 = new JLabel();

        tfPregRes = new JTextField();
        tfTempoRestante = new JTextField();

        bVerResultado = new JButton("Ver Resultado");
        bResponder = new JButton("Responder");
        


    
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
        jsEnunciadoPregunta = new JScrollPane(areaPregunta);
        areaPregunta.setEditable(false);
        bCancelarPreg = new JButton("Cancelar");
        bResponderPreg = new JButton("Responder");
        lNumPreg = new JLabel("Preguna 1");
        

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
        
        pPregunta.add(lNumPreg);
        pPregunta.add(jsEnunciadoPregunta);
        pPregunta.add(pOpcMultiple);

        //Objeto Eventos
       /*  ManejadoraEvento evento = new ManejadoraEvento();
        
        //Añadir escuchas
        // Añadiendo KeyListener a la ventana
        this.addKeyListener(evento);
        
        System.out.println("Funciona");
        System.out.println(Integer.toString(numeroPregunta.size()));

        ciclo();
        for(int i=0; i<numeroPregunta.size();i++ )
        {
            
            //retornarBoton(i).addActionListener(evento);
            System.out.println("se añadio correctamente la escucha");
                 
            
        }*/
        System.out.println("sisiisi");
        ManejadoraEvento evento = new ManejadoraEvento();
        bResponder.addActionListener(evento);

    
        
        
       //-------- Pestaña Resultado ---------------

        areaResultado = new JTextArea(15, 35);
        areaResultado.setEditable(false);
        jsResultado = new JScrollPane(areaResultado);
        lCalificacion = new JLabel("Calificacion");
        lPregCorrecta = new JLabel("Preguntas Correctas");
        lCalificacionNum = new JLabel("10");
        lPregCorrectaNum = new JLabel("20");
        lInforme = new JLabel("Resultado");
        bOK = new JButton("OK");

        pWestResultado = new JPanel(new BorderLayout());
        
        pWestResultado.add(lCalificacion, BorderLayout.NORTH); 
        pWestResultado.add(lPregCorrecta, BorderLayout.SOUTH); 

        pEastResultado = new JPanel(new BorderLayout());
        pEastResultado.add(lCalificacionNum, BorderLayout.NORTH);
        pEastResultado.add(lPregCorrectaNum, BorderLayout.SOUTH);

        pSouthResultado = new JPanel(new BorderLayout());
        pSouthResultado.add(pEastResultado, BorderLayout.EAST);
        pSouthResultado.add(pWestResultado, BorderLayout.WEST);
        pSouthResultado.add(bOK, BorderLayout.SOUTH);




        pResultado.add(lInforme);
        pResultado.add(jsResultado);
        pResultado.add(pSouthResultado);
        

    
    }







    public void crearBotones(int numPreguntas)
    {
        ManejadoraEvento evento = new ManejadoraEvento();
        for (int i=0; i < numPreguntas ; i++ )
        {
            
            JButton botonPreg = new JButton( Integer.toString(i+1));
            
            numeroPregunta.add(botonPreg);
            System.out.println("el boton "+numeroPregunta.get(i).getText()+"ha sido creado");
            numeroPregunta.get(i).addActionListener(evento);
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
        //escuchaToggleButton(numeroPregunta.size());
        System.out.println("tamaño arreglo de botones de pregunta: "+Integer.toString(numeroPregunta.size()));
    } 

    public void labelNumeroPregunta (int numPregunta)
    {
        lNumPreg.setText("Pregunta "+ Integer.toString(numPregunta));
    }

    public void bloquearBotones(String indiceBotonSinBloquear)
    {
        if(indiceBotonSinBloquear != "nulo")
        {
            for (int i=0; i<numeroPregunta.size();i++)
            {
                if(i == Integer.parseInt(indiceBotonSinBloquear))
                numeroPregunta.get(i).setEnabled(true);
            
                else
                numeroPregunta.get(i).setEnabled(false);
            }
        }
        else
            System.out.println("Los botones han sido deseleccionado");
        

    }

    class ManejadoraEvento implements ActionListener,KeyListener
    {
        /* 
        @Override
        public void itemStateChanged (ItemEvent e) 
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                for (int i=0; i<numeroPregunta.size(); i++)
                {

                    JButton toggleButton = numeroPregunta.get(i);

                    if(e.getItemSelectable() == toggleButton)
                    {
                        lNumPreg.setText("PREGUNTA "+ Integer.toString(i+1));
                        tpPestanas.setSelectedIndex(1);
                    }

                }
            }
        
        
        }*/

        @Override
        public void actionPerformed(ActionEvent e) {
            for(JButton boton : numeroPregunta) {
                if(e.getSource()== boton)
                {
                    areaExamen.setText("Esta es la pregunta "+boton.getText());;
                    System.out.println("El boton "+ boton.getText() +" ha sido seleccionado");
                }

               /*  if(e.getSource() == numeroPregunta.get(1))
                {
                    tpPestanas.setSelectedIndex(1);
                    System.out.println("El boton '2' ha sido seleccionado");
                }
                if(e.getSource() == retornarBoton(2))
                {
                    tpPestanas.setSelectedIndex(1);
                    System.out.println("El boton '2' ha sido seleccionado");
                }*/
            }

            if(e.getSource() == bResponder)
            {

                lNumPreg.setText("PREGUNTA "+ Integer.toString(2));
                tpPestanas.setSelectedIndex(1);
                bloquearBotones("2");

            }

        }

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }

    
    }
}



