//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
   import javax.swing.*;
	/**The Driver of Tetris, where it all begins.*/
    public class TetrisDriver
   {
		/**Frame of the game.*/
      private static JFrame frame;
		/**Runs the game.*/
       public static void main(String[] args)
      {
         initialize();
      }
		/**Actually begins the program.*/
       private static void initialize()
      {
         frame = new JFrame("Final Project: Tetris!");
         frame.setSize(600, 616);
         frame.setLocation(200, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MainPanel m = new MainPanel(true);
         frame.add(m);
			m.requestFocus();
         frame.setVisible(true);
      }
		/**Updates the frame.*/
       public static void reload(JPanel j, String s)
      {
			frame.getContentPane().removeAll();
			frame.getContentPane().invalidate();
         frame.setTitle(s);
         frame.getContentPane().add(j);
			frame.getContentPane().validate();
			j.requestFocus();
			frame.setVisible(true);
      }
   }