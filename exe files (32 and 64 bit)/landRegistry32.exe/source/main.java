import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.gicentre.utils.stat.*; 
import java.util.*; 
import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class main extends PApplet {




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

public void settings(){
  
  size(width, height);
}

public void setup() {

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
    "\u2190", color(13, 93, 104), color(0), 
    stdFont, EVENT_BUTTON3, 15);

  widget8=new Widget(width/3+MENU_BUTTON_WIDTH-50, 250, 50, MENU_BUTTON_HEIGHT, 
    "\u2193", color(19, 117, 130), color(255), 
    stdFont, EVENT_BUTTON8, 30);

  widget9 = new Widget(width/3, 610, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, 
    "Property Types(Pie Chart)", color(13, 93, 104), color (255), 
    stdFont, EVENT_BUTTON9, 30);

  widget11 = new Widget(width-50, 50, 50, 50, "\u2191", color(13, 93, 104), color(0), stdFont, EVENT_BUTTON11, 30);

  widget12 = new Widget(width-50, height-50, 50, 50, "\u2193", color(13, 93, 104), color(0), stdFont, EVENT_BUTTON12, 30);
  

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

public void draw() {
  image(background, 0, 0); 
  current.draw();
  image(power, 20, height-50);
}

public void mousePressed()
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

public void keyPressed() {  
  current.takeKeys(key);
}
//Creats a BarChart on screen of the average price of properties in a specified region

/*Four constructors:
 -(BarChart barChart, ArrayList properties, String region) -> For the average of all counties, districts, etc..
 -(ArrayList properties, String county, String district, String town, BarChart barChart) -> For a specific town
 -(ArrayList properties, String county, String district, BarChart barChart) -> For a specific district
 -(ArrayList properties, String county, BarChart barChart) -> For a specific county
 
 Create the AvgPriceBar object and call the draw() method to draw the barchart.
 
 */

class AvgPriceBar extends Graph
{
  ArrayList properties;
  float[] averagePricesForY;  //  average price for each region for the bar chart
  String[] regionsForX;        //  list of regions 
  BarChart barChart;

  //Index of ArrayList -> Street = 6, Locality = 7, Town = 8, District = 9, County = 10
  int region;

  String county;
  String district;
  String town;

  final int yAxisWidth = 55;

  //Ensures the average price of a region only appears once in averagePriceOfRegion[]
  //Ensures each region only appears once in listOfPlacesForX[];
  boolean uniqueRegion;

  AvgPriceBar() {
  }

  AvgPriceBar(BarChart barChart, ArrayList properties, String region)
  {
    region.toLowerCase();
    switch(region) {
    case "street": 
      this.region = 6;
      break;
    case "locality": 
      this.region = 7;
      break;
    case "town": 
      this.region = 8;
      break;
    case "district": 
      this.region = 9;
      break;
    case "county": 
      this.region = 10;
      break;
    }
    this.properties = properties;

    this.barChart = barChart;
    setup();
  }

  AvgPriceBar(ArrayList properties, String county, String district, String town, BarChart barChart)
  {
    this.town = town;
    this.district = district;
    this.county = county;
    this.region = 7;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;

    setup();
  }

  AvgPriceBar(ArrayList properties, String county, String district, BarChart barChart)
  {
    this.district = district;
    this.county = county;
    this.region = 8;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;
    setup();
  }

  AvgPriceBar(ArrayList properties, String county, BarChart barChart)
  {
    this.county = county;
    this.region = 9;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;
    //println(this.properties.size());
    setup();
  }

  public void setup()
  {

    averagePricePerRegion(properties);
    this.properties = alphabeticalOrder(this.properties, region);
    String[] sa = createListOfRegions(properties);
    barChart.setData(averagePricesForY);
    barChart.showValueAxis(true);
    barChart.setValueFormat("\u20ac#");
    barChart.showCategoryAxis(false);
    barChart.setBarLabels(sa);
    barChart.setBarColour(color(0, 112, 114));
  }


  public void draw()
  {
    barChart.draw(width/30, height/8, width/1.2f, height/1.5f);
    readXAxis();
  }

  //Fills averagePricesForY[] with the average values of all the places. 
  //ArrayList propertyList parameter is the ArrayList of all the inputted Data objects

  public void averagePricePerRegion(ArrayList propertyList)
  {
    ArrayList averagePriceOfRegion = new ArrayList();
    ArrayList pricesOfCurrentRegion = new ArrayList();
    ArrayList completedRegions = new ArrayList();
    //println(propertyList.size());
    for (int i = 0; i < propertyList.size(); i++)
    {
      uniqueRegion = true;
      Data d = (Data)propertyList.get(i);
      String regionString = d.arr[region];
      for (int j = 0; j < completedRegions.size(); j++)
      {
        String s2 = (String)completedRegions.get(j);
        if (regionString.equals(s2))
        {
          uniqueRegion = false;
        }
      }
      if (uniqueRegion)
      {
        completedRegions.add(regionString);
        for (int j = 0; j < propertyList.size(); j++)
        {
          Data d2 = (Data)propertyList.get(j);
          if (regionString.equals(d2.arr[region]))
          {
            pricesOfCurrentRegion.add(/*Integer.parseInt*/(d2.arr[0]));
          }
        }
        float totalSum = avgPrice(pricesOfCurrentRegion);
        averagePriceOfRegion.add(totalSum);
      }
    }
    averagePricesForY = new float[averagePriceOfRegion.size()];
    for (int i = 0; i < averagePricesForY.length; i++)
      averagePricesForY[i] = (float)averagePriceOfRegion.get(i);
  }

  //Fills regionsForX[] with all regions to be displayed when the mouse is over each barline.
  //ArrayList propertyList parameter is an ArrayList of all the inputted Data Objects
  public String[] createListOfRegions(ArrayList propertyList)
  {
    ArrayList listOfPlacesForX = new ArrayList();
    for (int i = 0; i < propertyList.size(); i++)
    {
      uniqueRegion = true;
      Data d = (Data)propertyList.get(i);
      String currentRegion = d.arr[region];
      for (int j = 0; j < listOfPlacesForX.size(); j++)
      {
        String otherRegion = (String)listOfPlacesForX.get(j);
        if (currentRegion.equals(otherRegion))
          uniqueRegion = false;
      }
      if (uniqueRegion)
      {
        listOfPlacesForX.add(currentRegion);
        //println(currentRegion);
      }
    }
    regionsForX = new String[listOfPlacesForX.size()];
    for (int i = 0; i < regionsForX.length; i++)
      regionsForX[i] = (String)listOfPlacesForX.get(i);

    return regionsForX;
  }



  public ArrayList categorisedPropertyList(ArrayList propertyList)
  {
    ArrayList<Data> categorisedList = new ArrayList<Data>();
    String overheadRegion = "";
    String currentRegion;
    switch(region+1)
    {
    case 10: 
      overheadRegion = this.county.toLowerCase();
      break;
    case 9: 
      overheadRegion = this.district.toLowerCase();
      break;
    case 8:
      overheadRegion = this.town.toLowerCase();
      break;
    }
    for (int i = 0; i < propertyList.size(); i++)
    {
      Data d = (Data)propertyList.get(i);
      currentRegion = d.arr[region+1].toLowerCase();
      if (currentRegion.equals(overheadRegion))
      {
        categorisedList.add(d);
      }
    }
    return categorisedList;
  }


  //Takes the current mouse position and displays the price and name of the region being hovered over
  public void readXAxis()
  {
    if (30 + yAxisWidth < mouseX && mouseX < width && 30 + yAxisWidth < mouseY && mouseY < height-90) {
      //line(mouseX, 0, mouseX, height);
      int compPath = PApplet.parseInt(map(mouseX, 30+yAxisWidth, width, 0, averagePricesForY.length));
      compPath = abs(compPath);
      fill(255);
      text(regionsForX[compPath], width/2, height/5);
      text(averagePricesForY[compPath], width/2, height/5+15);
    }
  }
}
//Author - Aaron Duggan
//getMonth and getYear - Sean Candon

class Data {

  String s; 
  String[] arr;
  String price, dateOfSale, postCode, propertyType, oldNew, numName, 
    street, locality, town, district, county;

  Data(String s) {
    this.s = s;
    arr = s.split(",");

    price = arr[0]; 
    dateOfSale = arr[1]; 
    postCode = arr[2]; 
    propertyType=arr[3];
    oldNew = arr[4]; 
    numName = arr[5]; 
    street = arr[6].toLowerCase(); 
    locality = arr[7].toLowerCase();
    town = arr[8].toLowerCase(); 
    district = arr[9].toLowerCase(); 
    county = arr[10].toLowerCase();
  }

  //returns month of data object as float
  public float getMonth() {

    String[] ar = dateOfSale.split("/");
    return Integer.parseInt(ar[1]);
  }

  //returns year of dat object as float
  public float getYear() {
    String[] ar = dateOfSale.split("/");
    String[] ar2 = ar[2].split(" ");
    return Float.parseFloat(ar2[0]);
  }

  public int getPrice() {
    return Integer.parseInt(this.price);
  }
  
// getDateOfSale and getPropType added by Sin\u00e9ad Galbraith
  public String getDateOfSale()
  {
    return this.dateOfSale;
  }
  
  public String getPropType()
  {
    return this.propertyType;
  }

  public String getCounty() {
    return this.county;
  }

  public String toString() {
    return this.s;
  }
}
//ChangeInTime - Sean Candon
//AvgPrice - Aaron Duggan

class Graph { 

  XYChart xy;

  public void draw() {
    xy.draw(width/30, height/8, width/1.2f, height/1.5f);
  }

  //function to set XY graph
  public void setXY(XYChart xy, float[] list) {
    xy.setData(YEARS_FULL, list); 
    xy.setLineColour(255);
    xy.setLineWidth(1);
    xy.setMinY(0);
    xy.setYFormat("\u00a3###,###");
    xy.setXFormat("0000");
    xy.showXAxis(true);
    xy.showYAxis(true);
  }

  //funtion that returns average price of a list of properties
  public int avgPrice(ArrayList a) {

    int d = a.size();
    int sum=0;
    for (int i=0; i<d; i++) {
      int p = Integer.parseInt((String)a.get(i));
      sum += p;
    }
    return sum/d;
  }

  //function that returns array of average prices 
  public float[] avgPrices(ArrayList list) {

    float[] prices = new float[22];

    for (int i=0; i<YEARS.length; i++) {
      int avg=0;
      ArrayList pr = new ArrayList();
      for (int n=0; n<list.size(); n++) {  
        Data d = (Data)list.get(n);
        float year = d.getYear();
        String p = d.price;
        if (YEARS[i] == year)
          pr.add(p);
      }
      if (pr.size()>0)
        avg = avgPrice(pr);
      else
        avg = 0;
      prices[i] = avg;
      //print(avg + " ");
    }

    return prices;
  }

  //Returns the given ArrayList in alphabetical order
  public ArrayList alphabeticalOrder(ArrayList a, int region)
  {
    ArrayList listOfPlaces = new ArrayList();
    ArrayList orderedList = new ArrayList();
    boolean sortedSpace = false;

    for (int i = 0; i < a.size(); i++)
    {
      Data d = (Data)a.get(i);
      String place = d.arr[region];
      listOfPlaces.add(place);
    }
    Collections.sort(listOfPlaces, String.CASE_INSENSITIVE_ORDER);
    for (int i = 0; i < listOfPlaces.size(); i++)
    {
      sortedSpace = false;
      int j = 0;
      while (j < listOfPlaces.size() && !sortedSpace)
      {
        Data d = (Data)a.get(j);
        String somePlace = d.arr[region];
        if (somePlace.equals((String)listOfPlaces.get(i)))
        {
          orderedList.add(d);
          sortedSpace = true;
        }
        j++;
      }
    }
    return orderedList;
  }
}

class ChangeInPrice extends Graph {

  ArrayList l1;

  //change in price over time per county
  ChangeInPrice(String c, XYChart xy) {
    this.xy = xy;
    //this.b = b; 
    l1 = new ArrayList(); 
    for (int i=0; i<DATA.size(); i++) {  
      Data da = ((Data)DATA.get(i));
      if (da.county.equals(c)) {
        l1.add(da);
      }
    }
    float[] list = avgPrices(l1);
    setXY(xy, list);
  }

  //change in price over time per district
  ChangeInPrice(String c, String d, XYChart xy) {

    this.xy = xy;
    l1 = new ArrayList();
    for (int i=0; i<DATA.size(); i++) {
      Data da = ((Data)DATA.get(i));
      if ((da.county.equals(c)) && (da.district.equals(d)))
        l1.add(da);
    }

    float[] list = avgPrices(l1);
    setXY(xy, list);
  }

  ChangeInPrice(String c, String d, String t, XYChart xy) {

    this.xy = xy;
    l1 = new ArrayList();
    for (int i=0; i<DATA.size(); i++) {
      Data da = ((Data)DATA.get(i));
      if (da.county.equals(c) && da.district.equals(d) && da.town.equals(t))
        l1.add(da);
    }
    float[] prices = avgPrices(l1);
    setXY(xy, prices);
  }
}
public class PieCharts extends AvgPriceBar {

  int detached;
  int semiDetached;
  int terraced;
  int flatsMaisonettes;
  int other;


  final float diameter = width/3;

  float[] angles;

  PieCharts(ArrayList properties, String county, String district, String town)
  {
    this.town = town;
    this.district = district;
    this.county = county;
    this.region = 7;
    this.properties = categorisedPropertyList(properties);

    setup();
  }

  PieCharts(ArrayList properties, String county, String district)
  {
    this.district = district;
    this.county = county;
    this.region = 8;
    this.properties = categorisedPropertyList(properties);

    setup();
  }

  PieCharts(ArrayList properties, String county)
  {
    this.county = county;
    this.region = 9;
    this.properties = categorisedPropertyList(properties);
    setup();
  }


  public void setup()
  {
    this.angles = angles(this.properties);
    //println(angles[0] + " " + angles[1] + " " + angles[2] + " " + angles[0] + " " + angles[0]);
    //noLoop();
    noStroke();
  }

  public void draw()
  {
    pieChart(this.diameter, this.angles);
    drawText();
  }



  public float[] angles(ArrayList categorisedProperties)
  {
    for (int i = 0; i < categorisedProperties.size(); i++)
    {
      Data d = (Data)categorisedProperties.get(i);
      String s = d.arr[3].toLowerCase();
      switch(s)
      {
      case "d": 
        this.detached++;
        break;
      case "s":
        this.semiDetached++;
        break;
      case "t":
        this.terraced++;
        break;
      case "f":
        this.flatsMaisonettes++;
        break;
      case "o":
        this.other++;
        break;
      }
    }
    float[] listOfData = {this.detached, this.semiDetached, this.terraced, this.flatsMaisonettes, this.other};

    float[] listOfAngles = new float[5];

    float upperBracket = this.detached + this.semiDetached + this.terraced + this.flatsMaisonettes + this.other;

    for (int i = 0; i < listOfAngles.length; i++)
    {
      float currentAngle = (map(listOfData[i], 0, upperBracket, 0, 360));
      listOfAngles[i] = currentAngle;
    }
    return listOfAngles;
  }

  public void pieChart(float diameter, float[] data) {
    float lastAngle = 0;
    for (int i = 0; i < data.length; i++) {
      switch(i)
      {
      case 0: 
        fill(255, 0, 0);    //red
        break;
      case 1: 
        fill(0, 255, 0);    //green
        break;
      case 2: 
        fill(0, 0, 255);    //blue
        break;
      case 3: 
        fill(255, 255, 0);  //yellow
        break;
      case 4: 
        fill(153, 0, 153);  //purple
        break;
      }
      arc(width/2, height/1.7f, diameter, diameter, lastAngle, lastAngle+radians(angles[i]));
      lastAngle += radians(angles[i]);
    }
  }

  public void drawText()
  {
    int ypos = width/4;
    fill(255);
    text("Detached = Red", 150, ypos);
    text("Semi-Detached = Green", 150, ypos+30);
    text("Terraced = Blue", 150, ypos+60);
    text("Flats/Maisonettes = Yellow", 150, ypos+90);
    text("Other = Purple", 150, ypos+120);
  }
}
/*Three constructors:
 -(ArrayList properties, String county, String district, String town, BarChart barChart) -> For a specific town
 -(ArrayList properties, String county, String district, BarChart barChart) -> For a specific district
 -(ArrayList properties, String county, BarChart barChart) -> For a specific county
 */

//Author - Aaron Duggan

class PropertyTypeBar extends AvgPriceBar {
  int detached;
  int semiDetached;
  int terraced;
  int flatsMaisonettes;
  int other; 

  PropertyTypeBar(ArrayList properties, String county, String district, String town, BarChart barChart)
  {
    this.county = county;
    this.district = district;
    this.town = town;
    this.region = 9;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;
    setup();
  }

  PropertyTypeBar(ArrayList properties, String county, String district, BarChart barChart)
  {
    this.district = district;
    this.county = county;
    this.region = 8;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;
    setup();
  }

  PropertyTypeBar(ArrayList properties, String county, BarChart barChart)
  {
    this.county = county;
    this.region = 9;
    this.properties = categorisedPropertyList(properties);
    this.barChart = barChart;
    setup();
  }

  public void setup()
  {
    //println(this.properties);
    this.detached = 0;
    this.semiDetached = 0;
    this.terraced = 0;
    this.flatsMaisonettes = 0;
    String[] propertyTypes = {"Detached", "Semi-Detached", "Terraced", "Flats/Maisonettes", "Other"};
    float[] yPropertyValues = numberOfEachType(properties);
    barChart.setData(yPropertyValues);
    barChart.setBarLabels(propertyTypes);
    barChart.setBarColour(color(0, 112, 114));
    barChart.showValueAxis(true);
    barChart.showCategoryAxis(true);
  }

  public void draw()
  {
    barChart.draw(width/7, height/8, width/1.2f, height/1.5f);
  }

  public float[] numberOfEachType(ArrayList categorisedProperties)
  {
    for (int i = 0; i < categorisedProperties.size(); i++)
    {
      Data d = (Data)categorisedProperties.get(i);
      String s = d.arr[3].toLowerCase();
      //println(s);
      switch(s)
      {
      case "d": 
        this.detached++;
        break;
      case "s":
        this.semiDetached++;
        break;
      case "t":
        this.terraced++;
        break;
      case "f":
        this.flatsMaisonettes++;
        break;
      case "o":
        this.other++;
        break;
      }
    }
    float[] yPropertyValues = {this.detached, this.semiDetached, this.terraced, this.flatsMaisonettes, this.other};
    return yPropertyValues;
  }
}
//Restructured by Sean Candon:
// - Broke the class into subclasses XYScreen (which displays line graphs),
//   TextScreen (which as of now does nothing except draws widgets
//   and search bar), and BarScreen (which draws bar charts), each of which
//   extend from Screen super class.

// Extended by Sin\u00e9ad Galbraith
// -Added getLabel() and respective texts for each screen

// Extended by Arthur Sasunts
// Added getTitle() - placed titles for each screen

class Screen {

  PImage image;
  String title;
  String string;
  String label;
  String text;
  ArrayList widgetList = new ArrayList();
  Graph g;
  UserInput ui;
  boolean searchBar = false;
  boolean changeInPrice = false;
  int x;
  int y;
  int textY = height/8;

  boolean avgPrice = false;
  boolean propType = false;
  boolean table = false;

  boolean highest = false;
  boolean lowest = false;

  boolean pie = false;
  
  public void clearSearchBar(){
    if(searchBar){
      this.ui.clearList();
      this.ui.setDraw(false);
    }
  }

  public void setHighest() {
    highest = true;
    lowest = false;
  }
  public void setLowest() {
    lowest = true;
    highest = false;
  }
  public void getLabel(String text)
  {
    fill(200, 200, 200);
    textSize(20);
    text("Search Criteria:", x, y+60);
    text(text, x, y+80);
  }
  
  public void getTitle(String title, int x)
  {
    fill(255);
    textSize(40);
    text(title, x, 60);
    textSize(30);
  }

  public void setUI (UserInput ui, int x, int y, int width, int height) {
    this.ui = ui;
    this.x = x;
    this.y = y;
    this.ui = new UserInput(x, y, width, height, xy);
    searchBar = true;
  }

  public void canTakeKeys() {
    if (searchBar && ui.mousePressed())
      ui.canType = true;
  }

  public void takeKeys(char key) {
    //if(ui.canType())
    ui.takeKeys(key);
  }

  public void draw() {
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }
  }

  public void addWidget(Widget w) {
    this.widgetList.add(w);
  }

  public void removeWidget(Widget w) {
    this.widgetList.remove(w);
  }

  public int getEvent()
  {   
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);

      int event = aWidget.getEvent(mouseX, mouseY);
      if (event != EVENT_NULL)
      {
        return event;
      }
    }
    return EVENT_NULL;
  }

  public void moveUp() {
    textY+=50;
  }
  public void moveDown() {
    textY-=50;
  }
}

