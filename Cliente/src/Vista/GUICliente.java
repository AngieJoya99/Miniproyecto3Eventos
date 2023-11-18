package Vista;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
public class GUICliente extends JFrame 

{
    JTabbedPane tpPestanas;
    JPanel pExamen, pResultado, pPregunta, pTextArea, pBotonesPreg, pSurExamen, pNorteExamen;
    JScrollPane jsExamen, jsResultado;
    JButton bResponder, bVerResultado, bCancelarPreg, bOK, B1,B2,B3;
    JToggleButton tbNumPregunta [];
    JRadioButton rbOpc1, rbOpc2, rbOpc3, rbOpc4;
    JTextField tfTempoRestante, tfPregRes;
    ButtonGroup bgGrupoOpc;
    JTextArea areaExamen, areaResultado;

    JLabel lExamen, lTempoRestante, lPregRespondidas, lClienteConectado1, 
    lClienteConectado2, lClienteConectado3,lNumPreg, lEnunciadoPreg, 
    lInforme, lPregCorrecta, lCalificacion; 

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
        //Crear componentes
        tpPestanas = new JTabbedPane();
        pExamen = new JPanel(new BorderLayout());
        pResultado = new JPanel();
        pPregunta = new JPanel();

        tpPestanas.addTab("Examen",pExamen);
        tpPestanas.addTab("Pregunta",pPregunta);
        tpPestanas.addTab("Resultado",pResultado);
        add(tpPestanas);

        pTextArea = new JPanel(new BorderLayout());
        pNorteExamen = new JPanel();
        
        pSurExamen = new JPanel(new GridLayout(2,2));
        pBotonesPreg = new JPanel(new GridLayout(3,1));

    

        B1 = new JButton("1");
        B2 = new JButton("2");
        B3 = new JButton("3");
        

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
        bCancelarPreg = new JButton("Cancelar");
        bResponder = new JButton("Responder");
        bOK = new JButton();

        rbOpc1 = new JRadioButton("Opción A");
        rbOpc2 = new JRadioButton("Opción B");
        rbOpc3 = new JRadioButton("Opción C");
        rbOpc4 = new JRadioButton("Opción D");
        bgGrupoOpc = new ButtonGroup();
        bgGrupoOpc.add(rbOpc1);bgGrupoOpc.add(rbOpc2);
        bgGrupoOpc.add(rbOpc3);bgGrupoOpc.add(rbOpc4);

        pBotonesPreg.add(B1);
        pBotonesPreg.add(B2);
        pBotonesPreg.add(B3);

    
        pTextArea.add(jsExamen, BorderLayout.NORTH); 
        pTextArea.add(bResponder, BorderLayout.SOUTH);
    
       pNorteExamen.add(pBotonesPreg);pNorteExamen.add(pTextArea);
       //pNorteExamen.add(bResponder, BorderLayout.SOUTH);
       
        pSurExamen.add(lTempoRestante);pSurExamen.add(lPregRespondidas);
        pSurExamen.add(tfTempoRestante);pSurExamen.add(tfPregRes);

        //pExamen.add(pBotonesPreg, BorderLayout.WEST);
        pExamen.add(pNorteExamen, BorderLayout.CENTER);
        pExamen.add(pSurExamen, BorderLayout.SOUTH);



    }


    
}



