import org.gicentre.utils.stat.*;
import java.util.*;

PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
final int EVENT_BUTTON4=4;
final int EVENT_BUTTON5=5;
final int EVENT_BUTTON6=6;
final int EVENT_BUTTON7=7;
final int EVENT_BUTTON8=8;
final int EVENT_BUTTON9=9;
final int EVENT_BUTTON10=10;
final int EVENT_BUTTON11=11;
final int EVENT_BUTTON12=12;
final int EVENT_BUTTON13 = 13;
final int EVENT_EXIT = 99;
final int EVENT_NULL=0;

final int MENU_BUTTON_WIDTH = 400;
final int MENU_BUTTON_HEIGHT = 50;

int count = 0;
ArrayList widgetList;
ArrayList screenList;
Widget widget1, widget2, widget3, widget4, widget5, widget6, widget7, widget8, widget9, widget11, widget12, exit;
Screen current, home, ap, pot, text, table;

ArrayList<Float> yrs;
PFont font;
ArrayList input;

XYChart xy;
BarChart b;
UserInput ui;
PImage background, power, map, average, prices, high, low, houses, pieChart;

public ArrayList<Data> DATA = new ArrayList<Data>();
public float[] YEARS = {95, 96, 97, 98, 99, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
//following string array needed for x-axis of graphs
public float[] YEARS_FULL = {1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016};

String[] a;

boolean end = false;
Data d;

int y=50;
int i=0;

ArrayList l = new ArrayList();

final int width = 1680;
final int height = 1050;

void settings(){
  
  size(width, height);
}

void setup() {

  a = loadStrings("pp-50k.csv");

  for (int i=0; i<a.length; i++) {
    d = new Data(a[i]);
    DATA.add(d);
  }

  background = loadImage("background.jpg");
  power = loadImage("power.png");
  map = loadImage("uk.png");
  average = loadImage("average.png");
  prices = loadImage("prices.png");
  high = loadImage("high.png");
  low = loadImage("low.png");
  houses = loadImage("houses.png");
  pieChart = loadImage("pieChart.png"); 

  //size(800, 800);

  xy = new XYChart(this);
  b = new BarChart(this);

  home = new HomeScreen(background);
  pot = new XYScreen(background, xy);
  ap = new BarScreen(background, b);
  text = new TextScreen(background);
  current = home;

  widget4=new Widget(width/3, 250, MENU_BUTTON_WIDTH-50, MENU_BUTTON_HEIGHT, 
    "Select", color(13, 93, 104), color(255), 
    stdFont, EVENT_BUTTON4, 50);

  widget1=new Widget(width/3, 310, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Average Price", color(13, 93, 104), color(255), 
    stdFont, EVENT_BUTTON1, 30);

  widget2=new Widget(width/3, 370, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Price Over Time", color(13, 93, 104), color(255), 
    stdFont, EVENT_BUTTON2, 30);

  widget5 = new Widget(width/3, 430, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Highest Prices", color(13, 93, 104), color(255), 
    stdFont, EVENT_BUTTON5, 30);

  widget6 = new Widget(width/3, 490, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Lowest Prices", color(13, 93, 104), color(255), 
    stdFont, EVENT_BUTTON6, 30);

  widget7 = new Widget(width/3, 550, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Property Types", color(13, 93, 104), color (255), 
    stdFont, EVENT_BUTTON7, 30);

  widget3=new Widget(30, 30, 30, 30, 
    "←", color(13, 93, 104), color(0), 
    stdFont, EVENT_BUTTON3, 15);

  widget8=new Widget(width/3+MENU_BUTTON_WIDTH-50, 250, 50, MENU_BUTTON_HEIGHT, 
    "↓", color(19, 117, 130), color(255), 
    stdFont, EVENT_BUTTON8, 30);

  widget9 = new Widget(width/3, 610, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Property Types(Pie Chart)", color(13, 93, 104), color (255), 
    stdFont, EVENT_BUTTON9, 30);

  widget11 = new Widget(width-50, 50, 50, 50, "↑", color(13, 93, 104), color(0), stdFont, EVENT_BUTTON11, 30);

  widget12 = new Widget(width-50, height-50, 50, 50, "↓", color(13, 93, 104), color(0), stdFont, EVENT_BUTTON12, 30);
  

  exit = new Widget(20, height-50, 30, 30, "", color(255, 0, 0), color(0), stdFont, EVENT_EXIT, 1);

  home.addWidget(widget4);
  home.addWidget(widget8);
  home.addWidget(exit);
  pot.addWidget(exit);
  ap.addWidget(exit);
  text.addWidget(exit);
  pot.addWidget(widget3);
  pot.setUI(ui, 100, 30, 600, 30);
  text.addWidget(widget3);
  text.addWidget(widget11);
  text.addWidget(widget12);
  text.setUI(ui, 100, 30, 600, 30);
  ap.addWidget(widget3);
  ap.setUI(ui, 100, 30, 500, 30);
}

void draw() {
  image(background, 0, 0); 
  current.draw();
  image(power, 20, height-50);
}

void mousePressed()
{
  int event;
  event = current.getEvent();
  switch(event)
  {
  case EVENT_BUTTON1:
    current = ap;
    current.avgPrice = true;
    current.propType = false;
    break;

  case EVENT_BUTTON2:
    current = pot;
    break;

  case EVENT_BUTTON3:
    current.clearSearchBar();
    current = home;
    count++;
    down = false;
    widget1.pullDownMenu(widget1, widget2, widget5, widget6, widget7, widget9);
    break;

  case EVENT_BUTTON5:
    current = text;
    current.setHighest();
    break;

  case EVENT_BUTTON6:
    current = text;
    current.setLowest();
    break;

  case EVENT_BUTTON7:
    current = ap;
    current.propType = true;
    current.avgPrice = false;
    break;

  case EVENT_BUTTON8:
    count++;
    down = true;
    if (count % 2 == 0)
      down = false;
    widget1.pullDownMenu(widget1, widget2, widget5, widget6, widget7, widget9);
    break;

  case EVENT_BUTTON9:
    current = ap;
    current.propType = false;
    current.avgPrice = false;
    current.pie=true;
    break;

  case EVENT_BUTTON11:
    current.moveUp();
    break;

  case EVENT_BUTTON12:
    current.moveDown();
    break;

  case EVENT_EXIT:
    System.exit(0);
  }
  current.canTakeKeys();
}

void keyPressed() {  
  current.takeKeys(key);
}