class HomeScreen extends Screen {

  HomeScreen(PImage image) {
    this.image = image;
  }

  public void draw() {
    fill(255);
    textSize(120);
    text("UK LAND REGISTERY", 125, 150);
    textSize(30);
    
   image(map, width/1.7f, height/6);
    
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }
  }
}

class XYScreen extends Screen {

  XYChart xy;

  XYScreen(PImage image, XYChart xy) {
    this.image = image;
    this.xy = xy;
    this.changeInPrice = true;
    this.title = "PRICE OVER TIME";
    this.text = "(county, district, town)";
  }
  public void draw() {
    image(prices, 1050, -10);
    getTitle(title, 730);
    getLabel(text);
    textSize(30);
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }
    if (searchBar) {
      this.ui.setType();
      this.ui.draw();
      if (ui.willDraw()) {
        int queries = ui.getQueries();
        if (this.changeInPrice) { 
          String[] input = ui.getInput();
          if (queries==3)
            g = new ChangeInPrice(input[0], input[1], input[2], xy);
          if (queries == 2)
            g = new ChangeInPrice(input[0], input[1], xy);
          if (queries == 1)
            g = new ChangeInPrice(input[0], xy);
          g.draw();
        }
      }
    }
  }
}

class TextScreen extends Screen {

  TextScreen(PImage image) {
    this.image = image;
    this.text = "(county, district, town, Number Of Searches)";
  }

