   import java.util.Arrays;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
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
   import java.io.*;
   import java.util.Scanner;           
   import com.javaranch.common.AWT;
    public class SprintPanel extends MarathonPanel
   {	
       public SprintPanel(int[] x, TetrisTheme theme, int startLevel)
      {
         super(x, theme, startLevel);
      }
       public void gameOver()
      {
         if (matrix[tetrad.getHeight()][5].getFilled()==true)
         {
            for (int r = 0; r < matrix.length; r++)
               for (int c = 0; c < matrix[0].length; c++)
               {
                  myBuffer.setColor(Color.black);
                  myBuffer.fillRect(20 * matrix[r][c].getX(), 20 * matrix[r][c].getY(), 20, 20);
                  myBuffer.setColor(Color.red);
                  myBuffer.drawString("GAME OVER", 75, 200);
               }
            musicArray[0].stop();
            musicArray[1].stop();
            musicArray[2].stop();               
            musicArray[3].stop();
            gameOverMusic.play();	
         }	     
         if (linesCleared >= 40)
         {
            Highscore original = new Highscore(null,timePassed);
            scores[0] = original;
            Arrays.sort(scores);
            if (!(scores[11]==original))
            {
               String scoreName = JOptionPane.showInputDialog("New High Score!  Name?");
               for (int k = 0; k < 10; k++)
                  if (scores[k]==original)
                  {
                     scores[k].setName(scoreName);
                     break;
                  }
            }                     
            String scoreList = "";
            for (int k = 1; k < 11; k++)
            {
               scoreList += (k + ". " + scores[k-1].getName() + "  " + scores[k-1].getScore());
               scoreList += "\n";
            }
            JOptionPane.showMessageDialog(null, scoreList);
            save(scores);
            gameOver = true;
            return;
         }
      }
   }	
   
   	
