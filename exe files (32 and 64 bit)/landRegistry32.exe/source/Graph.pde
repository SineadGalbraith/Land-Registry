//ChangeInTime - Sean Candon
//AvgPrice - Aaron Duggan

class Graph { 

  XYChart xy;

  void draw() {
    xy.draw(width/30, height/8, width/1.2, height/1.5);
  }

  //function to set XY graph
  void setXY(XYChart xy, float[] list) {
    xy.setData(YEARS_FULL, list); 
    xy.setLineColour(255);
    xy.setLineWidth(1);
    xy.setMinY(0);
    xy.setYFormat("£###,###");
    xy.setXFormat("0000");
    xy.showXAxis(true);
    xy.showYAxis(true);
  }

  //funtion that returns average price of a list of properties
  int avgPrice(ArrayList a) {

    int d = a.size();
    int sum=0;
    for (int i=0; i<d; i++) {
      int p = Integer.parseInt((String)a.get(i));
      sum += p;
    }
    return sum/d;
  }

  //function that returns array of average prices 
  float[] avgPrices(ArrayList list) {

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
  ArrayList alphabeticalOrder(ArrayList a, int region)
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