  public void draw() {
    int inc = 30;
    if (highest) {
      image(high, 1050, 15);
      this.title = "HIGHEST PRICES";
      getTitle(title, 730); 
      getLabel(text);
    } else if (lowest) {
      image(low, 1035, 20);
      this.title = "LOWEST PRICES";
      getTitle(title, 730); 
      getLabel(text);
    }
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }

    if (searchBar) {
      this.ui.setType();
      this.ui.draw();
      if (ui.willDraw()) {
        //text("Sorted by Price", width/2, height/10);
        int queries = ui.getQueries(); 
        String[] input = ui.getInput();
        Data[] sorted = null;
        if (queries==4) {
          ArrayList<Data> a = getTowns(input[0], input[1], input[2]);
          if (highest) {
            sorted = highest(a, Integer.parseInt(input[3]));
          } else if (lowest) {
            sorted = lowest(a, Integer.parseInt(input[3]));
          }
        } else if (queries==3) {
          ArrayList<Data> a = getDistricts(input[0], input[1]);
          if (highest) {
            sorted = highest(a, Integer.parseInt(input[2]));
          } else if (lowest) {
            sorted = lowest(a, Integer.parseInt(input[2]));
          }
        } else if (queries==2) {
          ArrayList<Data> a = getCounties(input[0]);
          if (highest) {
            sorted = highest(a, Integer.parseInt(input[1]));
          } else if (lowest) {
            sorted = lowest(a, Integer.parseInt(input[1]));
          }
        }
        if (sorted!=null) {
          fill(255);
          for (int i=0; i<sorted.length; i++) {
            text(sorted[i].toString(), width/8, textY+inc);
            inc+=30;
          }
        }
      }
    }
  }
}

