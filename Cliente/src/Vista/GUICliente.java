package Vista;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GUICliente extends JFrame 
{
    private ArrayList <JToggleButton> numeroPregunta;
    private ArrayList <Integer> preguntaSeleccionada;
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
        tpPestanas.setEnabledAt(1, false);
        tpPestanas.setEnabledAt(2, false);
        
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

    
        ManejadoraEvento evento = new ManejadoraEvento();
        bResponder.addActionListener(evento);
        bCancelarPreg.addActionListener(evento);

    
        
        
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
        ButtonGroup grupoBotonPreg = new ButtonGroup();
        ManejadoraEvento evento = new ManejadoraEvento();
        for (int i=0; i < numPreguntas ; i++ )
        {
            
            JToggleButton botonPreg = new JToggleButton( Integer.toString(i+1));
            
            numeroPregunta.add(botonPreg);
            grupoBotonPreg.add(botonPreg);
            System.out.println("el boton "+numeroPregunta.get(i).getText()+"ha sido creado");
            numeroPregunta.get(i).addActionListener( evento);
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
        System.out.println("tamaño arreglo de botones de pregunta: "+Integer.toString(numeroPregunta.size()));
    } 

    public void labelNumeroPregunta (String numPregunta)
    {
        
            lNumPreg.setText("Pregunta "+ numPregunta);

    }

    /*public void bloquearPregunta()
    {
        for(int i=0;i<numeroPregunta.size();i++)
        {
            if(numeroPregunta.get(i).isSelected())
                //numeroPregunta.get(i).setEnabled(false);
                System.out.println("El boton "+numeroPregunta.get(i).getText()+ " ha sido bloqueado");
        }

    }*/
    /*public void bloquearBotones(String indiceBotonSinBloquear)
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
        

    }*/

   

   /*public void bloquearPregunta(int numPregunta)
    {
        System.out.println(numeroPregunta.size());
        for(int i=0; i<numeroPregunta.size(); i++)
        {
            JToggleButton boton = numeroPregunta.get(i);
            if(boton.getText() == Integer.toString(numPregunta))
            { 
                boton.setEnabled(false);
                System.out.println("boton "+ Integer.toString(numPregunta)+"ha sido bloqueado");
            }
        }
    }*/

    public void bloquearPregunta()
    {
        for(JToggleButton boton : numeroPregunta)
        {
            if(boton.isSelected())
            {
                boton.setEnabled(false);
                System.out.println("El boton "+boton.getText()+ "ha sido bloqueado");
            }
            
        }

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
            for(JToggleButton boton : numeroPregunta) {
                if(e.getSource()== boton)
                {
                    areaExamen.setText("Esta es la pregunta "+boton.getText());
                    labelNumeroPregunta(boton.getText());
                    System.out.println("El boton "+ boton.getText() +" ha sido seleccionado");
                    

                }
            }
            if(e.getSource() == bResponder)
            {

                tpPestanas.setEnabledAt(1, true);
                tpPestanas.setSelectedIndex(1);
                tpPestanas.setEnabledAt(0, false);
                bloquearPregunta(); 
                /*for(int i=0; i<numeroPregunta.size(); i++)
                {
                    numeroPregunta.get(i).setEnabled(false);
                    System.out.println("se ha bloqueado el boton " + Integer.toString(i+1));
                }*/
                
                //bloquearPregunta(2);
                //tpPestanas.setEnabledAt(0,false);
                
                //bloquearBotones("2");

            }

            if(e.getSource() == bCancelarPreg)
            {}

               

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



