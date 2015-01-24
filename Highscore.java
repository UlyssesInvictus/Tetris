	//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
	/**Highscore lets us save scores of players and compare them.*/
    public class Highscore implements Comparable<Highscore>
   {
      String name;
      int score;
   		/**Default constructor of Highscore. Sets name as Null and the score as 0.*/
       public Highscore()
      {
         name = "Null";
         score = 0;
      }
   		/**Constructs a Highscore that sets the name as s and score as x.*/
       public Highscore(String s, int x)
      {
         name = s;
         score = x;
      }
   		/**  Answers the question, "What is your score?" Accessor method for the private field score.*/
       public int getScore()
      {
         return score;
      }
   		/**  Answers the question, "What is your name?" Accessor method for the private field name.*/
       public String getName()
      {
         return name;
      }
   		/** Returns the difference between the Highscore of 2 different players.*/
       public int compareTo(Highscore b)
      {
         return score - b.getScore();
      }	
   		/**Modifier method. Sets name as s*/ 
       public void setName(String s)
      {
         name = s;
      }		
   }