class BarScreen extends Screen {

  BarChart b;

  BarScreen(PImage image, BarChart b) {
    this.image = image;
    this.b = b;
    this.text = "(county, district, town)";
    //avgPrice = false;
    //propType = false;
  }

  public void draw() {
    if (this.avgPrice) {
      image(average, 940, 20);
      this.title = "AVERAGE PRICE";
      getTitle(title, 630);
      getLabel(text);
    } else if (this.propType) {
      image(houses, 970, 20);
      this.title = "PROPERTY TYPES";
      getTitle(title, 630);
      getLabel(text);
    } else if (this.pie) {
      this.title = "PROPERTY TYPES (PIE CHART)";
      image(pieChart, 1210, 25);
      getTitle(title, 630);
      getLabel(text);
    }
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }
    if (searchBar) {
      this.ui.setType();
      this.ui.draw();
      if (ui.willDraw()) {
        int queries = ui.getQueries();
        String[] input = ui.getInput();
        if (this.avgPrice) {
          if (queries == 3) {
            g = new AvgPriceBar(DATA, input[0], input[1], input[2], b);
          }
          if (queries == 2)
            g = new AvgPriceBar(DATA, input[0], input[1], b);
          if (queries == 1)
            g= new AvgPriceBar(DATA, input[0], b);
          g.draw();
        } else if (this.propType) {
          if (queries == 3) {
            g = new PropertyTypeBar(DATA, input[0], input[1], input[2], b);
          }
          if (queries == 2)
            g = new PropertyTypeBar(DATA, input[0], input[1], b);
          if (queries == 1)
            g= new PropertyTypeBar(DATA, input[0], b);
          g.draw();
        } else if (this.pie) {
          if (queries == 3) {
            g = new PieCharts(DATA, input[0], input[1], input[2]);
          }
          if (queries == 2)
            g = new PieCharts(DATA, input[0], input[1]);
          if (queries == 1)
            g= new PieCharts(DATA, input[0]);
          g.draw();
        }
      }
    }
  }
}
//Author - Sean Candon
//UserInput class draws search bar, keeps a record of
//each key pressed by user, and displays it to the window.
//Also contains method getQueries which returns user input.

