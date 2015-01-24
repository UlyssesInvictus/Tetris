   //Name: Raynor Kuang, Yash Maniar, JiHun Kim.  Date: 6/8/10
   import java.util.Arrays;//sorts
	//I don't just use util.* because there is another Timer class in it.  Right now, it's easier to just 
	//avoid confusing the compiler by specifying the class.
/****************************Graphics Packages***************************/
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
//**************************Sound Packages********************************
   import javax.sound.midi.InvalidMidiDataException;
   import javax.sound.midi.MidiSystem;
   import javax.sound.midi.MidiUnavailableException;
   import javax.sound.midi.MetaEventListener;
   import javax.sound.midi.MetaMessage;
   import javax.sound.midi.Sequence;
   import javax.sound.midi.Sequencer;
   import javax.sound.midi.Synthesizer;
   import javax.sound.midi.Receiver;
   import javax.sound.midi.Transmitter;
//*************************Files, Scanner, PrintStream, Exceptions Packages*****************
   import java.io.*;
   import java.util.Scanner;
/***********   Graphics Package:  Credit goes to javaranch.com.***********
							Used Only for the drawBevel method                */   
   import com.javaranch.common.AWT;
/**Initialize the Marathon Panel*/
    public class MarathonPanel extends JPanel
   {	
   //********************Set the graphics of the panel*******************
      public  final int FRAME = 400;
      public  final Color BACKGROUND = new Color(204, 204, 204);
      public  BufferedImage myImage;
      public  Graphics myBuffer;
      public ScoreBoard scoreboard;
   //********************Used for Mechanics**************************
      public  TetradV2 tetrad;
      public  Block[][] matrix;
      public boolean gameOver = false;
      public ActionListener Listener;
   //***********Used for Clearing Rows and Making New Pieces***********
      public  boolean readyNew = false;
      public boolean rowCleared = false;
      public  boolean[] readyDropRow = new boolean[20];
      public  int[] scrambled;
      public  int[] tetradTypes; 
      public  int tetradNum = 1;
   //****************Used to increment speed and drop rate**********
      public Timer t, t2, maraTimer;
      public  int pace;
      public  int time = 1000;
      public int timePassed = 0;
      public  Timer bottomTimer = new Timer(1000, new BottomListener());
   //**********************Used for Holding Pieces*********************
      public  boolean hasHold = false;
      public  int justHeld = -1;
      public  TetradV2 holdtetrad, temp, next;
      public  boolean hardDropped = false;
   //***********************Used for SCore******************************
      public  int score = 0;
      public  int level = 1;
      public  int levGoal = 5;
      public  int linesCleared = 0;
      public  int multiplier1 = 1;
      public  int multiplier2 = 1;
      public Highscore[] scores;
      public ActionListener TimedListener, maraListener;
   //*************************For Pausing**********************************
      public  boolean paused = false;
      public JOptionPane pauseScreen;
   //***********************For Music and Sound***********************
      public int currentMusic = 1;
      public SoundClass[] musicArray = new SoundClass[4];
      public SoundClass gameOverMusic;
      public SoundTest clearSound;
      public SoundTest tetrisSound;
      public SoundTest dropSound = new SoundTest("dropSound.wav");
      public TetrisTheme myTheme;
      public int[] myKeys;
      public Timer debugChecker;
   /**Constructor (Initialize above references)*/
       public MarathonPanel(int[] x, TetrisTheme theme, int startLevel)
      {
      //********************Set the graphics of the panel*******************
         myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         myBuffer.setColor(BACKGROUND);
         myBuffer.fillRect(0, 0, FRAME, FRAME);
         scoreboard = new ScoreBoard();
         setLayout(new BorderLayout());
         add(scoreboard, BorderLayout.EAST);
      /*Make an array of possible choices (0 to 6).  Then, scramble them to create a
      random list of pieces.  This will be done whenever necessary. */
         scrambled  = new int[7];
         for (int k = 0; k < 7; k++)
            scrambled[k] = k;
         tetradTypes = scramble(scrambled);
      //******************Initialize the main piece*********************
         tetrad = new TetradV2(tetradTypes[0]);
         tetrad.setPosition();
      //******************Initialize the grid***************************
         matrix = new Block[20][10];
         for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[0].length; c++)
               matrix[r][c] = new Block(r,c,Color.black);
      //*****************Set the timers going*************************
         t = new Timer(1, new Listener());
         t.start();//Draws the frame
         t2 = new Timer(1000, new TimedListener());
         t2.start();//Makes pieces drop slowly
         maraTimer = new Timer(1000, new maraListener());
         maraTimer.start();
         myTheme = theme;
         for (int k = 0; k < 4; k++)
         {
            if (theme.songArray[k].endsWith("wav"))
               musicArray[k] = new SoundTest(theme.songArray[k]);
            else
               musicArray[k] = new MidiPlayer(theme.songArray[k]);
         }
         if (theme.gameOver.endsWith("wav"))
            gameOverMusic = new SoundTest(theme.gameOver);
         else
            gameOverMusic = new MidiPlayer(theme.gameOver);
         clearSound = new SoundTest(theme.clearSound);
         tetrisSound = new SoundTest(theme.tetrisSound);
         musicArray[0].loop();//Play beginning music    
         myKeys = x;
         scores = getScores("marascores.txt"); 
         level = startLevel;
         levGoal = 15 * level;
         linesCleared = 15 * (level - 1);
         for (int k = 2; k <= level; k ++)
         {
            if (time > 100)
            {
               t2.setDelay(time-100);
               bottomTimer.setDelay(time-100);
               time = time - 100;
            }
            else if (time > 50)
            {
               t2.setDelay(time-10);
               bottomTimer.setDelay(time-10);
               time = time - 10;
            }
            else if (time > 25)
            {
               t2.setDelay(time-5);
               bottomTimer.setDelay(time-5);
               time= time - 5;
            }
            else if (time > 2)
            {
               t2.setDelay(time-1);
               bottomTimer.setDelay(time-100);
               time = time - 1;
            }
         }
         addKeyListener(new Key());//Add keyboard
         setFocusable(true);//Make panel visible
         
      	
      	
         debugChecker = new Timer(1, new debugList());
      }
       public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   	/**Key does things whenever a certain key is pressed.*/
       public class Key extends KeyAdapter
      {
         /**For all music that plays, we also have to stop any music currently playing. Starts the music.*/
          public void keyPressed(KeyEvent e)
         { 	
            if(e.getKeyCode() == KeyEvent.VK_T)//Play tetris music
            {
               tetrisSound.play();
            }
            if(e.getKeyCode() == KeyEvent.VK_1)//Play tetris music
            {
               stopMusic(currentMusic);
               startMusic(1);
            }
            if(e.getKeyCode() == KeyEvent.VK_2)//Play mario music            
            {
               stopMusic(currentMusic);
               startMusic(2);
            }
            if(e.getKeyCode() == KeyEvent.VK_3)//Play zelda music
            {
               stopMusic(currentMusic);
               startMusic(3);
            }
            if(e.getKeyCode() == KeyEvent.VK_4)//Play pokemon music
            {
               stopMusic(currentMusic);
               startMusic(4);
            }
            if(e.getKeyCode() == myKeys[3])
            { 			
               scoreboard.pauseGame();
            //Pause the game (If not paused, which is why there is the "paused" boolean).
            }  
            if(e.getKeyCode() == myKeys[2])
               hold();
            if(e.getKeyCode() == myKeys[0])
               hardDrop();
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
               moveLeft(tetrad);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
               moveRight(tetrad);
            }           
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
               moveDown(tetrad);
            }
            if(e.getKeyCode() == myKeys[1])
            {
            /*The longest code sequence in the project.  It has to check for every single 
            piece of every single orientation whether or not they can rotate.  Some situations
            will repeat, so I will only describe those once*/
               int loopbreaker = 0;//Used later
               int Ypos = tetrad.getY();
               int Xpos = tetrad.getX();
               boolean canRotate = true;
               switch(tetrad.getType())
               {
                  case 0:
                     switch (tetrad.getRotation()%2)
                     {
                        case 0:
                        //Should the piece be lined up against the right wall, 
                        //it will move left if it can
                           if (tetrad.getX()==9||tetrad.getX()==8||tetrad.getX()==7)
                              while (tetrad.getX()==9||tetrad.getX()==8||tetrad.getX()==7)
                              {
                                 loopbreaker++;
                                 if (loopbreaker>3)
                                 {
                                    loopbreaker = 0;
                                    tetrad.setX(Xpos);//If it has moved, but the movement is useless, restores orginal position.
                                    break;
                                 }
                                 moveLeft(tetrad);	
                              }
                           /*Does the same as above, but uses a "wall" of pieces.
                           They aren't in the same if-statement because an ArrayOutofBounds Exception
                           might be thrown should the piece be against the regular wall, so the above handles it.
                           */
                           else
                              while (tetrad.getX()+3 > 9 || matrix[tetrad.getY()][tetrad.getX()+1].getFilled()==true || matrix[tetrad.getY()][tetrad.getX()+2].getFilled()==true || matrix[tetrad.getY()][tetrad.getX()+3].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker>3)
                                 {
                                    loopbreaker = 0;
                                    tetrad.setX(Xpos);
                                    break;
                                 }
                                 if (tetrad.getX() > 0 && matrix[tetrad.getY()][tetrad.getX()-1].getFilled()==false)
                                 {
                                    moveLeft(tetrad);
                                 }	
                              }
                        //Does the same, but moves up instead to get out of the way
                           while (tetrad.getX()+3 > 19 ||  matrix[tetrad.getY()][tetrad.getX()+1].getFilled()==true || matrix[tetrad.getY()][tetrad.getX()+2].getFilled()==true || matrix[tetrad.getY()][tetrad.getX()+3].getFilled()==true)
                           {
                              loopbreaker++;//Should the piece never be able to get out, this will break the loop
                              if (loopbreaker>3)
                              {
                                 loopbreaker = 0;
                                 tetrad.setY(Ypos);
                                 canRotate = false;//Says the piece cannot rotate
                                 break;
                              }
                              moveUp(tetrad);	
                           }
                           break;
                        case 1:
                           while (tetrad.getY() < 3|| matrix[tetrad.getY()-1][tetrad.getX()].getFilled()==true || matrix[tetrad.getY()-2][tetrad.getX()].getFilled()==true || matrix[tetrad.getY()-3][tetrad.getX()].getFilled()==true)
                              if (loopbreaker > 3)
                              {
                                 loopbreaker = 0;
                                 tetrad.setY(Ypos);
                                 canRotate = false;//Says the piece cannot rotate
                                 break;
                              }
                           moveDown(tetrad);	
                           break;
                     }
                     break;
                  case 1:
                     switch(tetrad.getRotation()%4)
                     {
                        case 0:
                        //Tries to move down from top if it can.  Else says piece can't rotate
                           if (tetrad.getY() == 1)/*Actually, since the piece is already set as below the ceiling,
                           this is mostly useless.  However, we leave it in for further versions and unseen situations*/
                           {
                              if (canDown(tetrad)==true)            
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }
                        	
                        //Does same, but with blocks as wall instead
                           while (matrix[tetrad.getY()-2][tetrad.getX()].getFilled()==true)
                           {
                              loopbreaker++;
                              if (loopbreaker > 2)
                              {
                                 canRotate = false;
                                 loopbreaker = 0;
                                 break;
                              }
                              moveDown(tetrad);
                           }
                           break;
                        case 1:
                           if (tetrad.getX()==9)
                              if(canLeft(tetrad))
                                 moveLeft(tetrad);
                              else
                                 canRotate = false;
                           else if (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                              if(canLeft(tetrad))
                                 moveLeft(tetrad);
                              else
                                 canRotate = false;
                           else							
                              while (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);     
                              }
                        case 2:
                           if (tetrad.getY()==19)
                              if (canUp(tetrad)==true)
                              {}                      
                              	//moveUp(tetrad);
                              else
                                 canRotate=false;
                           break;
                        case 3:
                           if (tetrad.getX()==0)
                              moveRight(tetrad);
                           else if (matrix[tetrad.getY()-1][tetrad.getX()-1].getFilled()==true)
                              moveRight(tetrad);
                           else
                              while (matrix[tetrad.getY()-1][tetrad.getX()-1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }								
                     }
                     break;
                  case 3:
                     switch(tetrad.getRotation()%4)
                     {
                        case 0:
                           if (tetrad.getY() == 1)
                              moveDown(tetrad);
                           if (tetrad.getX()==9)
                           {
                              moveLeft(tetrad);
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                              if (canLeft(tetrad))
                                 moveLeft(tetrad);
                              else
                                 canRotate=false;
                           else
                              while (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                           if (canRotate == true)
                              moveLeft(tetrad);//Shifts the piece a bit so that it rotates in place
                           break;
                        case 1:
                           if (tetrad.getY()==1)
                              moveDown(tetrad);
                           else if (tetrad.getY()==19)
                           {
                              if (canUp(tetrad))
                                 moveUp(tetrad);
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()+1][tetrad.getX()+1].getFilled()==true)
                              if (canUp(tetrad))
                                 moveUp(tetrad);
                              else
                                 canRotate = false;
                           if (canRotate == true)
                           {
                              moveRight(tetrad);
                           }
                           break;
                        case 2:
                           if (tetrad.getX()==9)
                           {
                              moveLeft(tetrad);
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true||matrix[tetrad.getY()-2][tetrad.getX()+1].getFilled()==true)
                              if (canLeft(tetrad))                    
                                 moveLeft(tetrad);
                              else
                                 canRotate=false;
                           else
                              while (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true||matrix[tetrad.getY()-2][tetrad.getX()+1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                           if (canRotate == true)
                           {               
                              moveLeft(tetrad);
                           }
                           break;
                        case 3:
                           if (tetrad.getY() == 0)
                           {
                              if(canDown(tetrad)==true)
                                 moveDown(tetrad);
                              else 
                                 canRotate=false;
                              if(canDown(tetrad)==true)
                                 moveDown(tetrad);
                              else 
                                 canRotate=false;
                           }
                           else if (tetrad.getY()==1)
                           {
                              if(canDown(tetrad)==true)
                                 moveDown(tetrad);
                              else 
                                 canRotate=false;
                           }
                           if (canRotate==true)
                              moveRight(tetrad);
                           break;
                     }
                     break;
                  case 4:
                     switch(tetrad.getRotation()%4)
                     {
                        case 0:
                           if (tetrad.getX()==8)
                           {
                              if (canLeft(tetrad)==true)
                                 moveLeft(tetrad);
                              else 
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()+2].getFilled()==true)
                              if (canLeft(tetrad)==true)
                                 moveLeft(tetrad);
                              else 
                                 canRotate = false;
                           else
                              while (matrix[tetrad.getY()][tetrad.getX()+2].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 5)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                        	
                           if (canRotate == true)
                           {
                              moveRight(tetrad);
                              moveRight(tetrad);
                           }
                           break;
                        case 1:
                           if (tetrad.getY() == 19)
                           {
                              if (canUp(tetrad)==true)
                                 moveUp(tetrad); 
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()+1][tetrad.getX()-1].getFilled()==true)
                           {
                              if (canUp(tetrad)==true)
                                 moveUp(tetrad);
                              else
                                 canRotate = false;
                           }
                        		
                           if (canRotate == true)    
                           {                     
                              moveLeft(tetrad);
                              moveDown(tetrad);
                           }
                           break;
                        case 2:
                           if (tetrad.getX()==0)
                           {
                              if (canRight(tetrad)==true)
                                 moveRight(tetrad);
                              else
                                 canRotate=false;
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()-1].getFilled()==true)
                           {
                              if (canRight(tetrad)==true)
                                 moveRight(tetrad);
                              else
                                 canRotate=false;
                           }
                           else
                              while (matrix[tetrad.getY()][tetrad.getX()-1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                           if (canRotate == true)
                           {
                              moveUp(tetrad);
                              moveRight(tetrad);
                           }
                           break;
                        case 3:
                           if (tetrad.getY() == 1)
                           {
                              if (canDown(tetrad))
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()-2][tetrad.getX()-1].getFilled()==true)
                           {
                              if (canDown(tetrad))
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }
                        		
                           if (canRotate == true)    
                           {                     
                              moveLeft(tetrad);
                              moveLeft(tetrad);
                           }
                           break;									
                     }
                     break;  
                  case 5:
                     switch(tetrad.getRotation()%2)
                     {  
                        case 0:          
                           if (tetrad.getY() == 1)
                           {
                              if (canDown(tetrad))
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()].getFilled()==true||matrix[tetrad.getY()-2][tetrad.getX()].getFilled()==true)
                           {
                              if (canDown(tetrad)==true)
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }	
                           if (canRotate == true)                
                              moveRight(tetrad);
                           break;
                        case 1:
                           if (tetrad.getX()==9)
                           {
                              if (canLeft(tetrad)==true)
                                 moveLeft(tetrad);
                              else
                                 canRotate = false;  
                           }                         
                           else if (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                           {
                              if (canLeft(tetrad)==true)
                                 moveLeft(tetrad);
                              else
                                 canRotate = false;
                           }
                           else
                              while (matrix[tetrad.getY()-1][tetrad.getX()+1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 {
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                           if (canRotate == true)
                              moveLeft(tetrad);
                           
                           break;
                     }
                     break;	
               
                  case 6:
                     switch(tetrad.getRotation()%2)
                     {  
                        case 0:        
                           if (tetrad.getY() == 1)
                           {
                              if (canDown(tetrad)==true)
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()-2][tetrad.getX()].getFilled()==true)
                           {
                              if (canDown(tetrad))
                                 moveDown(tetrad);
                              else
                                 canRotate = false;
                           }	
                        
                           if (canRotate == true)                           	
                              moveLeft(tetrad);
                           break;
                        case 1:
                           if (tetrad.getX()==0)
                           {
                              if (canRight(tetrad))
                                 moveRight(tetrad);
                              else
                                 canRotate = false;
                           }
                           else if (matrix[tetrad.getY()-1][tetrad.getX()-1].getFilled()==true)
                           {
                              if (canRight(tetrad))
                                 moveRight(tetrad);
                              else
                                 canRotate = false;
                           }
                           else
                              while (matrix[tetrad.getY()-1][tetrad.getX()-1].getFilled()==true)
                              {
                                 loopbreaker++;
                                 if (loopbreaker > 18)
                                 { 
                                    canRotate = false;
                                    loopbreaker = 0;
                                    tetrad.setY(Ypos);
                                    break;
                                 }
                                 moveUp(tetrad);
                              }
                           if (canRotate == true)
                              moveRight(tetrad);
                           break;
                     }
                     break;	
               }
               if (canRotate==true)
               {
                  tetrad.rotate();
                  if (bottomTimer.isRunning())
                     bottomTimer.restart();
               }
            }
         }	
      }
       public class Listener implements ActionListener
      {
      	/**Performs the actions of the game not involving actual gameplay, such as tetrad rotation.*/
          public void actionPerformed(ActionEvent e)
         {	 
            if (gameOver==false)//For the entire code of Listener, action only performed if not gameOver
            {          
               scoreboard.updateLabels();
               if (readyNew == true)
               //boolean used for checking if previous piece hit bottom and new piece is ready
               {
               //Gameover sequence
                  gameOver();                 	
               //A tricky solution-Because holding a piece automatically makes a new one
               //justHeld has to have two steps before saying a piece was not justHeld.
               //justHeld is used to keep people from holding infinitely
                  if (justHeld == 1 || justHeld == 0)
                     justHeld--;
                  tetrad = new TetradV2(tetradTypes[tetradNum]);//Knows which piece to get next because of array
                  tetrad.setPosition();
                  tetradNum++;
                  if (tetradNum == 7)//Rescrambles the array when it's been run through
                  {
                     tetradTypes = scramble(scrambled);
                     tetradNum = 0;
                  }
                  readyNew = false;
               }
               setColors();
               multiplier1 = 1;//Resets the multiple line clear multiplier
            
            //************************Resets frame**********************************
               myBuffer.setColor(BACKGROUND);
               myBuffer.fillRect(0,0,FRAME,FRAME);
            //*********************************Makes pieces above cleared rows fall********************************
               for (int k = 0; k < matrix.length; k++)
                  if (readyDropRow[k]==true)
                  {
                     boolean foundFirst = false;
                     int fallDist = 0;
                     for (int r = k - 1; r >=0; r--)
                        for (int c = 0; c < matrix[0].length; c++)
                        {
                           if (matrix[r][c].getFilled()==true&&foundFirst==false)//Checks row by row to find first piece
                           																//above cleared row
                           {
                              fallDist = r;//records that row
                              foundFirst = true;//keeps from constantly rerecording row 
                           }
                        }
                     if (foundFirst==true)
                     {
                        for (int r = 0; r < fallDist; r++)
                        {
                           for(int c = 0; c < matrix[0].length; c++)//From that row onwards, pieces "fall"
                           {
                              matrix[k-r][c].setFilled(matrix[fallDist-r][c].getFilled());
                              matrix[k-r][c].setColor(matrix[fallDist-r][c].getColor());
                              matrix[fallDist-r][c].setFilled(false);							
                           }
                        }			
                     }			               
                  }		
            //*************************************Checks if bottom is hit******************************
               boolean hit = false;
               for (int k = 0;k < 4;k++)
                  if ((tetrad.getBlocks()[k]).getY() == 19 || matrix[(tetrad.getBlocks()[k]).getY()+1][(tetrad.getBlocks()[k]).getX()].getFilled()==true)
                     hit = true;
               if (hit==true)
               {
                  if (hardDropped == false)//If piece is hardDropped, it locks instantly (next case)
                     bottomTimer.start();
                  else
                  {
                     readyNew = true;
                     setFilleds();
                     if (rowCleared == false)//Sets combo multiplier back to one
                        multiplier2 = 1;
                     else//Or increments it
                        multiplier2++;
                  }
               }
               else 
               {
                  bottomTimer.stop();//Since the bottomTimer won't stop itself after it's action,
               						// this stops the count when it finally does
               }
               hardDropped = false;
               setColors();				
               for (int k = 0;k < matrix.length; k++)
                  readyDropRow[k] = false;        //Done after above so the scoring works
               rowCleared = false;
               if (linesCleared >= levGoal)//Sets all the timers
               {
                  level++;
                  levGoal = level * 15;//Sets the number of lines needed to win based on the level
                  if (time > 100)
                  {
                     t2.setDelay(time-100);
                     bottomTimer.setDelay(time-100);
                     time = time - 100;
                  }
                  else if (time > 50)
                  {
                     t2.setDelay(time-10);
                     bottomTimer.setDelay(time-10);
                     time = time - 10;
                  }
                  else if (time > 25)
                  {
                     t2.setDelay(time-5);
                     bottomTimer.setDelay(time-5);
                     time= time - 5;
                  }
                  else if (time > 2)
                  {
                     t2.setDelay(time-1);
                     bottomTimer.setDelay(time-100);
                     time = time - 1;
                  }
               }
            //**********************Draws the held piece********************************************	
               if (hasHold == true)
                  for (int k = 0; k < 4; k++)
                  {
                     myBuffer.setColor(holdtetrad.getColor());
                     holdtetrad.setPosition();
                     Rectangle hold = new Rectangle(20 * (holdtetrad.getBlocks()[k].getX()) + 150, 20 * (holdtetrad.getBlocks()[k].getY()) + 300, 20, 20);
                     myBuffer.fillRect(20 * (holdtetrad.getBlocks()[k].getX()) + 150, 20 * (holdtetrad.getBlocks()[k].getY()) + 300, 20, 20);
                     AWT.drawBeveledRect(myBuffer, hold, 5, true, holdtetrad.getColor());
                  //AWT is from the javaranch package.  drawBeveledRect sets the raised appearance of the pieces.          
                  }
            //**********************Draws the next piece**************************************************
               next = new TetradV2(tetradTypes[tetradNum]);
               for (int k = 0; k < 4; k++)
               {
                  myBuffer.setColor(next.getColor());
                  next.setPosition();
                  Rectangle nextRect = new Rectangle(20 * (next.getBlocks()[k].getX()) + 150, 20 * (next.getBlocks()[k].getY()) + 50, 20, 20);
                  myBuffer.fillRect(20 * (next.getBlocks()[k].getX()) + 150, 20 * (next.getBlocks()[k].getY()) + 50, 20, 20);
                  AWT.drawBeveledRect(myBuffer, nextRect, 5, true, next.getColor());
               }
            //**********************Checks if a row is ready for clearing**********************************
               for (int r = 0; r < matrix.length; r++)
                  checkClear(r);
            //************************Fills in the matrix*************************************************
               for (int r = 0; r < matrix.length; r++)
                  for (int c = 0; c < matrix[0].length; c++)
                  {
                     Rectangle bevel = new Rectangle(20 * c, 20 * r, 20, 20);
                     myBuffer.setColor(matrix[r][c].getColor());
                     myBuffer.fillRect((int)bevel.getX(),(int) bevel.getY(), 20, 20);
                     AWT.drawBeveledRect(myBuffer, bevel, 5, true, matrix[r][c].getColor());
                     if (matrix[r][c].getFilled() == false) 
                        matrix[r][c].setColor(Color.black);
                  }   
            //******************Draws the text in the scoreboard****************************
               myBuffer.setColor(Color.black);
               myBuffer.drawString("Hold Piece: ", 230, 280);
               myBuffer.drawString("Next Piece: ", 230, 20);
            //******************Draws the lines***********************************************
               myBuffer.setColor(Color.gray.darker().darker());
               for (int k = 0;k < 10;k++)
                  myBuffer.drawLine(20 * k, 0, 20 * k, 400);
               for (int k = 0;k < 20;k++)
                  myBuffer.drawLine(0, 20 * k, 200, 20 * k);
            //******************Draws the Shadow***********************************************
               drawShadow(findBottom());
               repaint();
            }
         }
      }
   
   	/**Marathon Panel listener.*/
       public class maraListener implements ActionListener
      {
      	/**Increases time.*/
          public void actionPerformed(ActionEvent e)
         {	
            timePassed++;            
         }
      } 
       public class TimedListener implements ActionListener
      {
      /**Automatically makes the piece move down.*/
          public void actionPerformed(ActionEvent e)
         {	
            if (canDown(tetrad))
            {
               moveDown(tetrad);
               score++;
            }
            tetrad.setPosition(); 
            setColors();       
            repaint();
         }
      }
       public class BottomListener implements ActionListener
      {
      /**Once piece hits bottom, this gives it a bit of time before it locks.*/
          public void actionPerformed(ActionEvent e)
         {	
            if (rowCleared == false)
               multiplier2 = 1;
            else
               multiplier2++;
            tetrad.setPosition();
            setFilleds();
            setColors(); 
            setColors();
            bottomTimer.stop(); 
            readyNew = true; 
            repaint();
         }
      }
   	/**Checks if the row is clear.*/  		
       public void checkClear(int x)
      {
         boolean clear = true;
         for (int r = 0; r < matrix[0].length; r++)
         {
            if(matrix[x][r].getFilled()==false)
               clear = false;
         }
         if (clear==true)
         {
            clearSound.play();
            wipeRow(x);
            readyDropRow[x] = true;//The row which has pieces to be cleared. (-1 means there was no lines to be cleared)
            rowCleared = true;
            linesCleared++;
            multiplier1++;//The multiplier for clearing many lines in one go.
            if (multiplier1 >= 5)
               tetrisSound.play();
            calcScore(false, true, -1);
         }
         else if (rowCleared == false)
         {
            t.setDelay(1);
         }		
      }
   	/**Wipes a row that is filled with blocks.*/
       public void wipeRow(int x)
      {
         t.setDelay(100);//Creates the flash of white to be long enough for the human eye to see
         for (int r = 0; r < matrix[0].length; r++)
         {
            matrix[x][r].setFilled(false);//"Destroys" those pieces, by unfilling them
            matrix[x][r].setColor(Color.white);
            matrix[x][r].setCleared(true);
         }	
      }
   	/**Takes the tetris piece and colors its components*/
       public  void setColors()
      {
         for (int k = 0;k < 4;k++)
            matrix[tetrad.getBlocks()[k].getY()][tetrad.getBlocks()[k].getX()].setColor(tetrad.getColor());
      }
   	/**Takes a tetris piece and fills its components.*/
       public  void setFilleds()
      {
         for (int k = 0;k < 4;k++)
            matrix[tetrad.getBlocks()[k].getY()][tetrad.getBlocks()[k].getX()].setFilled(true);
      }	 
   	/**Hard drops a piece by finding the bottom and setting it there.*/
       public  void hardDrop()
      {
         int bottom = findBottom();
         calcScore(true, false, bottom);
         tetrad.setY(bottom);
         tetrad.setPosition();
         dropSound.play();	
         setColors();			
         setFilleds();  
         hardDropped = true; 
      }
   	/**Finds the bottom of the matrix.*/
       public  int findBottom()
      {
         int origPos = tetrad.getY();
         while (canDown(tetrad))
            moveDown(tetrad);
         int bottom = tetrad.getY();
         tetrad.setY(origPos);//Once the piece hits bottom, it returns to original
      								//and keeps where that bottom was
         return bottom;
      }
   	/**Holds the piece in position.*/
       public  void hold()
      {
         if (justHeld == -1)
         {
            if (hasHold == false)//Never held before
            {
               readyNew = true;
               holdtetrad = new TetradV2(tetrad.getType());
               hasHold = true;
               justHeld = 1;
            }
            if (hasHold == true)
            {	
               temp = new TetradV2(tetrad.getType());//Swaps the current and held pieces
               tetrad = new TetradV2(holdtetrad.getType());
               holdtetrad = new TetradV2(temp.getType());
               justHeld = 0;//A new piece isn't made since the pieces are just switched
            					//And since the pieces are re-instantiated they'll automatically start at the top
            					//Therefore we only need justHeld at one step, unlike above
               tetrad.setPosition();
            }
         }
      }
       public void gameOver()
      {
         if (matrix[tetrad.getHeight()-1][5].getFilled()==true)
         {
            for (int r = 0; r < matrix.length; r++)
            {
               for (int c = 0; c < matrix[0].length; c++)
               {
                  myBuffer.setColor(Color.black);
                  myBuffer.fillRect(20 * matrix[r][c].getX(), 20 * matrix[r][c].getY(), 20, 20);
                  myBuffer.setColor(Color.red);
                  myBuffer.drawString("GAME OVER", 75, 200);
               }
            }
            musicArray[0].stop();
            musicArray[1].stop();
            musicArray[2].stop();               
            musicArray[3].stop();
            gameOverMusic.play();     
            Highscore original = new Highscore(null,score);
            scores[0] = original;
            Arrays.sort(scores);
            if (!(scores[0]==original))
            {
               String scoreName = JOptionPane.showInputDialog("New High Score!  Name?");
               for (int k = 1; k < 11; k++)
                  if (scores[k]==original)
                  {
                     scores[k].setName(scoreName);
                     break;
                  }
            }                     
            String scoreList = "";
            for (int k = 1; k < 11; k++)
            {
               scoreList += (k + ". " + scores[11-k].getName() + "  " + scores[11-k].getScore());
               scoreList += "\n";
            }
            JOptionPane.showMessageDialog(null, scoreList);
            save(scores);
            t2.removeActionListener(t2.getActionListeners()[0]);
            bottomTimer.removeActionListener(bottomTimer.getActionListeners()[0]);
            maraTimer.removeActionListener(maraTimer.getActionListeners()[0]);
            debugChecker.start();
            gameOver = true;
         }	
      }
       public class debugList implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            if (gameOver==false)
            {
               System.out.println("WTF!");
            }
         }
      }
   	/**Calculates your score.*/
       public  void calcScore(boolean harddrop, boolean linecleared, int bottom)
      {
         if (harddrop == true)//Instantly adds the score should piece hardDrop
            score += 2 * (bottom - tetrad.getY());
         if (linecleared==true)//Depending on what the "clear in one go" multiplier is, score is calculated.
         {
            int linescore = 0;
            switch (multiplier1)
            {
               case 1: linescore = 40 * (level + 1) * multiplier2;
                  break;
               case 2: linescore = 100 * (level + 1) * multiplier2;
                  break;
               case 3: linescore = 300 * (level + 1) * multiplier2;
                  break;
               case 4: linescore = 1200 * (level +1) * multiplier2;
                  break;
            }
            score += linescore;
         }
      	//A line isn't always cleared, nor is a piece hardDropped, so the booleans are necessary
      	//as the score must be continually calculated by TimedListener
      }
   	/**Draws shadow in white where the tetrad is.*/
       public  void drawShadow(int bottom)
      {
         myBuffer.setColor(Color.white);//Finds bottom, draws the shadow in white there by setting the tetrad there 
         int origPos = tetrad.getY();//and then going back up.
         tetrad.setY(bottom);
         tetrad.setPosition();
         for (int k = 0; k < 4; k++)
         {
            myBuffer.drawRect(20 * (tetrad.getBlocks()[k].getX()), 20 * (tetrad.getBlocks()[k].getY()), 20, 20);
         }
         tetrad.setY(origPos);
         tetrad.setPosition();
      }
   	/**Scrambles the order of the pieces in the array*/
       public  int[] scramble(int[] arg)//The scrambled array
      {
         int[] used = new int[7];//The used array keeps track of what numbers have already been scrambled
         int[] returned = new int[7];//The (duh) returned array
         for (int k = 0; k < arg.length; k++)
         {
            int random = (int)(Math.random() * 7);
            while (used[random]==1)//While that cell has been used before, a new one is chosen
            {
               random = (int)(Math.random() * 7);
            }
            used[random] = 1;//Says that cell has been used before
            returned[k] = arg[random];//Makes the current cell equal to the randomly chosen cell
         }
         return returned;
      }
   	/**Moves a tetrad left, if possible.*/
       public  void moveLeft(TetradV2 tetrad)
      {
         boolean okay = canLeft(tetrad);//Checks if can moveLeft, then does
         if (okay==true)
         {
            tetrad.moveLeft();
            if (bottomTimer.isRunning()==true)
               bottomTimer.restart();//Whenever something moves, the delay at the bottom has to reset
         }
         tetrad.setPosition();
      }
   	/**Checks to see whether a tetrad can move left.*/
       public boolean canLeft(TetradV2 tetrad)
      {
         boolean okay = true;
         for (int k = 0;k < 4;k++)
         {
            if (!((tetrad.getBlocks()[k]).getX() > 0))
               okay = false;
            if ((tetrad.getBlocks()[k]).getX()>0)
               if (matrix[(tetrad.getBlocks()[k]).getY()][(tetrad.getBlocks()[k]).getX()-1].getFilled()==true)
                  okay = false;
         }
         return okay;
      }
   	/**Moves a tetrad right, if possible.*/
       public  void moveRight(TetradV2 tetrad)
      {
         boolean okay = canRight(tetrad);
         if (okay==true)
         {
            tetrad.moveRight();
            if (bottomTimer.isRunning()==true)
               bottomTimer.restart();
         }
         tetrad.setPosition();
      }
   	/**Checks to see whether a tetrad can move right.*/
       public boolean canRight(TetradV2 tetrad)
      {
         boolean okay = true;
         for (int k = 0;k < 4;k++)
         {            
            if (tetrad.getBlocks()[k].getX() == 9)
               okay = false;
            if ((tetrad.getBlocks()[k]).getX()<9)
            {
               if (matrix[(tetrad.getBlocks()[k]).getY()][(tetrad.getBlocks()[k]).getX()+1].getFilled()==true)
                  okay = false;
            }
         }
         return okay;
      }
   	/**Moves a tetrad down, if possible.*/
       public  void moveDown(TetradV2 tetrad)
      {
         boolean okay = canDown(tetrad);
         if (okay)
            tetrad.moveDown();
         tetrad.setPosition();
      }
   	/**Checks to see whether a tetrad can move down.*/
       public boolean canDown(TetradV2 tetrad)
      {
         boolean okay = true;
         for (int k = 0;k < 4;k++)
            if (!((tetrad.getBlocks()[k]).getY() < 19) || matrix[(tetrad.getBlocks()[k]).getY()+1][(tetrad.getBlocks()[k]).getX()].getFilled()==true)
               okay = false;
         return okay;
      }
   	/**Moves a tetrad up, if possible.*/
       public  void moveUp(TetradV2 tetrad)
      {
         boolean okay = canUp(tetrad);
         if (okay)
            tetrad.moveUp();
         tetrad.setPosition();
      }
   	/**Checks to see whether a tetrad can move up.*/
       public boolean canUp(TetradV2 tetrad)
      {
         boolean okay = true;
         for (int k = 0;k < 4;k++)
            if (!((tetrad.getBlocks()[k]).getY() > 0) || matrix[(tetrad.getBlocks()[k]).getY()-1][(tetrad.getBlocks()[k]).getX()].getFilled()==true)
               okay = false;
         return okay;
      }
       public void startMusic(int x)
      {
         musicArray[x - 1].loop();
         currentMusic = x;		
      }		
       public void stopMusic(int x)
      {
         musicArray[x - 1].stop();	
      }
   	/**Destroys instances of music*/
       public void exitMusic()
      {
         musicArray[0].exit();
         musicArray[1].exit();
         musicArray[2].exit();
         musicArray[3].exit();
      }
   	/**The scoreboard seen on the right, with the goal, time passed, level, lines cleared, score, and reset, and quit buttons.*/
       public class ScoreBoard extends JPanel
      {
         JLabel goalLabel, clearedLabel, lvlLabel, scoreLabel, timeLabel;
         JButton resetButton, quitButton;
          public ScoreBoard()
         {
            setLayout(new GridLayout(7,1));
         
            goalLabel = new JLabel("Goal: "+levGoal);
            goalLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            goalLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(goalLabel);
         
            clearedLabel = new JLabel("Lines Cleared: "+linesCleared + "  ");//The spaces at the end are for formatting
            clearedLabel.setHorizontalAlignment(SwingConstants.LEFT);
            clearedLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            add(clearedLabel);		
         
            lvlLabel = new JLabel("Level: "+level );
            lvlLabel.setHorizontalAlignment(SwingConstants.LEFT);
            lvlLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            add(lvlLabel);		
         	
            scoreLabel = new JLabel("Score: "+score );
            scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
            scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            add(scoreLabel);
         	
            timeLabel = new JLabel("Time Passed: "+timePassed );
            timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
            timeLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            add(timeLabel);
         	
            resetButton = new JButton("RESET");
            resetButton.addActionListener(new resetList());
            add(resetButton);     	
         
            quitButton = new JButton("Main Menu");
            quitButton.addActionListener(new quitList());
            add(quitButton); 
         	        	
            setBackground(BACKGROUND);
         }
      	/**Resets screen.*/
          public class resetList implements ActionListener
         {
             public void actionPerformed(ActionEvent e)
            {
               exitMusic();
               TetrisDriver.reload(new MarathonPanel(myKeys, myTheme, level), "Marathon!");	
            }
         }
      	/**Quits back to home screen.*/
          public class quitList implements ActionListener
         {
             public void actionPerformed(ActionEvent e)
            {
               exitMusic();
               TetrisDriver.reload(new MainPanel(false), "Final Project: Tetris!");	
            }
         }
      	/**Constantly updates the scoreboard.*/
          public void updateLabels()
         {
            goalLabel.setText("Goal: "+levGoal);
            clearedLabel.setText("Lines Cleared: "+linesCleared+ "  ");
            lvlLabel.setText("Level: "+level );
            scoreLabel.setText("Score: "+score );
            timeLabel.setText("Time Passed: "+timePassed);
         }
      	/**Pauses the game.*/
          public void pauseGame()
         {
            if (paused==false)
            {
               t.stop();//Stop any timers
               t2.stop();
               bottomTimer.stop();
               pauseScreen = new JOptionPane();
               String response = pauseScreen.showInputDialog("PAUSED (Type 1 to unpause.");
               paused = true;
               while (!(response.equals("1")))
               {
                  response = pauseScreen.showInputDialog("INVALID INPUT:PAUSED (Type 1 to unpause.)");
               }
            	//Unpause the game 
               if (response.equals("1"))  
               {
                  t.start();//Start timers
                  t2.start();
                  bottomTimer.start();
                  paused = false;
               }
            }
         }
      }
   	/**Gets high scores from file.*/
       public Highscore[] getScores(String filename)
      {
         Scanner infile = null;
         try{
            infile = new Scanner(new File(filename));
         }
             catch(FileNotFoundException e)
            {
               JOptionPane.showMessageDialog(null,"The file could not be found.");
               System.exit(0);
            }
         Highscore[] scorearray = new Highscore[11];
         for(int k = 1; k < 11; k++)
         {
            scorearray[k] = new Highscore(infile.nextLine(), Integer.parseInt(infile.nextLine()));
         }
         infile.close();
         return scorearray;
      } 
   	/**Saves high scores.*/
       public static void save(Highscore[] array)
      {
         PrintStream outfile = null;
         try{
            outfile = new PrintStream(new FileOutputStream("marascores.txt"));
         }
             catch(FileNotFoundException e)
            {
               JOptionPane.showMessageDialog(null,"The file could not be created.");
            }
         System.setOut(outfile);
         for (int k = 10; k > 0; k--)
         {
            System.out.println(""+array[k].getName());
            System.out.println(""+array[k].getScore());
         }
         outfile.close();
      } 
       	
   }