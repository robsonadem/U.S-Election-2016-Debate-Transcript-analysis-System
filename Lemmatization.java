
import processing.core.PApplet; 
/**
 * A class that merge the words in different tense
 * A lemmatization includes 
 * Arrays of dict, the entire dictionary; translate, the word which words in different tense merge to;
 * and origin, words in different tense
 */
public class Lemmatization {
////////////////////////// fields ///////////////////////////////////
 
  String[] dict;
  String [] translate;
  String [] origin ;
  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that create a new lemmatization.  
   * @param string fileName, the file that holds the dictionary.
   * @param PApplet p, an class to use its methods
   */
  public Lemmatization(String fileName, PApplet p) {
    dict = p.loadStrings(fileName);
    translate = new String[dict.length];
    origin = new String[dict.length];
    for (int i = 0; i < dict.length; i++) {
      String[] word = p.splitTokens( dict[i], "\t");
      translate[i] = word[0];
      origin[i] =  word[1];
    }
  }
  ///////////////////////////Methods////////////////////////////////

  /**
   * Method to find the coresponding word in translate from origin
   *@param string s, the word wanted to find
   * return that string in translate
   */
  public String translator(String s) {

    for (int i = 0; i < origin.length; i ++) {
      if (origin[i].equals(s)) {

        return translate[i];
      }
    }

    return s;
  }
}