class UserInput {

  int x, y, width, height;
  ArrayList l;
  boolean draw = false;
  XYChart xy;
  boolean type = false;
  int queries=0;
  String[] inputAr;
  boolean canType = false;
  String text;

  UserInput(int x, int y, int width, int height, XYChart xy) {

    this.x = x; 
    this.y = y; 
    this.width = width;
    this.height = height;
    l = new ArrayList();
    this.xy = xy;
  }

  public void draw() {

    fill(255);
    stroke(0);
    rect(x, y, width, height);
    if (mouseMoved() && !canType) {
      fill(150);
      textSize(15);
      text("Search", x+8, y+20);
      textSize(30);
    }
    if (type) {
      fill(0);
      textSize(15);
      for (int i=0; i<l.size(); i++) {
        text((char)l.get(i), x + (i*8), y + 20);
      }
    }
    if (draw==true) {
      char[] ar = new char[l.size()];
      for (int i=0; i<l.size(); i++) {
        ar[i] = (char)l.get(i);
      }
      String input = new String(ar);
      inputAr = input.split(", ");

      if (inputAr.length==3)
        queries = 3;
      else if (inputAr.length==2)
        queries = 2;
      else if (inputAr.length==1)
        queries = 1;
      else
        queries = 0;
    }
  }

