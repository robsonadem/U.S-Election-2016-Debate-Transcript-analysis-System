PShape hillary;
PShape trump;
PFont font;
PImage bg;
String [] wordsTrump1, wordsTrump2, wordsTrump3, wordsHillary1, wordsHillary2, wordsHillary3;
int []     freqTrump1, freqTrump2, freqTrump3, freqHillary1, freqHillary2, freqHillary3;
int maxFont=100;
int minFont=30;
boolean sep26Pressed, oct9Pressed, oct19Pressed;
IntDict inventory;
Cloud trumpCloud1, trumpCloud2, trumpCloud3, tweetsTrump;
Cloud hillaryCloud1, hillaryCloud2, hillaryCloud3, tweetsHillary;

void setup() {
  size(1600, 700, P3D);
  bg=loadImage("bg.jpg");
  background(bg);
  font=createFont("alpha.ttf", 20);
  hillary=loadShape("hill.svg");
  trump=loadShape("don.svg");
  
  //setting up the upper two icons
  ellipseMode(CORNER);
  rectMode(CORNER);
  textAlign(CENTER);
  textFont(font, 20);
  fill(250,8,36);
  ellipse(360,0,100,100);
  shape(trump,350,0,100,100);
  fill(18,61,224);
  ellipse(1170,0,100,100);
  shape(hillary,1150,0,100,100);
  noFill();

  inventory = new IntDict();
  //creating a transcipt for three debates
  Transcript first= new Transcript( "Sep_26_Hofstra.txt", "HOLT", this);
  //this subclass created under transcript because there were two moderators on the second debate
  TwoSpeaker second = new TwoSpeaker("Oct_9_Missouri.txt", "RADDATZ", "COOPER", this);
  Transcript third= new Transcript( "Oct_19_LasVegas.txt", "WALLACE", this);
  Lemmatization l = new Lemmatization("lemmatization-en.txt", this);

  //creating frequency objects to split the transcripts based on the speakers 
  Frequency trumpFirst = new Frequency(first.getTrump(), 100, l, this);
  Frequency trumpSecond= new Frequency(second.getTrump(), 100, l, this);
  Frequency trumpThird=  new Frequency(third.getTrump(), 100, l, this);

  Frequency hillaryFirst= new Frequency(first.getClinton(), 100, l, this);
  Frequency hillarySecond= new Frequency(second.getClinton(), 100, l, this);
  Frequency hillaryThird= new Frequency(third.getClinton(), 100, l, this);

  // using the  frequency objects getting the words to display
  wordsTrump1=trumpFirst.getWords();
  wordsTrump2=trumpSecond.getWords();
  wordsTrump3=trumpThird.getWords();

  wordsHillary1=hillaryFirst.getWords();
  wordsHillary2=hillarySecond.getWords();
  wordsHillary3=hillaryThird.getWords();
 // using the frquency objects getting the frequency or quantity of each words and return array of descending frequencies
  freqTrump1=trumpFirst.getQuantity();
  freqTrump2=trumpSecond.getQuantity();
  freqTrump3=trumpThird.getQuantity();

  freqHillary1=hillaryFirst.getQuantity();
  freqHillary2=hillarySecond.getQuantity();
  freqHillary3=hillaryThird.getQuantity();

  //creating Clouds for each candidate with their repective three debates
  trumpCloud1= new Cloud(this, wordsTrump1, freqTrump1, font, 50, 100, 700, 500);
  trumpCloud2= new Cloud(this, wordsTrump2, freqTrump2, font, 50, 100, 700, 500);
  trumpCloud3= new Cloud(this, wordsTrump3, freqTrump3, font, 50, 100, 700, 500);

  hillaryCloud1= new Cloud(this, wordsHillary1, freqHillary1, font, 800, 100, 700, 500);
  hillaryCloud2= new Cloud(this, wordsHillary2, freqHillary2, font, 800, 100, 700, 500);
  hillaryCloud3= new Cloud(this, wordsHillary3, freqHillary3, font, 800, 100, 700, 500);
  
}
void draw() {
  
 //drawing the three icons with their default details 
  rect(150, 620, 150, 50, 30);
  rect(300, 620, 150, 50, 30);
  rect(450, 620, 150, 50, 30);
  noFill();
  fill(21, 28, 90);
  text("Sep 26", 225, 650);
  text("oct 9", 375, 650);
  text("oct 19", 525, 650);
  noFill();


  if (sep26Pressed) {
    //changing the default icon details to indicate that the icon have been pressed
    fill(250, 8, 56);
    stroke(0);
    strokeWeight(5);
    rect(150, 620, 150, 50, 30);
    fill(21, 28, 90);
    text("Sep 26", 225, 650);
    noStroke();
    fill(255);
    //redraw both windows to update the window every time mouse is clicked
    rect(50, 100, 700, 500, 30);
    rect(800, 100, 700, 500, 30);
    //drawing the words on the cloud
    trumpCloud1.drawCloud(this);
    hillaryCloud1.drawCloud(this);
  }
  if (oct9Pressed ) {
     //changing the default icon details to indicate that the icon have been pressed
    fill(250, 8, 56);
    stroke(0);
    strokeWeight(5);
    rect(300, 620, 150, 50, 30);
    fill(21, 28, 90);
    text("oct 9", 375, 650);
    noStroke();
    fill(255);
    //redraw both windows to update the window every time mouse is clicked
    rect(50, 100, 700, 500, 30);
    rect(800, 100, 700, 500, 30);
    //drawing the words on the cloud
    trumpCloud2.drawCloud(this);
    hillaryCloud2.drawCloud(this);
  }
  if (oct19Pressed ) {
     //changing the default icon details to indicate that the icon have been pressed
    fill(250, 8, 56);
    stroke(0);
    strokeWeight(5);
    rect(450, 620, 150, 50, 30);
    fill(21, 28, 90);
    text("oct 19", 525, 650);
    noStroke();
    fill(255);
    //redraw both windows to update the window every time mouse is clicked
    rect(50, 100, 700, 500, 30);
    rect(800, 100, 700, 500, 30);
    //drawing the words on the cloud
    trumpCloud3.drawCloud(this);
    hillaryCloud3.drawCloud(this);
  }
}
void mouseClicked() {

  /*the drawCloud function disables the loop. As a result it hults the functions 
   of draw. To solve this, loop is reinforced whenever a mouse is clicked. 
   */
  loop();

  /*setting boolean values to true when mouse is pressed in t
   he regions specified which correspond to the three icons left bottom corner*/
  sep26Pressed=(mouseX>150 && mouseX<150+150) && (mouseY>620 && mouseY<620+50);
  oct9Pressed=(mouseX>300 && mouseX<300+150) && (mouseY>620 && mouseY<620+50);
  oct19Pressed=(mouseX>450 && mouseX<450+150) && (mouseY>620 && mouseY<620+50);
}