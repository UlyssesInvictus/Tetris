 	//Name: Raynor Kuang, Yash Maniar, JiHun Kim. Date: 6/8/10  
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
   import java.io.File;
   import java.io.IOException;
    public class MidiPlayer extends SoundClass
   {
      private  Sequencer	sm_sequencer = null;
      private  Synthesizer	sm_synthesizer = null;
		private Receiver	synthReceiver = null;
		private Transmitter	seqTransmitter = null;
		/**The constructor of MidiPlayer, sets the filename to a Sequence object.*/
       public MidiPlayer(String filename)
      {
         File midiFile = new File(filename);
         /*
         *	We read in the MIDI file to a Sequence object.
         *	This object is set at the Sequencer later.
         */
         Sequence	sequence = null;
         try
         {
            sequence = MidiSystem.getSequence(midiFile);
         }
             catch (InvalidMidiDataException e)
            {
               /*
               *	In case of an exception, we dump the exception
               *	including the stack trace to the console.
               *	Then, we exit the program.
               */
               e.printStackTrace();
               System.exit(1);
            }
             catch (IOException e)
            {
               /*
               *	In case of an exception, we dump the exception
               *	including the stack trace to the console.
               *	Then, we exit the program.
               */
               e.printStackTrace();
               System.exit(1);
            }
         /*
         *	Now, we need a Sequencer to play the sequence.
         *	Here, we simply request the default sequencer.
         */
         try
         {
            sm_sequencer = MidiSystem.getSequencer();
         }
             catch (MidiUnavailableException e)
            {
               e.printStackTrace();
               System.exit(1);
            }
         if (sm_sequencer == null)
         {
            
            System.exit(1);
         }         
         
      //************************Open the sequencer, essentially making it live******************************
         try
         {
         sm_sequencer.open();
         }
             catch (MidiUnavailableException e)
            {
               e.printStackTrace();
               System.exit(1);
            }
         
         /*
         *	Next step is to tell the Sequencer which
         *	Sequence it has to play. In this case, we
         *	set it as the Sequence object created above.
         */
         try
         {
            sm_sequencer.setSequence(sequence);
         }
             catch (InvalidMidiDataException e)
            {
               e.printStackTrace();
               System.exit(1);
            }
         
            /*
            *	We try to get the default synthesizer, open()
            *	it and chain it to the sequencer with a
            *	Transmitter-Receiver pair.
            */
         try
         {
            sm_synthesizer = MidiSystem.getSynthesizer();
            sm_synthesizer.open();
            synthReceiver = sm_synthesizer.getReceiver();
            seqTransmitter = sm_sequencer.getTransmitter();
            seqTransmitter.setReceiver(synthReceiver);
         }
             catch (MidiUnavailableException e)
            {
               e.printStackTrace();
            }
         
         
      }
   	/**Loops the sequencer.*/
       public void loop()
      {
         sm_sequencer.setLoopCount(sm_sequencer.LOOP_CONTINUOUSLY);
         sm_sequencer.start();
      }
		public void play()
		{
         sm_sequencer.setLoopCount(0);
         sm_sequencer.start();		
			}
		/**Stops the sequencer.*/
       public void stop()
      {
         try
         {
            sm_sequencer.stop();
         }
             catch (IllegalStateException e)
            {
               try
               {
                  sm_synthesizer = MidiSystem.getSynthesizer();
                  sm_synthesizer.open();
                  Receiver	synthReceiver = sm_synthesizer.getReceiver();
                  Transmitter	seqTransmitter = sm_sequencer.getTransmitter();
                  seqTransmitter.setReceiver(synthReceiver);
               }
                   catch (MidiUnavailableException f)
                  {
                     f.printStackTrace();
                  }
            }
      }
		/**Closes the sequencer.*/
       public void exit()
      {
         sm_sequencer.close();
			sm_synthesizer.close();
			synthReceiver.close();
			seqTransmitter.close();
      }
   }