  public void takeKeys(char key) {
    if (this.canType) {
      if (key == ENTER || key == RETURN)
        draw = true;
      else if (key == BACKSPACE && l.size()>=1) {
        l.remove(l.size() -1);
        draw = false;
      } else {
        l.add(key);
        draw = false;
      }
    }
  }

  public void setType() {
    type = true;
  }

  public int getQueries() {
    return this.queries;
  }
  
  public void clearList(){
    this.l.clear();
  }
  
  public void setDraw(boolean b){
    this.draw = b;
  }

  public String[] getInput() {
    return this.inputAr;
  }

  public boolean willDraw() {
    return this.draw;
  }

  public boolean mouseMoved()
  {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }

  public boolean mousePressed() {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }
}
//Author - Arthur Sasunts
public boolean down = false;

class Widget {
  int x, y, width, height;
  String label; 
  int event;
  int widgetColor, labelColor;
  PFont widgetFont;

  Widget(int x, int y, int width, int height, String label, 
    int widgetColor, int labelColor, PFont widgetFont, int event, int textSize) {
    this.x=x; 
    this.y=y; 
    this.width = width; 
    this.height= height;
    this.label=label; 
    this.event=event; 
    this.widgetColor=widgetColor; 
    this.widgetFont=widgetFont;
    this.labelColor=labelColor;
    textSize(textSize);
  }
  public void draw() {
    if (mouseMoved())
    {
      stroke(255);
    } else
    {
      noStroke();
    }
    fill(widgetColor);
    rect(x, y, width, height);
    fill(labelColor);
    text(label, x+10, y+height-10);
  }
  public int getEvent(int mX, int mY) {
    if (mX>x && mX < x+width && mY >y && mY <y+height) {
      return event;
    }
    return EVENT_NULL;
  }

