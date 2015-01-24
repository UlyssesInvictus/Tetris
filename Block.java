	//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
   import java.awt.*;
	/**Block represents one cell in the matrix.*/
    public class Block 
   {
      private int myX;   // x and y coordinates of grid  (NORMAL CARTESIAN)
      private int myY;
      private boolean isFilled;
      private boolean isCleared;
      private Color myColor;
   	/**Constructs a Block with initial position specified by x and y, whose color is color and isn't filled or ready to be cleared .*/
       public Block(int y, int x, Color color)     //default constructor
      {
         myX = x;
         myY = y;
         isFilled = false;
         myColor = color;
         isCleared = false;
      }
    // accessor methods
    /**  Answers the question, "What is your x-coordinate?" Accessor method for the private field myX.*/
       public int getX() 
      { 
         return myX;
      }
   	/**Answers the question, "What is your y-coordinate?" Accessor method for the private field myY.*/
       public int getY()      
      { 
         return myY;
      }
   	/**Answers the question, "Is there something there?" Accesor method for the private field isFilled */
       public boolean getFilled()
      {
         return isFilled;
      }
   	/**Answers the question, "What color is the Block?" Accesor method for the private field myColor */
       public Color getColor()
      {
         return myColor;
      }
   		/**Answers the question, "Is it ready to be cleared?" Accesor method for the private field isCleared */
       public boolean getCleared()
      {
         return isCleared;
      }
   // modifier methods 
   /**Modifier method. Sets the color of Block to c.*/
       public void setColor(Color c)
      {
         myColor = c;
      }
   	/**Modifier method. Can set that the block is filled or not*/
       public void setFilled(boolean b)
      {
         isFilled = b;
      }
   	/**Modifier method. Can determine if a block is ready to be cleared or not*/
       public void setCleared(boolean b)
      {
         isCleared = b;
      }
   }