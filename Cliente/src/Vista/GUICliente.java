package Vista;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import Controlador.ControladorCliente;


public class GUICliente extends JFrame 
{
    private ArrayList <JToggleButton> numeroPregunta;
    
    JTabbedPane tpPestanas;
    JScrollPane jsPestanaExamen, jsResultado, jsExamen, jsEnunciadoPregunta;
    JButton bResponder, bVerResultado, bCancelarPreg, bOK, bResponderPreg;
    JRadioButton rbOpcA, rbOpcB, rbOpcC, rbOpcD;
    ButtonGroup bgGrupoOpc;
    JTextArea areaExamen, areaResultado, areaPregunta;

    JPanel pExamen,pWestResultado, pEastResultado, pSouthResultado, pResultado, pPregunta, pTextArea, pBotonesPreg, pSurExamen, pNorteExamen, pUsuarioConectado, pInformacion, pOpcMultiple, pBotones, pOpc1, pOpc2, pInfoResultado;

    JLabel lTRestante, lPregRes, lExamen, lTempoRestante, lPregRespondidas, lClienteConectado1, lClienteConectado2, lClienteConectado3,lNumPreg, lInforme, lPregCorrecta, lCalificacion, lCalificacionNum, lPregCorrectaNum; 

    /**
     *Constructor de la clase GUIServidor
     */
    public GUICliente()
    {
        setTitle("Cliente");
        setSize(480, 620);
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
        pUsuarioConectado = new JPanel();

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
        jsExamen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 5),""));
        jsExamen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        

        lExamen = new JLabel();
        
        lTempoRestante = new JLabel("\t\t\t\tTiempo Restante   ");
        lPregRespondidas = new JLabel("\t\t\t\tPreguntas Respondidas  ");
        
        
        lClienteConectado1 = new JLabel(new ImageIcon(getClass().getResource("../imagenes/Rojo.png")));
        lClienteConectado2 = new JLabel(new ImageIcon(getClass().getResource("../imagenes/Rojo.png")));
        lClienteConectado3 = new JLabel(new ImageIcon(getClass().getResource("../imagenes/Rojo.png")));
        pUsuarioConectado.add(lClienteConectado1);  pUsuarioConectado.add(lClienteConectado2);  pUsuarioConectado.add(lClienteConectado3);

        lPregRes = new JLabel("\t \t--");
        lTRestante = new JLabel("\t \t-- : --");
        lPregRes.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lTRestante.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));

        bVerResultado = new JButton("Ver Resultado");
        bResponder = new JButton("Responder");

        //panel 
        pTextArea.add(jsExamen, BorderLayout.NORTH); 
        pTextArea.add(bResponder, BorderLayout.CENTER);

        pInformacion.add(lTempoRestante);pInformacion.add(lPregRespondidas);
        pInformacion.add(lTRestante);pInformacion.add(lPregRes);

        pUsuarioConectado.add(lClienteConectado1);pUsuarioConectado.add(lClienteConectado2);pUsuarioConectado.add(lClienteConectado3);

       pNorteExamen.add(pBotonesPreg);pNorteExamen.add(pTextArea);
       pSurExamen.add(bVerResultado, BorderLayout.NORTH);
       pSurExamen.add(pInformacion, BorderLayout.CENTER);
       pSurExamen.add(pUsuarioConectado, BorderLayout.SOUTH);

        
        //Se añaden los paneles a la pestaña examen
        pExamen.add(pNorteExamen, BorderLayout.NORTH);
        pExamen.add(pSurExamen, BorderLayout.CENTER);
        pExamen.add(pUsuarioConectado, BorderLayout.SOUTH);

        //---------pestaña pregunta-----------------

        //Crear componentes
        
        areaPregunta = new JTextArea(15, 35);
        jsEnunciadoPregunta = new JScrollPane(areaPregunta);
        jsEnunciadoPregunta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
        
        //Se añaden los componentes a la pestaña 'pregunta'
        pPregunta.add(lNumPreg);
        pPregunta.add(jsEnunciadoPregunta);
        pPregunta.add(pOpcMultiple);

       //-------- Pestaña Resultado ---------------

        areaResultado = new JTextArea(15, 35);
        areaResultado.setEditable(false);
        jsResultado = new JScrollPane(areaResultado);
        jsResultado.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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

        //Se añaden los componentes a la pestaña 'Resultado'
        pResultado.add(lInforme);
        pResultado.add(jsResultado);
        pResultado.add(pSouthResultado);

        // -- Eventos -- 
        ManejadoraEvento evento = new ManejadoraEvento();

        //Escucha de botones
        bResponder.addActionListener(evento);
        bCancelarPreg.addActionListener(evento);
        bVerResultado.addActionListener(evento);
        this.addKeyListener(evento);
        
    }

    public void clienteConectado(int numCliente)
    {
        int opcion = numCliente;
        switch (opcion) 
        {
            case 1:
                lClienteConectado1.setIcon(new ImageIcon(getClass().getResource("../imagenes/Verde.png")));
                System.out.println("El cliente "+Integer.toString(opcion)+" se ha conectado");
                break;
            case 2:
                lClienteConectado2.setIcon(new ImageIcon(getClass().getResource("../imagenes/Verde.png")));
                System.out.println("El cliente "+Integer.toString(opcion)+" se ha conectado");
                break;
            case 3:
                lClienteConectado3.setIcon(new ImageIcon(getClass().getResource("../imagenes/Verde.png")));
                System.out.println("El cliente "+Integer.toString(opcion)+" se ha conectado");
                break;
            default:
                System.out.println("No se ha conectado ningun cliente");
                break;
            }     
    }

    /**
     * Función que crea los botones de las preguntas segun el numero de estas 
     * y le añade escucha a los botones
     * @param numPreguntas
     */

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

    public String getNumPreg()
    {
        String[] palabras = lNumPreg.getText().split(" ");
        
        return palabras[1];
    }

    public void setTextAreaPreg(String pregunta)
    {
        areaExamen.setText(pregunta);
    }

    public void setTextPestPreg(String pregunta)
    {
        areaPregunta.setText(pregunta);
    }
    /**
     * funcion que recibe como parametro un booleano que me dice si se quiere bloquear la pregunta o desbloquearla
     * @param valor
     */
    public void bloquearPregunta(boolean valor)
    {
        for(JToggleButton boton : numeroPregunta)
        {
            if(valor)
            {
                if(boton.isSelected())
                {
                    
                    boton.setEnabled(false);
                    System.out.println("El boton "+boton.getText()+ " ha sido bloqueado");
                 }
            }
            else
            {
                if(boton.isEnabled() == false)
                    boton.setEnabled(true);
                    System.out.println("la boton "+ boton.getText()+ "ha sido desbloqueado");
            } 
        }
    }

    public void setEnabled(int indice, boolean bol)
    {
        tpPestanas.setEnabledAt(indice, bol);

    }


    class ManejadoraEvento implements ActionListener,KeyListener, ItemListener
    {
        String answerSelected;

        @Override
        public void actionPerformed(ActionEvent e) {

            for(JToggleButton boton : numeroPregunta)
             {
                if(e.getSource()== boton)
                {
                    ControladorCliente.mostrarPregunta(Integer.parseInt(boton.getText()));
                    //labelNumeroPregunta(boton.getText());
                    System.out.println("El boton "+ boton.getText() +" ha sido seleccionado");
<<<<<<< HEAD
=======
                    
                    

>>>>>>> 2f434bedb0fac9e768c5ce91acd2a9ae402e30b8
                }
            }
            if(e.getSource() == bResponder)
            {
                tpPestanas.setEnabledAt(1, true);
                tpPestanas.setSelectedIndex(1);
                //tpPestanas.setEnabledAt(0, false);
                bloquearPregunta(true); 
<<<<<<< HEAD
=======
                

>>>>>>> 2f434bedb0fac9e768c5ce91acd2a9ae402e30b8
            }
            if(e.getSource() == bCancelarPreg)
            {
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                bloquearPregunta(false);
            }
            if(e.getSource() == bVerResultado)
            {
                tpPestanas.setEnabledAt(2, true);
                tpPestanas.setSelectedIndex(2);
                tpPestanas.setEnabledAt(0, false);
                tpPestanas.setEnabledAt(1, false);

            }
            if(e.getSource() == bResponderPreg)
            {
                ControladorCliente.responderPregunta(answerSelected);
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

		@Override
		public void itemStateChanged(ItemEvent e) 
        {
            if(e.getStateChange() == e.SELECTED)
            {
                if(e.getItemSelectable()==rbOpcA)
                {
                    answerSelected = rbOpcA.getText();
                }
                if(e.getItemSelectable()==rbOpcB)
                {
                    answerSelected = rbOpcB.getText();
                }
                if(e.getItemSelectable()==rbOpcC)
                {
                    answerSelected = rbOpcC.getText();
                }
                if(e.getItemSelectable()==rbOpcD)
                {
                    answerSelected = rbOpcD.getText();
                }
            }
		}

    
    }
}



