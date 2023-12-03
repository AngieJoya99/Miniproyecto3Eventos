package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class GUICliente extends JFrame 
{
    private ArrayList <JToggleButton> numeroPregunta;
    JTabbedPane tpPestanas;
    JScrollPane jsPestanaExamen, jsResultado, jsExamen, jsEnunciadoPregunta;
    JButton bResponder, bVerResultado, bCancelarPreg, bOK, bResponderPreg;
    JRadioButton rbOpcA, rbOpcB, rbOpcC, rbOpcD;
    JTextField tfTempoRestante, tfPregRes;
    ButtonGroup bgGrupoOpc;
    JTextArea areaExamen, areaResultado, areaPregunta;

    JPanel pExamen, pResultado, pPregunta, pTextArea, pBotonesPreg, pSurExamen, pNorteExamen, pUsuarioConectado, pInformacion, pOpcMultiple, pBotones, pOpc1, pOpc2, pInfoResultado;

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

        //ponerle orejas a los botones
        escuchaToggleButton();

    
        
        
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

        JPanel pWestResultado = new JPanel(new BorderLayout());
        pWestResultado.add(lCalificacion, BorderLayout.NORTH); 
        pWestResultado.add(lPregCorrecta, BorderLayout.SOUTH); 

       JPanel pEastResultado = new JPanel(new BorderLayout());
        pEastResultado.add(lCalificacionNum, BorderLayout.NORTH);
        pEastResultado.add(lPregCorrectaNum, BorderLayout.SOUTH);

        JPanel pSouthResultado = new JPanel(new BorderLayout());
        pSouthResultado.add(pEastResultado, BorderLayout.EAST);
        pSouthResultado.add(pWestResultado, BorderLayout.WEST);
        pSouthResultado.add(bOK, BorderLayout.SOUTH);




        pResultado.add(lInforme);
        pResultado.add(jsResultado);
        pResultado.add(pSouthResultado);
        



        
    

        







    
    }

    public void escuchaToggleButton()
    {
        ManejadoraEvento evento = new ManejadoraEvento();
        for (int i=0; i<numeroPregunta.size();i++)
        {
            JToggleButton toggleButton = numeroPregunta.get(i);

            toggleButton.addItemListener(evento);
        }
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

    public void labelNumeroPregunta (int numPregunta)
    {
        lNumPreg.setText("Pregunta "+ Integer.toString(numPregunta));
    }

    class ManejadoraEvento implements ActionListener, ItemListener
    {
        @Override
        public void itemStateChanged (ItemEvent e) 
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                for (int i=0; i<numeroPregunta.size(); i++)
                {
                    JToggleButton toggleButton = numeroPregunta.get(i);

                    if(e.getItemSelectable() == toggleButton)
                    {
                        lNumPreg.setText("PREGUNTA "+ Integer.toString(i+1));;
                    }

                }
            }
        
        
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }
}



