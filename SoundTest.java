	//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10
   import java.io.File;
   import java.io.IOException;
   import javax.sound.sampled.*;
   import javax.sound.sampled.AudioFormat;
   import javax.sound.sampled.AudioInputStream;
   import javax.sound.sampled.AudioSystem;
   import javax.sound.sampled.DataLine;
   import javax.sound.sampled.LineUnavailableException;
   import javax.sound.sampled.SourceDataLine;
	/**Gets information about the sound files.*/
    public class SoundTest extends SoundClass
   {
      private static final int EXTERNAL_BUFFER_SIZE = 128000;
      Clip line = null;
      boolean oncePlayed = false;
   	/**The constructor of SoundTest. The filename is used to be read in and the audio is later gotten from it and used.*/
       public SoundTest(String filename)
      {
         File soundFile = new File(filename);
      /**We have to read in the sound file.*/
         AudioInputStream audioInputStream = null;
         try
         {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
         }
             catch (Exception e)
            {
            /**In case of an exception, we dump the exception including the stack trace to the console output. Then, we exit the program.*/
               e.printStackTrace();
               System.exit(1);
            }
      /**From the AudioInputStream, i.e. from the sound file, we fetch information about the format of the audio data. These information include the sampling frequency, the number of channels and the size of the samples. These information are needed to ask Java Sound for a suitable output line for this audio file.*/
         AudioFormat	audioFormat = audioInputStream.getFormat();
         DataLine.Info	info = new DataLine.Info(Clip.class,
            								 audioFormat);
         try
         {
            line = (Clip) AudioSystem.getLine(info);
         	/**The line is there, but it is not yet ready to receive audio data. We have to open the line.*/
            line.open(audioInputStream);
         }
             catch (LineUnavailableException e)
            {
               e.printStackTrace();
               System.exit(1);
            }
             catch (Exception e)
            {
               e.printStackTrace();
               System.exit(1);
            }
      }
   	/**Begins the music.*/
       public void play()
      {
         FloatControl gainControl = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
         gainControl.setValue(6);
         if (line != null)
         {    
            if (oncePlayed == false)
            {
               line.loop(0);
               oncePlayed = true;
            }
            else
               line.loop(1);
         }
      }
       public void loop()
      {
         line.loop(Clip.LOOP_CONTINUOUSLY);
      }
       public void stop()
      {
         line.stop();
      }
       public void exit()
      {
         line.stop();
         line.flush();
      }
   }