  public boolean mouseMoved()
  {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }

  public void pullDownMenu(Widget w1, Widget w2, Widget w3, Widget w4, Widget w5, Widget w6)
  {  
    if (down == true)
    {
      home.addWidget(w1);
      home.addWidget(w2);
      home.addWidget(w3);
      home.addWidget(w4);
      home.addWidget(w5);
      home.addWidget(w6);
    }

    if (down == false)
    {
      home.removeWidget(w1);
      home.removeWidget(w2);
      home.removeWidget(w3);
      home.removeWidget(w4);
      home.removeWidget(w5);
      home.removeWidget(w6);
    }
  }
}
//Author- Sin\u00e9ad Galbraith


//This method will take the array list of all the properties and return an array 
//of only the prices which can be used for the sorting methods. 
public int[] getPriceList(ArrayList<Data> properties)
{
  int [] priceList = new int[properties.size()];
  for (int i = 0; i < properties.size(); i++)
  {
    Data d1 = properties.get(i);
    priceList[i] = d1.getPrice();
  }
  return priceList;
}

//This method will take in the priceList from getPriceList() and using the sort() function, 
//will sort the prices from highest to lowest.
public int[] sortLowToHigh(int[] priceList)
{
  Arrays.sort(priceList); 
  return priceList;
}

