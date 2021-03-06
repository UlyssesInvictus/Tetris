/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPanel.java
 *
 * Created on Jun 15, 2010, 5:47:48 PM
 */

//package my.mainpanel;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
/**
 *
 * @author RAYNOR
 */
    public class MainPanel extends javax.swing.JPanel {
      private int dropKey = KeyEvent.VK_SPACE;
      private int rotateKey = KeyEvent.VK_UP;
      private int holdKey = KeyEvent.VK_X;
      private int pauseKey = KeyEvent.VK_P;
      private static int[] myKeys = new int[4];
      private OptionPanel optPan;
      private MarathonPanel maraPan;
      private SprintPanel sprinPan;
      private UltraPanel ultraPan;
      private SoundTest clickSound;
      private static TetrisTheme myTheme;
      private static int startLevel;
    /** Creates new form MainPanel */
       public MainPanel(boolean firstOpen)
      {
         initComponents();
         if (firstOpen)
         {
            myKeys[0] = dropKey;
            myKeys[1] = rotateKey;
            myKeys[2] = holdKey;
            myKeys[3] = pauseKey;
            myTheme = new TetrisTheme();
            startLevel = 1;
         }
         clickSound = new SoundTest(myTheme.clearSound);
        
      }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
       @SuppressWarnings("unchecked")
       // <editor-fold defaultstate="collapsed" desc="Generated Code">
       private void initComponents() {
      
         creditButton = new javax.swing.JButton();
         tutoButton = new javax.swing.JButton();
         optButton = new javax.swing.JButton();
         ultraButton = new javax.swing.JButton();
         sprintButton = new javax.swing.JButton();
         maraButton = new javax.swing.JButton();
      
         setBackground(new java.awt.Color(0, 102, 255));
         setMaximumSize(new java.awt.Dimension(600, 560));
         setMinimumSize(new java.awt.Dimension(600, 560));
         setPreferredSize(new java.awt.Dimension(600, 560));
         addMouseListener(
                new java.awt.event.MouseAdapter() {
                   public void mouseClicked(java.awt.event.MouseEvent evt) {
                     formMouseClicked(evt);
                  }
               });
      
         creditButton.setBackground(new java.awt.Color(153, 15, 15));
         creditButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         creditButton.setForeground(java.awt.Color.white);
         creditButton.setText("Credits");
         creditButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         creditButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     creditButtonActionPerformed(evt);
                  }
               });
      
         tutoButton.setBackground(new java.awt.Color(173, 14, 14));
         tutoButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         tutoButton.setForeground(java.awt.Color.lightGray);
         tutoButton.setText("Tutorial");
         tutoButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         tutoButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     tutoButtonActionPerformed(evt);
                  }
               });
      
         optButton.setBackground(new java.awt.Color(194, 12, 12));
         optButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         optButton.setForeground(java.awt.Color.gray);
         optButton.setText("Options");
         optButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         optButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     optButtonActionPerformed(evt);
                  }
               });
      
         ultraButton.setBackground(new java.awt.Color(214, 9, 9));
         ultraButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         ultraButton.setForeground(java.awt.Color.darkGray);
         ultraButton.setText("Ultra");
         ultraButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         ultraButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     ultraButtonActionPerformed(evt);
                  }
               });
      
         sprintButton.setBackground(new java.awt.Color(235, 5, 5));
         sprintButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         sprintButton.setText("Sprint");
         sprintButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         sprintButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     sprintButtonActionPerformed(evt);
                  }
               });
      
         maraButton.setBackground(new java.awt.Color(255, 0, 0));
         maraButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
         maraButton.setText("Marathon");
         maraButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
         maraButton.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     maraButtonActionPerformed(evt);
                  }
               });
      
         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
         this.setLayout(layout);
         layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creditButton, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(tutoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(optButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(ultraButton, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(sprintButton, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(maraButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
            );
         layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(maraButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sprintButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ultraButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tutoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            );
      
         layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {creditButton, maraButton, optButton, sprintButton, tutoButton, ultraButton});
      
      }// </editor-fold>
   
       private void maraButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();    
         maraPan = new MarathonPanel(myKeys, myTheme, startLevel);
         TetrisDriver.reload(maraPan, "Marathon!");        // TODO add your handling code here:
      }
   
       private void sprintButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();    
         sprinPan = new SprintPanel(myKeys, new MarioTheme(), startLevel);
         TetrisDriver.reload(sprinPan, "Sprint!");        // TODO add your handling code here:
      }
   
       private void ultraButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();    
         ultraPan = new UltraPanel(myKeys, new MarioTheme(), startLevel);
         TetrisDriver.reload(ultraPan, "Ultra!");        // TODO add your handling code here:
      }
   
       private void optButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();			
         OptionPanel.setKeys(myKeys);
         OptionPanel.setTheme(myTheme);
         optPan = new OptionPanel();
         TetrisDriver.reload(optPan, "Options!");        // TODO add your handling code here:
      }
   
       private void tutoButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();
         String s = "";
         s+="In the game, shapes composed of four square blocks will fall down in the matrix.";
         s+="\n The player has to either move them sideways or rotate it by 90 degrees." ;
         s+="\n and try to create a horizontal line of blocks without gaps. When this horizontal";
         s+="\n line is created, the line disappears. The goal of this game is to clear as many";
         s+="\n lines as possible.You lose when the blocks touches the top of the matrix.";
         s+="\n There are multiple variations of this game.";
         s+="\n In Marathon mode, you try to go on as long as possible without losing.";
         s+="\n In Sprint mode, you are trying to clear 40 lines as fast as possible.";
         s+="\n In Ultra mode, you are trying to clear as many lines possible in 2 minutes.";
         s+="\n Press 1,2,3, or 4 to change music.";
      
         JOptionPane.showMessageDialog(null, s);        // TODO add your handling code here:
      }
   
       private void creditButtonActionPerformed(java.awt.event.ActionEvent evt) {
         clickSound.play();    
         String s = "";
         s += "Created by Raynor Kuang, Jihun Kim, and Yash Maniar, at TJHSST, 2010.";
         s += "\n Many thanks to all the people that bug tested this program!";
         s += "\n Tetris was created by Alexey Pazhitnov in 1984, so all credit for its invention goes to him!";
         s += "\n Manhours slaved away on this project: over 60";
         s += "\n Lines of code in the program: over 5000.";
         s += "\n Getting an A+: Priceless. (Or for certain ethnic groups, more manhours of studying)";
         JOptionPane.showMessageDialog(null, s);        // TODO add your handling code here:
      }
   
       private void formMouseClicked(java.awt.event.MouseEvent evt) {
       // TODO add your handling code here:
      }
    
   
       public static void setKeys(int[] newKeys)
      {
         myKeys = newKeys;
      }
       public static void setTheme(TetrisTheme theme)
      {
         myTheme = theme;
      }
       public static void setLevel(int level)
      {
         startLevel = level;
      }
       public void paint(Graphics g)
      {
         ImageIcon tetrisTitle = new ImageIcon("TetrisTitle.gif");
			
         super.paint(g);
			
			
         g.drawImage(tetrisTitle.getImage(), 300 - (tetrisTitle.getIconWidth() / 2), 10, getParent());
      }
   
    // Variables declaration - do not modify
      private javax.swing.JButton creditButton;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JButton maraButton;
      private javax.swing.JButton optButton;
      private javax.swing.JButton sprintButton;
      private javax.swing.JButton tutoButton;
      private javax.swing.JButton ultraButton;
    // End of variables declaration
   
   }
