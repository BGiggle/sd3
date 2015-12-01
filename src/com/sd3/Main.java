package com.sd3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
    private GameThread gt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][][]", "[grow][grow][grow][grow][grow][grow]"));
		
		JButton bStart = new JButton("Start");
		frame.getContentPane().add(bStart, "flowy,cell 1 0,alignx center,aligny center");
		
		JButton bPause = new JButton("Pause");
		frame.getContentPane().add(bPause, "cell 2 0,alignx center");
		
		GameWorldPanel gameWorldPanel = new GameWorldPanel();
		gameWorldPanel.setOpaque(true);

		frame.getContentPane().add(gameWorldPanel, "cell 0 0 1 6,grow");
		
		JButton bMove = new JButton("Make Move");
		frame.getContentPane().add(bMove, "cell 2 1");
		
		JButton bUndo = new JButton("Undo Move");
		frame.getContentPane().add(bUndo, "cell 1 2,alignx center");
		
		JButton bRedo = new JButton("Redo Move");
		frame.getContentPane().add(bRedo, "cell 2 2,alignx center");
		
		JLabel lblOgreDiet = new JLabel("Ogre Diet");
		frame.getContentPane().add(lblOgreDiet, "cell 1 3,alignx trailing");
		
		JComboBox ogreDiet = new JComboBox();
		frame.getContentPane().add(ogreDiet, "cell 2 3,growx");
		
		JLabel lblWidth = new JLabel("Width");
		frame.getContentPane().add(lblWidth, "cell 1 4,alignx trailing");
		
		JComboBox numberOfTilesX = new JComboBox();
		frame.getContentPane().add(numberOfTilesX, "cell 2 4,growx");
		
		JLabel lblHeight = new JLabel("Height");
		frame.getContentPane().add(lblHeight, "cell 1 5,alignx trailing");
		
		JComboBox numberOfTilesY = new JComboBox();
		frame.getContentPane().add(numberOfTilesY, "cell 2 5,growx");
		

	    ActionListener pausePressed = new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gt.setGameRunning(!gt.isGameRunning());
	            if(gt.isGameRunning()){
	                new Thread(gt).start();
	                bPause.setText("Pause");
	            }else{
	                bPause.setText("Unpause");
	            }
	        }
	    };

	    ActionListener movePressed = new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(gt == null){
	                gt = new GameThread(gameWorldPanel, bUndo, bRedo);
	            }
	            gt.moveAndDraw();
	        }
	    };

	    ActionListener undoPressed = new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gt.undo();
	        }
	    };

	    ActionListener redoPressed = new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gt.redo();
	        }
	    };
	    GlobalParams.getGlobalParamsInstance();

        GlobalParams.setWidth(4);
        GlobalParams.setHeight(4);

        for(int i = 2; i < 11; i++){
            numberOfTilesY.addItem(i);
            numberOfTilesX.addItem(i);
        }
        numberOfTilesY.setSelectedIndex(3);
        numberOfTilesX.setSelectedIndex(3);


        ogreDiet.addItem("Knights in Shining Armour");
        ogreDiet.addItem("Ogre Enemies");
        ogreDiet.addItem("Very Big Macs");

        ActionListener startPressed = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                gt = new GameThread(gameWorldPanel, bUndo, bRedo);
                gt.setGameRunning(true);
                new Thread(gt).start();
                bPause.setVisible(true);
                bPause.setText("Pause");
            }
        };
        
        ogreDiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalParams.setOgreDiet((String)ogreDiet.getSelectedItem());
            }
        });

        numberOfTilesY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalParams.setHeight((int)numberOfTilesY.getSelectedItem());
                gameWorldPanel.repaint();
            }
        });

        numberOfTilesX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalParams.setWidth((int)numberOfTilesX.getSelectedItem());
                gameWorldPanel.repaint();
            }
        });

        bStart.addActionListener(startPressed);
        bPause.addActionListener(pausePressed);
        bMove.addActionListener(movePressed);
        bUndo.addActionListener(undoPressed);
        bRedo.addActionListener(redoPressed);

        bUndo.setVisible(false);
        bRedo.setVisible(false);
        bPause.setVisible(false);
	    
	}
}
