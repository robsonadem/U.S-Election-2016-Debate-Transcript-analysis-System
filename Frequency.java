import processing.core.PApplet;  //<>//
import processing.data.IntDict;

public class Frequency {
  ////////////////////////// fields ///////////////////////////////////
 
  IntDict inventory;
  String[] keys;
  int[] amounts;
  String[] sA;
  String[] words;
  int[] quantity;
  String useless[];
  String[] noUse;
  int size;
  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that create a new frequency.  
   * @param string s, the string user want to find the frequency
   * @param int times, the number of words the user wants in the top frequency array
   * @param lemmatization l, an class which merge words in different tense to one
   * @param PApplet p, an class to use its methods
   */
  public Frequency(String s, int times, Lemmatization l, PApplet p) {
    inventory = new IntDict();
    size = times;
    words = new String[size];
    quantity = new int[size];
    useless = p.loadStrings("useless.txt");
    noUse = p.split(useless[0], ',');
    //System.out.println(noUse[2]);
    s = l.translator(s);
    String delim = "[-_—— .,:?!]+";
    sA=p.splitTokens(s, delim);
    for (int i = 0; i < sA.length; i++) {
      inventory.add(sA[i], 1);
    }
    keys = inventory.keyArray();
    amounts = inventory.valueArray();
    FreqWord();
  }
   ///////////////////////////Methods////////////////////////////////

  /**
   * Method to return the number of a term in the string
   *param string term, the word user want to search
   * @return the frequency of that word
   */
  public int count(String term) {
    term = term.toLowerCase();
    if (inventory.hasKey(term) == true) {
      return inventory.get(term);
    } else {
      return -1;
    }
  }
  /**
   * Method to sort array of words and array of quantity based on their frequency
   */
  public void FreqWord() {
    try {
      for (int i = keys.length; i > 0; i --) {
        for (int j = 0; j < i-1; j++) {
          if (amounts[j] > amounts[j+1]) {
            swap2(amounts, j, j+1);
            swap(keys, j, j+1);
          }
        }
      }
      int u = 0;
      for (int k = 0; k < size; k ++) {    
        for (int t = u; t<keys.length; t++ ) {
          if (canUse(keys[keys.length-1-t])) {
            words[k] = keys[keys.length-1-t];
            quantity[k]=amounts[amounts.length-1-t];
            u++;
            break;
          } else {
            u++;
          }
        }
      }
    }
    catch(Exception e) {

      System.out.println("error");
    }
  }
  /**
   * Method to switch the index of string
   *@param string[] array, the array wanted to switch index
   *@param int a, int b, the number in the array that needs to switch
   */
  public void swap(String[] array, int a, int b) {
    String holder;
    holder = array[a];
    array[a] = array[b];
    array[b] = holder;
  }
  /**
   * Method to switch the index of int
   *@param int[] array, the array wanted to switch index
   *@param int a, int b, the number in the array that needs to switch
   */
  public void swap2(int[] array, int a, int b) {
    int holder;
    holder = array[a];
    array[a] = array[b];
    array[b] = holder;
  }
  /**
   * Method to get string[] words which holds the words in the given string
   * r  
   */
   public String[] getWords() {
   
   return words;
   }
  /**
   * Method to find if a string is in the array of noUse
   *@param string s, the word wanted to check
   * return that as a boolean
   */
  public boolean canUse(String s) {
    boolean bb = true;
    for (int l= 0; l < noUse.length; l++) {
      if (s.equals(noUse[l])) {
        bb = false;
      }
    }
    return bb;
  }
  /**
   * Method to get int[] quantity which holds the quantity in the given string
   * return that as a int array
   */
  public int[] getQuantity() {
    return quantity;
  }
  /**
   * Method to get  int[] amounts which holds the quantity in the given string
   * return that as a int array
   */
  public int[] getAmounts() {
    return amounts;
  }
}