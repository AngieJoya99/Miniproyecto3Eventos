/* Angie Joya - 2322609
 * Emily Nuñez - 2240156
 * Sheila Valencia - 2243011
 * Victoria Volveras - 2241874
 */
package Vista;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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

    JLabel lTRestanteH, lTRestanteM, lDosP, lPregRes, lExamen, lTempoRestante, lPregRespondidas, lClienteConectado1, lClienteConectado2, lClienteConectado3,lNumPreg, lInforme, lPregCorrecta, lCalificacion, lCalificacionNum, lPregCorrectaNum; 

    /**
     *Constructor de la clase GUIServidor
     */
    public GUICliente()
    {
        setTitle("Cliente");
        setSize(480, 530);
        crearGUI();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Metodo que permite crear la gui
     */
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
        tpPestanas.setEnabledAt(0, false);
        //establecerBotones(10);
        
        pTextArea = new JPanel(new BorderLayout());
        pInformacion = new JPanel();
        pBotonesPreg = new JPanel(new GridLayout(11,0));

        pNorteExamen = new JPanel();
        pSurExamen = new JPanel(new BorderLayout());
        
        

        areaExamen = new JTextArea("Esperando examen...", 10, 28);
        areaExamen.setEditable(false);
        jsExamen = new JScrollPane(areaExamen);
        jsExamen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));
        jsExamen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        

        lExamen = new JLabel();
        
        lTempoRestante = new JLabel("\t\t\t\tTiempo Restante   ");
        //lPregRespondidas = new JLabel("\t\t\t\t");
        //lPregRes = new JLabel("\t \t");
        lTRestanteH = new JLabel();
        lTRestanteM = new JLabel();
        lDosP = new JLabel();
        //lPregRes.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lTRestanteH.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lTRestanteM.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lDosP.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));

        bVerResultado = new JButton("Ver Resultado");
        //bResponder.setEnabled(false);
        bResponder = new JButton("Responder");

        //panel 
        pTextArea.add(jsExamen, BorderLayout.NORTH); 
        pTextArea.add(bResponder, BorderLayout.CENTER);

        pInformacion.add(lTempoRestante);
        pInformacion.add(lTRestanteH);
        pInformacion.add(lDosP);
        pInformacion.add(lTRestanteM);
        //pInformacion.add(lTRestante);pInformacion.add(lPregRes);
        pNorteExamen.add(pBotonesPreg);pNorteExamen.add(pTextArea);
        pSurExamen.add(bVerResultado, BorderLayout.NORTH);
        pSurExamen.add(pInformacion, BorderLayout.CENTER);
        

        
        //Se añaden los paneles a la pestaña examen
        pExamen.add(pNorteExamen, BorderLayout.NORTH);
        pExamen.add(pSurExamen, BorderLayout.SOUTH);

        //---------pestaña pregunta-----------------

        //Crear componentes
        
        areaPregunta = new JTextArea(15, 35);
        areaPregunta.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));
        jsEnunciadoPregunta = new JScrollPane(areaPregunta);
        jsEnunciadoPregunta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaPregunta.setEditable(false);
        bCancelarPreg = new JButton("Cancelar");
        bResponderPreg = new JButton("Responder");
        lNumPreg = new JLabel("Preguna 1");
        

        rbOpcA = new JRadioButton("Opción a");
        rbOpcB = new JRadioButton("Opción b");
        rbOpcC = new JRadioButton("Opción c");
        rbOpcD = new JRadioButton("Opción d");
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
        areaResultado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));
        areaResultado.setEditable(false);
        jsResultado = new JScrollPane(areaResultado);
        jsResultado.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //lCalificacion = new JLabel("Calificacion");
        //lPregCorrecta = new JLabel("Preguntas Correctas");
        //lCalificacionNum = new JLabel("10");
        //lPregCorrectaNum = new JLabel("20");
        lInforme = new JLabel("Resultado");
        bOK = new JButton("OK");

        //pWestResultado = new JPanel(new BorderLayout());
        
        //pWestResultado.add(lCalificacion, BorderLayout.NORTH); 
        //pWestResultado.add(lPregCorrecta, BorderLayout.SOUTH); 

        //pEastResultado = new JPanel(new BorderLayout());
        //pEastResultado.add(lCalificacionNum, BorderLayout.NORTH);
        //pEastResultado.add(lPregCorrectaNum, BorderLayout.SOUTH);

        //pSouthResultado = new JPanel(new BorderLayout());
        //pSouthResultado.add(pEastResultado, BorderLayout.EAST);
        //pSouthResultado.add(pWestResultado, BorderLayout.WEST);
        //pSouthResultado.add(bOK, BorderLayout.SOUTH);

        //Se añaden los componentes a la pestaña 'Resultado'
        pResultado.add(lInforme);
        pResultado.add(jsResultado);
        pResultado.add(bOK);

        //Dar formato a elementos
        tpPestanas.setBackground(new Color(221,208,220));
        pExamen.setBackground(new Color(221,208,220));
        pResultado.setBackground(new Color(221,208,220));
        pPregunta.setBackground(new Color(221,208,220));
        pUsuarioConectado.setBackground(new Color(221,208,220));
        pTextArea.setBackground(new Color(221,208,220));
        pInformacion.setBackground(new Color(221,208,220));
        pBotonesPreg.setBackground(new Color(221,208,220));
        pNorteExamen.setBackground(new Color(221,208,220));
        pSurExamen.setBackground(new Color(221,208,220));
        pBotones.setBackground(new Color(221,208,220));
        pOpc1.setBackground(new Color(221,208,220));
        pOpc2.setBackground(new Color(221,208,220));
        pOpcMultiple.setBackground(new Color(221,208,220));
        /*
        pBotonesPreg.setBackground(new Color(221,208,220));
        pEastResultado.setBackground(new Color(221,208,220));
        pInfoResultado.setBackground(new Color(221,208,220));
        
        pInformacion.setBackground(new Color(221,208,220));
        
        
        
        
        
        
        pSouthResultado.setBackground(new Color(221,208,220));
        */
        

        // -- Eventos -- 
        ManejadoraEvento evento = new ManejadoraEvento();

        //Escucha de botones
        bResponder.addActionListener(evento);
        bCancelarPreg.addActionListener(evento);
        bVerResultado.addActionListener(evento);
        bResponderPreg.addActionListener(evento);
        bOK.addActionListener(evento);
        rbOpcA.addItemListener(evento);
        rbOpcB.addItemListener(evento);
        rbOpcC.addItemListener(evento);
        rbOpcD.addItemListener(evento);
        this.addKeyListener(evento);

        
    }

    /**
     * Obtiene el tiempo del examen
     * @param duracion tiempo que dura el examen
     */
    public void setHorasRestantes(String duracion)
    {
        lTRestanteH.setText(duracion);

    }

    public void setMinRestantes(String duracion)
    {
        lTRestanteM.setText(duracion);

    }
    

    public void areaInforme(String informe)
    {
        areaResultado.setText(informe);
    }


    /**
     * Permite bloquear o desbloquar una pestaña, dependiendo de el valor de entrada
     * @param indice
     * @param valor si se desbloquea o se bloquea
     */
    public void bloquearPestaña(int indice, boolean valor)
    {
        if(valor)
            tpPestanas.setEnabledAt(indice, false);
        else
            tpPestanas.setEnabledAt(indice, true);
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
            System.out.println("el boton "+numeroPregunta.get(i).getText()+" ha sido creado");
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

    public void setTextPestPreg(String pregunta, String respuesta)
    {
        areaPregunta.setText(pregunta+"\n"+respuesta);
    }
   /**
     * funcion que recibe como parametro un booleano que me dice si se quiere bloquear la pregunta o desbloquearla
     * @param numPreg
     */

     public void bloquearPregunta(String numPreg, boolean valor)
     {
        System.out.println("Entre a bloquearPregunta");
        for(JToggleButton boton : numeroPregunta)
        {
            if(valor == true)
            {
                System.out.println("Entre al if de valor igual a true");
                System.out.println(boton.getText());
                System.out.println(numPreg);
                 if(boton.getText().equals(numPreg))
                {
                    boton.setEnabled(false);
                    pBotonesPreg.updateUI();
                    System.out.println("El boton "+boton.getText()+ " ha sido bloqueado");  
                }
            }
            else
            {
                System.out.println("Entre al if de valor igual a false");
                System.out.println(boton.getText());
                System.out.println(numPreg);
                if(boton.getText().equals(numPreg))
                {
                    boton.setEnabled(true);
                    pBotonesPreg.updateUI();
                    System.out.println("la boton "+ boton.getText()+ " ha sido desbloqueado");
                }
            } 
        }

     }

    public void setEnabled(int indice, boolean bol)
    {
        tpPestanas.setEnabledAt(indice, bol);

    }


    class ManejadoraEvento implements ActionListener,KeyListener, ItemListener
    {
        String answerSelected = "";

        @Override
        public void actionPerformed(ActionEvent e) {

            for(JToggleButton boton : numeroPregunta)
             {
                if(e.getSource()== boton)
                {
                    try
                    {
                        ControladorCliente.mostrarPregunta(Integer.parseInt(boton.getText()));
                        //labelNumeroPregunta(boton.getText());
                    System.out.println("El boton "+ boton.getText() +" ha sido seleccionado");

                    }catch(NumberFormatException ex)
                    {
                        System.out.println("Error al convertir cadena a número: " + ex.getMessage());
                    }
                    

                }
            }
            if(e.getSource() == bResponder)
            {
                
                tpPestanas.setEnabledAt(1, true);
                tpPestanas.setSelectedIndex(1);
                tpPestanas.setEnabledAt(0, false);
                ControladorCliente.enviarBloqueada(getNumPreg(), true);

            }
            if(e.getSource() == bCancelarPreg)
            {
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                ControladorCliente.enviarBloqueada(getNumPreg(), false);
                
            }
            if(e.getSource() == bVerResultado)
            {
                tpPestanas.setEnabledAt(2, true);
                tpPestanas.setSelectedIndex(2);
                tpPestanas.setEnabledAt(0, false);
                tpPestanas.setEnabledAt(1, false);
                ControladorCliente.obtenerInforme();

            }
            if(e.getSource() == bResponderPreg)
            {
                /*ControladorCliente.preguntaRespondida(getNumPreg());
                if(Controlador.examenCompleto() == true)*/
                System.out.println("RESPONDE PREGUNTA");
                bResponder.setEnabled(true);
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                System.out.println("Pregunta selecionada: "+answerSelected);

                ControladorCliente.responderPregunta(answerSelected);


            }
            if(e.getSource()== bOK)
            {
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                tpPestanas.setEnabledAt(2,false);
                bResponder.setEnabled(false);
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