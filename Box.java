import processing.core.*;
import processing.data.*;

/**
 * A class that represents a box for the word cloud.
 * A Box includes a x,y coordinate and width and height
 */
public class Box {
  ////////////////////////// fields ///////////////////////////////////
  /** the PApplet**/
  private PApplet p;
  /** the width of the virtual box where the words are going to drawn on */
  private int boxWidth;
  /** the height of the virtual box where the words are going to drawn on */
  private int boxHeight;
  /** the  X co-ordinate of the corner of the  box where the words are going to drawn in */
  private int startX;
  /** the Y co-ordinate  of the corner of the  box where the words are going to drawn in */
  private int startY;

  ///////////////////////Constructors//////////////////////////

  /**
   * A constructor that creates a new Box  
   * @param startX, startY, boxWidth, boxHeight
   */
  public Box(int startX, int startY, int boxWidth, int boxHeight) {
    this.startX=startX;
    this.startY=startY;
    this.boxWidth=boxWidth;
    this.boxHeight=boxHeight;
  }
  ///////////////////////////Methods////////////////////////////////
  /**
   * Method to build a  box where the words should be drawn in
   * @param PApplet
   */
  public  void drawBox(PApplet p) {
    p.rectMode(p.CORNER);
    p.smooth();
    p.stroke(0);
    p.rect(this.startX, this.startY, this.boxWidth, this.boxHeight, 30);
    p.fill(255);
  }

  /**
   * Method to get the startX
   * @return startX
   */
  public int getStartX() {
    return startX;
  }
  /**
   * Method to get the startY
   * @return startY
   */
  public int  getStartY() {
    return startY;
  }
  /**
   * Method to get the boxWidth
   * @return boxWidth
   */
  public int  getBoxWidth() {
    return boxWidth;
  }
  /**
   * Method to get the boxHeight
   * @return boxHeight
   */
  public int getBoxHeight() {
    return boxHeight;
  }
}