   //Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
   /*import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	/**Panel where options for key movements are changed, including drop, rotate, hold, and pause.*/
  //  public class OptionPanel extends JPanel
   //{
		/**Default drop key is space.*/
  //    private int dropKey = KeyEvent.VK_SPACE;
		/**Default rotate key is up.*/
  //    private int rotateKey = KeyEvent.VK_UP;
		/**Default hold key is X.*/
   //   private int holdKey = KeyEvent.VK_X;
		/**Default pause key is P.*/
  //    private int pauseKey = KeyEvent.VK_P;
		/**Spot in the array that designates which of the four keys is to be changed, array is made in the panel's constructor.*/
  //    private int designated = 0;
  //    private JButton dropButton, rotateButton, holdButton, pauseButton, quitButton;
		/**The array in this constructor is filled with the specific key, whatever the key is at the moment.*/
    /*   public OptionPanel(int[] x)
      {
         dropKey = x[0];
         rotateKey = x[1];
         holdKey = x[2];
         pauseKey = x[3];
 
         setLayout(new GridLayout(3,2));
      
         dropButton = new JButton("Hard Drop Key: " + KeyEvent.getKeyText(dropKey));
         dropButton.addActionListener(new dropList());
         add(dropButton);
      	
         rotateButton = new JButton("Rotate Key: " + KeyEvent.getKeyText(rotateKey));
         rotateButton.addActionListener(new rotateList());
         add(rotateButton);
         
         holdButton = new JButton("Hold Key: " + KeyEvent.getKeyText(holdKey));
         holdButton.addActionListener(new holdList());
         add(holdButton);
         
         pauseButton = new JButton("Pause Key: " + KeyEvent.getKeyText(pauseKey));
         pauseButton.addActionListener(new pauseList());
         add(pauseButton);  
      	
         String instructions = "Click desired keybutton then press the new one.";
      	
         JLabel instruct = new JLabel(instructions);
         add(instruct);
      	
         quitButton = new JButton("Main Menu");
         quitButton.addActionListener(new quitList());
         add(quitButton);  

         addKeyListener(new Key());
      }
   	/**Does things depending on which key is pressed.*/
  /*     private class Key extends KeyAdapter
      {
			/**Sets the key as whatever is input into the text box.*/
/*          public void keyPressed(KeyEvent e)
         {
            if (designated == 0)
            {
               dropKey = e.getKeyCode();
               setButton(dropButton, "Hard Drop Key: ", e.getKeyText(dropKey));
            }
            else if (designated == 1)
            {
               rotateKey = e.getKeyCode();
               setButton(rotateButton, "Rotate Key: ", e.getKeyText(rotateKey));
            }		
            else if (designated == 2)
            {
               holdKey = e.getKeyCode();
               setButton(holdButton, "Hold Key: ", e.getKeyText(holdKey));
            }
            else if (designated == 3)
            {
               pauseKey = e.getKeyCode();
               setButton(pauseButton, "Pause Key: ", e.getKeyText(pauseKey));
            }
         }
      }
   	/**Designates the drop key to an index in the array.*/
 /*      private class dropList implements ActionListener
      {
			/**Drop key index is 0.*/
 /*         public void actionPerformed(ActionEvent e)
         {
            designated = 0;
				requestFocus();
         }
      }
		/**Designates the rotate key to an index in the array.*/
   /*    private class rotateList implements ActionListener
      {
			/**Rotate key index is 1.*/
  /*        public void actionPerformed(ActionEvent e)
         {
            designated = 1;
				requestFocus();
         }
      }
		/**Designates the hold key to an index in the array.*/
 /*      private class holdList implements ActionListener
      {
			/**Hold key index is 2.*/
      /*    public void actionPerformed(ActionEvent e)
         {
            designated = 2;
				requestFocus();
         }
      }
		/**Designates the pause key to an index in the array.*/
   /*    private class pauseList implements ActionListener
      {
			/**Pause key index is 3.*/
  /*        public void actionPerformed(ActionEvent e)
         {
            designated = 3;
				requestFocus();
         }
      }
		/**Sets the text of a button.*/
 /*      private void setButton(JButton button, String s, String x)
      {
         button.setText(s + x);
      }
		/**Gets what the keys are now set as.*/
   /*    public int[] getKeys()
      {
         int[] keyarray = {dropKey, rotateKey, holdKey, pauseKey};
         return keyarray;
      }
		/**Exits the Option panel.*/
