	//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
   import java.awt.*;
	/**TetradV2 makes a tetris piece.*/
    public class TetradV2
   {
      private int myX;
      private int myY;
      private Block[] blocky;
      private int myType;
      private int myRotation;
      private int myHeight;
      private Color myColor;
       public TetradV2()     //default constructor
      {
         myX = 5;
         myType = (int)(Math.random() * 7);
         switch (myType)
         {
            case 0: myColor = new Color(0,255,255);  myY = 3; myHeight = 4;
               break;
            case 1: myColor = Color.magenta;  myY = 1; myHeight = 2;
               break;
            case 2: myColor = Color.yellow;  myY = 1; myHeight = 2;
               break;
            case 3: myColor = new Color(255,102,0);  myY = 2; myHeight = 3;
               break;
            case 4: myColor = Color.blue.darker();  myY = 2; myHeight = 3;
               break;
            case 5: myColor = Color.green;  myY = 1; myHeight = 2;
               break;
            case 6: myColor = Color.red;  myY = 1; myHeight = 2;
               break;
         }
         myRotation = 0;
         blocky = new Block[4];
         for (int k = 0;k < 4;k++)
            blocky[k] = new Block(0,0,Color.black);
			setPosition();
      }
       public TetradV2(int type)     
      {
         myX = 5;
         myType = (int)(Math.random() * 7);
         myType = type;
         switch (myType)
         {
            case 0: myColor = new Color(0,255,255);  myY = 3; myHeight = 4;
               break;
            case 1: myColor = Color.magenta;  myY = 1; myHeight = 2;
               break;
            case 2: myColor = Color.yellow;  myY = 1; myHeight = 2;
               break;
            case 3: myColor = new Color(255,102,0);  myY = 2; myHeight = 3;
               break;
            case 4: myColor = Color.blue.darker();  myY = 2; myHeight = 3;
               break;
            case 5: myColor = Color.green;  myY = 1; myHeight = 2;
               break;
            case 6: myColor = Color.red;  myY = 1; myHeight = 2;
               break;
         }
         myRotation = 0;
         blocky = new Block[4];
         for (int k = 0;k < 4;k++)
            blocky[k] = new Block(0, 0, myColor);
			setPosition();
      }
    // accessor methods
     /**Answers the question, "What is the TetradV2's x-coordinate value?" Accessor method for the private field myX.*/ 
       public int getX() 
      {     
         return myX;
      }
   	  /**Answers the question, "What is the TetradV2's y-coordinate value?" Accessor method for the private field myY.*/
       public int getY()      
      { 
         return myY;
      }
   	/**Answers the question, "What type of TetradV2 is it?" Accessor method for the private field myType.*/
       public int getType() 
      {
         return myType;
      }
   	/**Answers the question, "How much has the TetradV2 been rotated?" Accessor method for the private field type myRotation.*/
       public int getRotation()
      {
         return myRotation;
      } 
   	/**Answers the question, "How tall is the TetradV2?" Accessor method for the private field type myHeight.*/
       public int getHeight()
      {
         return myHeight;
      }
   	/**Answers the question, "What color is the TetradV2?" Accessor method for the private field type myColor.*/
       public Color getColor()
      {
         return myColor;
      }
   	/**Answers the question, "Where are the blocks that make up the TetradV2?" Accessor method for the private field blocky.*/
       public Block[] getBlocks()
      {
         return blocky;
      }
   // modifier methods
   /**Increases private field myX by one.*/
       public void moveRight()
      {
         myX++;
      }
   	/**Decreases private field myX by one.*/
       public void moveLeft()
      {
         myX--;
      }
   	/**Increases private field myY by one.*/
       public void moveDown()
      {
         myY++;
      }
   	/**Decreases private field myY by one.*/
       public void moveUp()
      {
         myY--;
      }
   	/**Increases private field myRotation by one.*/
       public void rotate()
      {
         myRotation++;
      }
   	/**Modifier method that sets private field myY as y.*/
       public void setY(int y)
      {
         myY = y;
      }
   	/**Modifier method that sets private field myX as x.*/
       public void setX(int x)
      {
         myX = x;
      }
   	/**Defines where the blocks fill the TetradV2.*/
       public void setPosition()//Defines where the blocks fill the tetrad
      {
         switch (myType)
         {      
            case 0: 
               switch (myRotation%2)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-2,myX,myColor);
                     blocky[3]= new Block(myY-3,myX,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX+1,myColor);
                     blocky[2]= new Block(myY,myX+2,myColor);
                     blocky[3]= new Block(myY,myX+3,myColor);
                     break;
               }						
               break;
            case 1: 
               switch (myRotation%4)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-1,myX+1,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-2,myX,myColor);
                     break;
                  case 2:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY,myX-1,myColor);
                     blocky[3]= new Block(myY,myX+1,myColor);	
                     break;
                  case 3:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX+1,myColor);
                     blocky[3]= new Block(myY-2,myX,myColor);											
               }
               break;
            case 2: 
               blocky[0]= new Block(myY,myX,myColor);
               blocky[1]= new Block(myY-1,myX,myColor);
               blocky[2]= new Block(myY-1,myX+1,myColor);
               blocky[3]= new Block(myY,myX+1,myColor);
               break;
            case 3:  
               switch (myRotation%4)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX-1,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-2,myX-1,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX+1,myColor);
                     blocky[3]= new Block(myY-1,myX+2,myColor);
                     break;
                  case 2:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-2,myX,myColor);
                     blocky[3]= new Block(myY-2,myX-1,myColor);
                     break;
                  case 3:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX+1,myColor);
                     blocky[2]= new Block(myY,myX+2,myColor);
                     blocky[3]= new Block(myY-1,myX+2,myColor);
                     break;
               }
               break;
            case 4:  
               switch(myRotation%4)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX+1,myColor);
                     blocky[2]= new Block(myY-1,myX+1,myColor);
                     blocky[3]= new Block(myY-2,myX+1,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX-1,myColor);
                     blocky[2]= new Block(myY,myX-2,myColor);
                     blocky[3]= new Block(myY-1,myX-2,myColor);
                     break;
                  case 2:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-2,myX,myColor);
                     blocky[3]= new Block(myY-2,myX+1,myColor);
                     break;
                  case 3:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-1,myX-2,myColor);
                     break;					
               }
               break;
            case 5:  
               switch(myRotation%2)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX+1,myColor);
                     blocky[2]= new Block(myY-1,myX+1,myColor);
                     blocky[3]= new Block(myY-1,myX+2,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-2,myX-1,myColor);
                     break;
               }
               break;					
            case 6:  
               switch(myRotation%2)
               {
                  case 0:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY,myX-1,myColor);
                     blocky[2]= new Block(myY-1,myX-1,myColor);
                     blocky[3]= new Block(myY-1,myX-2,myColor);
                     break;
                  case 1:
                     blocky[0]= new Block(myY,myX,myColor);
                     blocky[1]= new Block(myY-1,myX,myColor);
                     blocky[2]= new Block(myY-1,myX+1,myColor);
                     blocky[3]= new Block(myY-2,myX+1,myColor);
                     break;				
               }
         }
      }
   }