//This method calls the getPriceList method and uses the array returned to get the low-to-high sorted array.
//Then, using nested for loops and a new array of the same length, populate the new array with the sorted array, but in reverse order.
public int[] sortHighToLow(int[] priceList)
{
  int[] prices = new int[priceList.length];
  Arrays.sort(priceList);
  int k=0;
  for (int i = priceList.length - 1; i >= 0; i--)
  {
    //for(int j = 0; j < prices.length; j++)
    //{
    //  prices[j] = priceList[i];
    //  //println("     " + prices[j]);
    //}
    if (k<prices.length) {
      prices[k] = priceList[i];
      //println(prices[j]);
      k++;
    }
  }
  //println(Arrays.toString(prices));
  return prices;
}

public Data[] lowest(ArrayList<Data> d1, int n) {

  int[] prices = getPriceList(d1);
  int[] pricesSorted = sortLowToHigh(prices);
  Data[] d2 = new Data[n];
  if(n<prices.length){
    int count = 0;
    for (int i=0; i<n; i++) {
      for (int j=0; j<d1.size(); j++) {
        int price = pricesSorted[i];
        if ((price == d1.get(j).getPrice()) && (count<n)) {
          d2[count] = d1.get(j);
          //println(a[count]);
          count++;
        }
      }
    }
    return d2;
  }
  return null;
}

public Data[] highest(ArrayList<Data> d1, int n) {

  int[] prices = getPriceList(d1);
  int[] pricesSorted = sortHighToLow(prices);
  Data[] d2 = new Data[n];
  if(n<prices.length){
    int count = 0;
    for (int i=0; i<n; i++) {
      for (int j=0; j<d1.size(); j++) {
        int price = pricesSorted[i];
        if (price == d1.get(j).getPrice() && count<n) {
          d2[count] = d1.get(j);
          count++;
        }
      }
    }
    return d2;
  }
  return null;
  
}

public ArrayList<Data> getCounties(String c) {
  ArrayList<Data> cA = new ArrayList<Data>();
  for (int i=0; i<DATA.size(); i++) {
    String county = DATA.get(i).getCounty();
    if (c.equals(county)) {
      cA.add(DATA.get(i));
    }
  }
  return cA;
}

public ArrayList<Data> getDistricts(String c, String d) {
  ArrayList<Data> abc = new ArrayList<Data>();
  for (int i=0; i<DATA.size(); i++) {
    String county = DATA.get(i).getCounty();
    String district = DATA.get(i).district;
    if (c.equals(county) && d.equals(district)) {
      abc.add(DATA.get(i));
    }
  }
  return abc;
}

public ArrayList<Data> getTowns(String c, String d, String t) {
  ArrayList<Data> a = new ArrayList<Data>();
  for (int i=0; i<DATA.size(); i++) {
    String county = DATA.get(i).getCounty();
    String district = DATA.get(i).district;
    String town = DATA.get(i).town;
    if (c.equals(county) && d.equals(district) && t.equals(town)) {
      a.add(DATA.get(i));
    }
  }
  return a;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
