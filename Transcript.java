import processing.core.PApplet; //<>// //<>// //<>// //<>//
import java.io.*;
import processing.data.IntDict;
/**
 * A class that represents a transcript.
 * A transcript includes 
 * the sentences and two new transcripts for each candidates.
 */
public class Transcript {
  ////////////////////////// fields ///////////////////////////////////

  //the string array which holds every word in the transcript
  private String[] sentences;
  protected String speaker;
  //the name of the speaker
  private String name;
  protected String wholeDebate;
  String cli = "";
  String tru = "";
  String spk = "";
  String clinton;
  String trump;

  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that create a new transcript. 
   * @param String name, the name of this transcript
   * @param String fileName, the name of that transcript file
   * @param String speaker, the name of that speaker in the debate
   * @param PApplet p, need that to use its methods
   */
  public Transcript(String fileName, String speaker, PApplet p) {

    this.speaker = speaker+":";
    trump = "TRUMP:";
    clinton ="CLINTON:";
    sentences = p.loadStrings(fileName);
    wholeDebate= "";    
    for (int i = 0; i < sentences.length; i++) {
      wholeDebate = wholeDebate + sentences[i];
    }
    seperate();
  }

  ///////////////////////////Methods////////////////////////////////

  /**
   * Method to return the sentences from a transcript
   * such as who said what
   * @return the string which describes the object
   */
  public void seperate() {
    String seperation = new String (wholeDebate);


    //String end = "Good night, everyone.";
    //int first = 0;
    boolean threePeople = true;
    boolean twoPeople = true;
    do {
      try {
        int c = seperation.indexOf(clinton);
        int t = seperation.indexOf(trump);
        int h = seperation.indexOf(speaker);
        //int e = seperation.indexOf(end);
        if (c < t && c < h) {
          if (t < h) {
            cli = cli + seperation.substring(c, t)+ "\n";
            seperation = seperation.substring(t, seperation.length());
          }
          if (h < t) {
            cli = cli + seperation.substring(c, h)+ "\n";
            seperation = seperation.substring(h, seperation.length());
          }
          //System.out.println("got c");
          // System.out.println(seperation);
        }
        if (t< c && t<h) {
          if (c<h) {
            tru = tru + seperation.substring(t, c)+ "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (h<c) {
            tru = tru + seperation.substring(t, h)+ "\n";
            seperation = seperation.substring(h, seperation.length());
          }
        }
        if (h<c&&h<t) {
          if (c<t) {
            spk = spk + seperation.substring(h, c) + "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (t<c) {
            spk = spk + seperation.substring(h, t) + "\n";
            seperation = seperation.substring(t, seperation.length());
          }
        }
      }
      //if there is an error
      //most likely because there are only one speakers left in the end
      catch(Exception e) {
        //switch the boolean to false
        threePeople = false;
      }
      //loop ends here
    } while (threePeople);
    do {
      try {
        int c = seperation.indexOf(clinton);
        int t = seperation.indexOf(trump);
        int h = seperation.indexOf(speaker);

        if (c == -1) {
          //find the one who speaks first
          if (h<t) {
            //copy the content between the first speaker and second speaker 
            //to the string where holds the first speaker's sentences
            spk = spk + seperation.substring(h, t) + "\n";
            seperation = seperation.substring(t, seperation.length());
          }
          if (t<h) {
            tru = tru + seperation.substring(t, h)+ "\n";
            seperation = seperation.substring(h, seperation.length());
          }
        }
        if (t == -1) {
          if (h<c) {
            spk =spk + seperation.substring(h, c) + "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (c<h) {
            cli = cli + seperation.substring(c, h)+"\n";
            seperation = seperation.substring(h, seperation.length());
          }
        }
      }
      //if there is an error
      //most likely because there are only one speakers left in the end
      catch(Exception e) {
        //switch the boolean to false
        twoPeople = false;
      }
    } while (twoPeople);
    //because the only thing left in seperation is the sentence of house speaker
    //so i just add it to spk
    spk = spk + seperation+ "\n";
  } 

  //a toString method returns the whole debate in a string
  public String toString() {
    return wholeDebate.toLowerCase();
  }
  /**
   * Method to seperate the original transcript to a new transcript only from Clinton
   * return that as a string array
   */
  public String getClinton() {
    return cli.toLowerCase();
  }

  /**
   * Method to seperate the original transcript to a new transcript only from Trump
   * return that as a string array
   */
  public String getTrump() {
    return tru.toLowerCase();
  }
  /**
   * Method to get house speaker's part
   * return that as a string
   */
  public String getSpeaker() {
    return spk.toLowerCase();
  }
}