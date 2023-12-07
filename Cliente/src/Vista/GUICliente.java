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

    Font fuente1, fuente2, fuente3, fuenteBotones;
    /**
     *Constructor de la clase GUIServidor
     */
    public GUICliente()
    {
        setTitle("Cliente");
        setSize(510, 570);
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
        
        pTextArea = new JPanel(new BorderLayout());
        pInformacion = new JPanel();
        pBotonesPreg = new JPanel(new GridLayout(11,0));

        pNorteExamen = new JPanel();
        pSurExamen = new JPanel(new BorderLayout());

        fuente1 = new Font("Lato", Font.BOLD, 20);
        fuente2 = new Font("Lato", Font.PLAIN, 16);
        fuente3 = new Font("Lato", Font.BOLD, 13);
        fuenteBotones = new Font("Lato", Font.BOLD, 18);

        areaExamen = new JTextArea("Esperando examen...", 10, 28);
        areaExamen.setEditable(false);
        jsExamen = new JScrollPane(areaExamen);
        jsExamen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));
        jsExamen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        lExamen = new JLabel();
        
        lTempoRestante = new JLabel("\t\t\t\tTiempo Restante   ");
        lTRestanteH = new JLabel();
        lTRestanteM = new JLabel();
        lDosP = new JLabel(":");
        lTRestanteH.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lTRestanteM.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));
        lDosP.setFont(new Font("Hedvig Letters Serif", Font.BOLD, 24));

        bVerResultado = new JButton("Ver Resultado");
        bResponder = new JButton("Responder");
        bResponder.setEnabled(false);
        bVerResultado.setEnabled(false);
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
        jsEnunciadoPregunta = new JScrollPane(areaPregunta);
        jsEnunciadoPregunta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsEnunciadoPregunta.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));
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
        areaResultado.setEditable(false);
        jsResultado = new JScrollPane(areaResultado);
        jsResultado.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsResultado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(171,91,121), 5)));

        lInforme = new JLabel("Resultado");
        bOK = new JButton("OK");

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

        bResponder.setBackground(new Color(157,113,129));
        bResponder.setForeground(Color.WHITE);
        bResponder.setFont(fuenteBotones);
        bCancelarPreg.setBackground(new Color(157,113,129));
        bCancelarPreg.setForeground(Color.WHITE);
        bCancelarPreg.setFont(fuenteBotones);
        bVerResultado.setBackground(new Color(157,113,129));
        bVerResultado.setForeground(Color.WHITE);
        bVerResultado.setFont(fuenteBotones);
        bResponderPreg.setBackground(new Color(157,113,129));
        bResponderPreg.setForeground(Color.WHITE);
        bResponderPreg.setFont(fuenteBotones);
        bOK.setBackground(new Color(157,113,129));
        bOK.setForeground(Color.WHITE);
        bOK.setFont(fuenteBotones);

        lInforme.setFont(fuente1);
        lNumPreg.setFont(fuente1);
        rbOpcA.setFont(fuente2);
        rbOpcA.setBackground(new Color(221,208,220));
        rbOpcB.setFont(fuente2);
        rbOpcB.setBackground(new Color(221,208,220));
        rbOpcC.setFont(fuente2);
        rbOpcC.setBackground(new Color(221,208,220));
        rbOpcD.setFont(fuente2);
        rbOpcD.setBackground(new Color(221,208,220));
        lTempoRestante.setFont(fuenteBotones);
        areaExamen.setFont(fuente2);
        areaPregunta.setFont(fuente2);
        areaResultado.setFont(fuente2);
    
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

        bResponder.setFocusable(false);
        bCancelarPreg.setFocusable(false);
        bVerResultado.setFocusable(false);
        bResponderPreg.setFocusable(false);
        rbOpcA.setFocusable(false);
        rbOpcB.setFocusable(false);
        rbOpcC.setFocusable(false);
        rbOpcD.setFocusable(false);

        setFocusable(true);
        requestFocus(); 
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
        tpPestanas.setEnabledAt(indice, valor);
    }

    public void focalizarPestaña(int indice)
    {
        tpPestanas.setSelectedIndex(indice);
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
            botonPreg.setBackground(new Color(221,208,220));
            botonPreg.setForeground(Color.WHITE);
            botonPreg.setFont(fuenteBotones);
            
            numeroPregunta.add(botonPreg);
            grupoBotonPreg.add(botonPreg);
            System.out.println("el boton "+numeroPregunta.get(i).getText()+" ha sido creado");
            numeroPregunta.get(i).addActionListener( evento);
            pBotonesPreg.updateUI();
            botonPreg.setFocusable(false);
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
        pBotonesPreg.updateUI();
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
    public void bResponderSetEnabled(boolean valor)
    {
        bResponder.setEnabled(valor);
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
                    //boton.setVisible(true);
                    boton.setEnabled(true);
                    pBotonesPreg.updateUI();
                    System.out.println("la boton "+ boton.getText()+ " ha sido desbloqueado");
                }
            } 
        }

     }

    public void bPestExamenSetEnabled(boolean valor)
    {
        bVerResultado.setEnabled(valor);
    }
     public boolean desbloquearBInforme()
     {
        int pregRes=0; 
        for(JToggleButton boton : numeroPregunta)
        {
            if(!boton.isEnabled())
                pregRes+=1;
        }

        if(pregRes == numeroPregunta.size())
            return true;
        else
        {
            return false;
        }
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
                        System.out.println("El boton "+ boton.getText() +" ha sido seleccionado"); 
                        bResponder.setEnabled(true);
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
                System.out.println("LE DI CLIC A CANCELAR");
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                ControladorCliente.enviarBloqueada(getNumPreg(), false);
                System.out.println("ENVIÉ A LA PREGUNTA"+getNumPreg()+"FALSE");
                
            }
            if(e.getSource() == bVerResultado)
            {
                if(desbloquearBInforme())
                {
                    tpPestanas.setEnabledAt(2, true);
                    tpPestanas.setSelectedIndex(2);
                    tpPestanas.setEnabledAt(0, false);
                    tpPestanas.setEnabledAt(1, false);
                    ControladorCliente.obtenerInforme();
                }
                else
                { 
                    System.out.println("El usuario ha seleccionado ver resultado, pero no se han contestado todas las preguntas");
                }


            }
            if(e.getSource() == bResponderPreg)
            {
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
        public void keyPressed(KeyEvent e) 
        {
            if(e.getKeyCode()== KeyEvent.VK_O)
            {
                bResponder.doClick();
            }
            if(e.getKeyCode()==KeyEvent.VK_A)
            {
                rbOpcA.setSelected(true);
            }
            if(e.getKeyCode()==KeyEvent.VK_B)
            {
                rbOpcB.setSelected(true);
            }
            if(e.getKeyCode()==KeyEvent.VK_C)
            {
                rbOpcC.setSelected(true);
            }
            if(e.getKeyCode()==KeyEvent.VK_D)
            {
                rbOpcD.setSelected(true);
            }
            if ((e.getKeyCode() == KeyEvent.VK_R) && (e.getKeyCode() == KeyEvent.VK_ALT))
            {
                bResponder.setEnabled(true);
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                ControladorCliente.responderPregunta(answerSelected);
            }
            if ((e.getKeyCode() == KeyEvent.VK_C) && (e.getKeyCode() == KeyEvent.VK_ALT))
            {
                tpPestanas.setEnabledAt(0, true);  
                tpPestanas.setSelectedIndex(0);
                tpPestanas.setEnabledAt(1,false);
                ControladorCliente.enviarBloqueada(getNumPreg(), false);
            }
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