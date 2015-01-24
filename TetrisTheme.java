    public class TetrisTheme
   {
      public static String tetrisSound;
      public static String gameOver;
      public static String clearSound;
		public static String menuSong;
		public static String[] songArray = new String[4];
		public static String titleImage;
       public TetrisTheme()
      {
			menuSong = "tetrismenu.mid";
         gameOver ="tetrisgameover.mid";
			tetrisSound = "smb_powerup.wav";
			clearSound = "smb_coin.wav";
			songArray[0] = "TetrisA.mid";
      	songArray[1] = "TetrisB.mid";
			songArray[2] = "TetrisC.mid";
			songArray[3] = "tetrisD.mid";
			titleImage = "TetrisTitle.gif";
      }
   }