/*       private class quitList implements ActionListener
      {
			/**Reloads the driver with the new keys.*/
  /*        public void actionPerformed(ActionEvent e)
         {
            TetrisDriver.reload(new MainPanel(getKeys()), "Final Project: Tetris!");
         }
      }   	
   }*/
	/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptionPanel.java
 *
 * Created on Jun 14, 2010, 8:09:22 PM
 */

//package my.optionpanel;
   import java.awt.event.*;
	import javax.swing.*;
	import java.io.*;
/**
 *
 * @author RAYNOR
 */
    public class OptionPanel extends javax.swing.JPanel {
	 	private static TetrisTheme myTheme;
   	/**Spot in the array that designates which of the four keys is to be changed, array is made in the panel's constructor.*/
      private int designated = 0;
		private static int[] myKeys = new int[4];
		private static int startLevel = 1;
    /** Creates new form OptionPanel */
       public OptionPanel() 
		 {
         initComponents();
       }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
       @SuppressWarnings("unchecked")
       // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dropButton = new javax.swing.JButton("Hard Drop Key: " + KeyEvent.getKeyText(myKeys[0]));
        rotateButton = new javax.swing.JButton();
        holdButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TetrisRadio = new javax.swing.JRadioButton();
        MarioRadio = new javax.swing.JRadioButton();
        ZeldaRadio = new javax.swing.JRadioButton();
        PokemonRadio = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(600, 575));
        setMinimumSize(new java.awt.Dimension(600, 575));
        setPreferredSize(new java.awt.Dimension(600, 575));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        dropButton.setBackground(new java.awt.Color(255, 60, 0));
        dropButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dropButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 7));
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });

        rotateButton.setBackground(new java.awt.Color(255, 60, 0));
        rotateButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rotateButton.setText("Rotate Key: " + KeyEvent.getKeyText(myKeys[1]));
        rotateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 7));
        rotateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateButtonActionPerformed(evt);
            }
        });

        holdButton.setBackground(new java.awt.Color(255, 60, 0));
        holdButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        holdButton.setText("Hold Key: " + KeyEvent.getKeyText(myKeys[2]));
        holdButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 7));
        holdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                holdButtonActionPerformed(evt);
            }
        });

        pauseButton.setBackground(new java.awt.Color(255, 60, 0));
        pauseButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pauseButton.setText("Pause Key: " + KeyEvent.getKeyText(myKeys[3]));
        pauseButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 7));
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Click desired button with desired key for change.  Then press new key.");

        TetrisRadio.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(TetrisRadio);
        TetrisRadio.setText("Tetris");
        TetrisRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TetrisRadioActionPerformed(evt);
            }
        });

        MarioRadio.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(MarioRadio);
        MarioRadio.setText("Mario");
        MarioRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarioRadioActionPerformed(evt);
            }
        });

        ZeldaRadio.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(ZeldaRadio);
        ZeldaRadio.setText("Zelda");
        ZeldaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZeldaRadioActionPerformed(evt);
            }
        });

        PokemonRadio.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(PokemonRadio);
        PokemonRadio.setText("Pokemon");
        PokemonRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PokemonRadioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Click desired theme.");

        jTextField1.setText("1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter desired start level.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Check or uncheck.");

        jToggleButton1.setText("Music Off");

        jToggleButton2.setText("Sound Effects Off");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Main Menu");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(holdButton, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(dropButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton2))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(TetrisRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MarioRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ZeldaRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PokemonRadio))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dropButton, holdButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {MarioRadio, TetrisRadio, ZeldaRadio});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jToggleButton1, jToggleButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dropButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(holdButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rotateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addGap(187, 187, 187)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MarioRadio)
                    .addComponent(TetrisRadio)
                    .addComponent(ZeldaRadio)
                    .addComponent(PokemonRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton1)
                            .addComponent(jToggleButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dropButton, holdButton, pauseButton, rotateButton});

    }       private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
      }
   
       private void formKeyPressed(java.awt.event.KeyEvent evt) {
         if (designated == 0)
         {
            myKeys[0] = evt.getKeyCode();
            dropButton.setText("Hard Drop Key: " + KeyEvent.getKeyText(myKeys[0]));
         }
         else if (designated == 1)
         {
            myKeys[1] = evt.getKeyCode();
            rotateButton.setText("Rotate Key: " + KeyEvent.getKeyText(myKeys[1]));
         }
         else if (designated == 2)
         {
            myKeys[2] = evt.getKeyCode();
            holdButton.setText("Hold Key: " + KeyEvent.getKeyText(myKeys[2]));
         }
         else if (designated == 3)
         {
            myKeys[3] = evt.getKeyCode();
            pauseButton.setText("Pause Key: " + KeyEvent.getKeyText(myKeys[3]));
         }        // TODO add your handling code here:
      }
   
       private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {
         designated = 0;
         requestFocus();        // TODO add your handling code here:
      }
   
       private void rotateButtonActionPerformed(java.awt.event.ActionEvent evt) {
         designated = 1;
         requestFocus();        // TODO add your handling code here:
      }
   
       private void holdButtonActionPerformed(java.awt.event.ActionEvent evt) {
         designated = 2;
         requestFocus();        // TODO add your handling code here:
      }
   
       private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {
         designated = 3;
         requestFocus();        // TODO add your handling code here:
      }
   
       private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		 	try 
			{
			startLevel = Integer.parseInt(jTextField1.getText());
			}
			catch (java.lang.NumberFormatException e)
			{
			JOptionPane.showMessageDialog(null, "Double check your start level!");
			return;
			}
			MainPanel.setLevel(startLevel);
		 	MainPanel.setKeys(myKeys);
			MainPanel.setTheme(myTheme);
         TetrisDriver.reload(new MainPanel(false), "Final Project: Tetris!");             
			}
		 private void MarioRadioActionPerformed(java.awt.event.ActionEvent evt) {
       myTheme = new MarioTheme();
      }
       private void TetrisRadioActionPerformed(java.awt.event.ActionEvent evt) {
		 myTheme = new TetrisTheme();
      }
   
       private void ZeldaRadioActionPerformed(java.awt.event.ActionEvent evt) {
		 myTheme = new ZeldaTheme();
      }
   
       private void PokemonRadioActionPerformed(java.awt.event.ActionEvent evt) {
		 myTheme = new PokemonTheme();
      } 
       public int[] getKeys()
      {
         return myKeys;
      }
		public static void setKeys(int[] x)
		{
		myKeys = x;
		}
		public static void setTheme(TetrisTheme theme)
		{
		myTheme = theme;
		}
   
    // Variables declaration - do not modify
      private javax.swing.ButtonGroup buttonGroup1;
      private javax.swing.JButton dropButton;
      private javax.swing.JButton holdButton;
      private javax.swing.JButton jButton5;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel4;
      private javax.swing.JRadioButton TetrisRadio;
      private javax.swing.JRadioButton MarioRadio;
      private javax.swing.JRadioButton ZeldaRadio;
      private javax.swing.JRadioButton PokemonRadio;
      private javax.swing.JTextField jTextField1;
      private javax.swing.JToggleButton jToggleButton1;
      private javax.swing.JToggleButton jToggleButton2;
      private javax.swing.JButton pauseButton;
      private javax.swing.JButton rotateButton;
    // End of variables declaration
   
   }
