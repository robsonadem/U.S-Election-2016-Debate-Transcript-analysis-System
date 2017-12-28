import processing.core.*;
import processing.data.*;

/**
 * A class that represent the word clouds.
 * A word cloud includes an array of words, a word size, a box to place the word in
 */
public class Cloud
{  
  ////////////////////////// fields ///////////////////////////////////
 
  /** the font used to display the words */
  private PFont font;
  /** the value used to scale the font */
  private int fontControl;
  /** the box where the words are drawn in*/
  private Box box;
  /** the array of words to display */
  private String [] displayWords;
  /** the array of frequency of each word in a given data */
  private int [] freq;
  /** the lowest frequency */
  private int lowestFreq;
  /** the highest frequency */
  private int highestFreq;
  /** the lowest font sixe */
  private int minFont=30;
  /** the highest font size */
  private int maxFont=100;



  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that creates a new WordCloud
   * @param p the PApplet object
   * @param displayWords  the set of words to display sorted by frequency
   * @param freq the frequencies for each displayWords
   * @param startX, startY, boxWidth,boxHeight details to create a box
   */
  public Cloud(PApplet p ,String [] displayWords, int [] freq, PFont font, int startX, int startY, int boxWidth, int boxHeight)
  {
    //setting values of instance variables
    this.displayWords=displayWords;
    this.freq= freq;
    this.font=font;
    this.fontControl=50;
    this.lowestFreq=this.freq[displayWords.length-1];
    this.highestFreq=this.freq[1];
    this.box= new Box(startX, startY, boxWidth, boxHeight);
    box.drawBox(p);
  }

  ///////////////////////////Methods////////////////////////////////
  /**
   * Method to draw the cloud 
   */
  public void drawCloud(PApplet p) {

    for (int i=1; i<this.displayWords.length; i++) {
      float wordSize=p.map(this.freq[i], this.lowestFreq, this.highestFreq, this.minFont, this.maxFont);
      int z=drawWord(p, displayWords[i], wordSize);
    }
  }
  /**
   * Method to draw a word in relation to its frequency
   * @param word the word to be drawn
   * @param fontSize the size for the font
   * @return 1 or 0 based on whether their enough space in the region  
   */

/**
 * Method to draw a word in relation to its frequency
 * @param word the word to be drawn
 * @param fontSize the size for the font
 * @return 1 or 0 based on whether their enough space in the region  
 */
public int drawWord(PApplet p, String word, float fontSize) {
  int x=0, y=0;
  int h = (int)fontSize;
  p.textFont(font, fontSize);
  int w = (int)(p.textWidth(word));
  //create a graphics to hold the words
  PGraphics textBox = p.createGraphics(w, h);
  textBox.beginDraw();
  textBox.background(p.color(255), 0);
  textBox.textAlign(p.CENTER, p.CENTER);
  textBox.translate(w/2, fontSize/2);
  textBox.scale(fontSize / this.fontControl);
  textBox.textFont(font);
  textBox.fill(p.random(255), p.random(255), p.random(255));
  textBox.text(word, 0, 0);
  textBox.endDraw();
  //based on textbox graphics create an image
  PGraphics gMask = p.createGraphics(w, h);
  gMask.beginDraw();
  gMask.image(textBox, 0, 0);
  gMask.filter(p.ERODE);  
  gMask.filter(p.ERODE);
  gMask.endDraw();

  /*goes through the specified box and draws, with no overlap, the words 
  till it runs out of space*/
  
  for (int lookForSpace=50; lookForSpace>0; lookForSpace--) {
    int startX=this.box.getStartX();
    int startY=this.box.getStartY();
    int boxWidth=this.box.getBoxWidth();
    int boxHeight=this.box.getBoxHeight();

    x = (int)p.random(startX, startX+boxWidth);
    y = (int)p.random(startY, startY+boxHeight);
    //boolean telling if there is an enough space
    boolean EnoughSpace = true;
    
    for (int i = 0; i< w && EnoughSpace; i++) {
      for (int j = 0; j<h && EnoughSpace; j++) {
        int c=gMask.get(i, j);
        int d=p.get(x+i, y+j);
        //preventing overlapping by sampling pixels at the trial positions
        if (p.red(c)!=255 && p.blue(c)!=255 && p.green(c)!=255) {
          if (p.red(d)!=255 && p.blue(d)!=255 && p.green(d)!=255) {
            EnoughSpace = false;
          }
        }
      }
    }
    if (EnoughSpace) {
      p.pushMatrix();
      p.image(textBox, x, y);
      p.popMatrix();
      p.noLoop();//if this loops the words will be drawn multiple times
      return 1;
    }
  }
  p.noLoop();//if this loops the words will be drawn multiple times
  return 0;
}
/**
 * Method to get the lowestFreq
 * @return lowestFreq
 */
public int getLowestFreq() {
  return lowestFreq;
}
/**
 * Method to get the lowestFreq
 * @return lowestFreq
 */
public int getHighestFreq() {
  return highestFreq;
}
}