//Restructured by Sean Candon:
// - Broke the class into subclasses XYScreen (which displays line graphs),
//   TextScreen (which as of now does nothing except draws widgets
//   and search bar), and BarScreen (which draws bar charts), each of which
//   extend from Screen super class.

// Extended by Sinéad Galbraith
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
  
  void clearSearchBar(){
    if(searchBar){
      this.ui.clearList();
      this.ui.setDraw(false);
    }
  }

  void setHighest() {
    highest = true;
    lowest = false;
  }
  void setLowest() {
    lowest = true;
    highest = false;
  }
  void getLabel(String text)
  {
    fill(200, 200, 200);
    textSize(20);
    text("Search Criteria:", x, y+60);
    text(text, x, y+80);
  }
  
  void getTitle(String title, int x)
  {
    fill(255);
    textSize(40);
    text(title, x, 60);
    textSize(30);
  }

  void setUI (UserInput ui, int x, int y, int width, int height) {
    this.ui = ui;
    this.x = x;
    this.y = y;
    this.ui = new UserInput(x, y, width, height, xy);
    searchBar = true;
  }

  void canTakeKeys() {
    if (searchBar && ui.mousePressed())
      ui.canType = true;
  }

  void takeKeys(char key) {
    //if(ui.canType())
    ui.takeKeys(key);
  }

  void draw() {
    for (int i = 0; i<widgetList.size(); i++) {
      Widget aWidget = (Widget)widgetList.get(i);
      aWidget.draw();
    }
  }

  void addWidget(Widget w) {
    this.widgetList.add(w);
  }

  void removeWidget(Widget w) {
    this.widgetList.remove(w);
  }

  int getEvent()
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

  void moveUp() {
    textY+=50;
  }
  void moveDown() {
    textY-=50;
  }
}

class HomeScreen extends Screen {

  HomeScreen(PImage image) {
    this.image = image;
  }

  void draw() {
    fill(255);
    textSize(120);
    text("UK LAND REGISTERY", 125, 150);
    textSize(30);
    
   image(map, width/1.7, height/6);
    
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
  void draw() {
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

  void draw() {
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

  void draw() {
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