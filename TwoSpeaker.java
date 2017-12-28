import processing.core.PApplet;
import java.io.*;
/**
 * A class that represents a special transcript.
 * A TwoSpeaker includes all things from Transcript
 * And the sign of speaker2 and String holds texts from speaker2
 */
public class TwoSpeaker extends Transcript {
  ////////////////////////// fields ///////////////////////////////////

  private String speaker2;
  String spk2 = null;
  int c, t, h, h2;

  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that create a new TwoSpeaker. 
   * @param String name, the name of this transcript
   * @param String fileName, the name of that transcript file
   * @param String speaker, the name of that speaker in the debate
   *@param String speaker2, the name of the second house speaker
   * @param PApplet p, need that to use its methods
   */

  public TwoSpeaker( String fileName, String speaker, String speaker2, PApplet p) {
    super( fileName, speaker, p);    
    this.speaker2 = speaker2 +":";
    seperate2();
  }
  ///////////////////////////Methods////////////////////////////////

  /**
   * Method to seperate the wholeDebate by speakers
   * such as who said what
   */
  public void seperate2() {
    String seperation = new String (wholeDebate);
    boolean fourPeople = true;
    boolean threePeople = true;
    boolean twoPeople = true;
    //keep doing this loop while all four people are speaking
    do {
      //try these lines 
      try {
        c = seperation.indexOf(clinton);
        t = seperation.indexOf(trump);
        h = seperation.indexOf(speaker);
        h2 = seperation.indexOf(speaker2);


        if (c < t && c< h && c < h2) {
          //check who speaks first
          if (t < h && t < h2) {
            //and who speaks second
            cli = cli + seperation.substring(c, t)+ "\n";
            seperation = seperation.substring(t, seperation.length());
          }
          if (h < t && h < h2) {
            cli = cli + seperation.substring(c, h)+ "\n";
            seperation = seperation.substring(h, seperation.length());
          }
          if (h2 < t && h2 < t) {
            cli = cli + seperation.substring(c, h2)+ "\n";
            seperation = seperation.substring(h2, seperation.length());
          }
        }



        if (t< c && t<h && t < h2) {
          if (c<h && c < h2) {
            tru = tru + seperation.substring(t, c)+ "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (h<c && h< h2) {
            tru = tru + seperation.substring(t, h)+ "\n";
            seperation = seperation.substring(h, seperation.length());
          }
          if (h2 < c && h2 < h) {
            tru = tru + seperation.substring(t, h2)+ "\n";
            seperation = seperation.substring(h2, seperation.length());
          }
        }




        if (h<c&&h<t && h < h2) {
          if (c<t && c < h2) {
            spk = spk + seperation.substring(h, c) + "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (t<c && t < h2) {
            spk = spk + seperation.substring(h, t) + "\n";
            seperation = seperation.substring(t, seperation.length());
          }
          if (h2 < t && h2 < c) {
            spk = spk + seperation.substring(h, h2) + "\n";
            seperation = seperation.substring(h2, seperation.length());
          }
        }

        if (h2<c&&h2<t && h2 < h) {
          if (c<t && c < h) {
            spk2 = spk2 + seperation.substring(h2, c) + "\n";
            seperation = seperation.substring(c, seperation.length());
          }
          if (t<c && t < h) {
            spk2 = spk2 + seperation.substring(h2, t) + "\n";
            seperation = seperation.substring(t, seperation.length());
          }
          if (h < t && h < c) {
            spk2 = spk2 + seperation.substring(h2, h) + "\n";
            seperation = seperation.substring(h, seperation.length());
          }
        }
      }
      catch(Exception e) {
        //switch the boolean to false
        fourPeople = false;
      }
      //loop ends here
    } while (fourPeople);
    do {
      //do the whole thing again 
      try {
        c = seperation.indexOf(clinton);
        t = seperation.indexOf(trump);
        h = seperation.indexOf(speaker);
        h2 = seperation.indexOf(speaker2);
        if (c == -1) {
          if (h<t && h < h2) {
            if ( t < h2) {
              spk = spk + seperation.substring(h, t) + "\n";
              seperation = seperation.substring(t, seperation.length());
            }
            if (h2 < t ) {
              spk = spk + seperation.substring(h, h2) + "\n";
              seperation = seperation.substring(h2, seperation.length());
            }
          }
          if (h2<t && h2 < h) {
            if ( t < h) {
              spk2 = spk2 + seperation.substring(h, t) + "\n";
              seperation = seperation.substring(t, seperation.length());
            }
            if (h < t) {
              spk2 = spk2 + seperation.substring(h2, h) + "\n";
              seperation = seperation.substring(h, seperation.length());
            }
          }
          if (t < h && t < h2) {
            if (h < h2) {
              tru = tru + seperation.substring(t, h)+ "\n";
              seperation = seperation.substring(h, seperation.length());
            }
            if (h2 < h) {
              tru = tru + seperation.substring(t, h2)+ "\n";
              seperation = seperation.substring(h2, seperation.length());
            }
          }
        }
        if (t == -1) {
          if (h2 < h && h2 < c) {
            if (h<c) {
              spk2 =spk2 + seperation.substring(h2, h) + "\n";
              seperation = seperation.substring(h, seperation.length());
            }
            if (c<h) {
              spk2 =spk2 + seperation.substring(h2, c) + "\n";
              seperation = seperation.substring(c, seperation.length());
            }
          }
          if (h<c && h < h2) {
            if ( c < h2) {
              spk = spk + seperation.substring(h, c)+ "\n";
              seperation = seperation.substring(c, seperation.length());
            }
            if (h2 < c ) {
              spk = spk + seperation.substring(h, h2) + "\n";
              seperation = seperation.substring(h2, seperation.length());
            }
          }
          if (c<h && c < h2) {
            if ( h < h2) {
              cli = cli + seperation.substring(c, h)+ "\n";
              seperation = seperation.substring(h, seperation.length());
            }
            if (h2 < h ) {
              cli = cli + seperation.substring(c, h2) + "\n";
              seperation = seperation.substring(h2, seperation.length());
            }
          }
        }
      }
      catch(Exception e) {
        threePeople = false;
      }
    } while (threePeople);

    do {
      try {
        h = seperation.indexOf(speaker);
        h2 = seperation.indexOf(speaker2);

        if (h < h2) {
          spk = spk + seperation.substring(h, h2) + "\n";
          seperation = seperation.substring(h2, seperation.length());
        }
        if (h2 < h) {
          spk2 = spk2 + seperation.substring(h2, h) + "\n";
          seperation = seperation.substring(h, seperation.length());
        }
      }
      catch(Exception e)
      {
        twoPeople = false;
      }
    } while (twoPeople);

    h = seperation.indexOf(speaker);

    h2 = seperation.indexOf(speaker2);
    if (h == -1) {
      spk2 = spk2 + seperation;
    }
    if (h2 == -1) {
      spk = spk + seperation;
    }
  }
  /**
   * Method to get house speaker2's part
   * return that as a string
   */
  public String getSpeaker2() {
    return spk2.toLowerCase();
  }
}