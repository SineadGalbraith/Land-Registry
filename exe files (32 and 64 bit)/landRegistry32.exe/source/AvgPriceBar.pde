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

  void setup()
  {

    averagePricePerRegion(properties);
    this.properties = alphabeticalOrder(this.properties, region);
    String[] sa = createListOfRegions(properties);
    barChart.setData(averagePricesForY);
    barChart.showValueAxis(true);
    barChart.setValueFormat("€#");
    barChart.showCategoryAxis(false);
    barChart.setBarLabels(sa);
    barChart.setBarColour(color(0, 112, 114));
  }


  void draw()
  {
    barChart.draw(width/30, height/8, width/1.2, height/1.5);
    readXAxis();
  }

  //Fills averagePricesForY[] with the average values of all the places. 
  //ArrayList propertyList parameter is the ArrayList of all the inputted Data objects

  void averagePricePerRegion(ArrayList propertyList)
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
  String[] createListOfRegions(ArrayList propertyList)
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



  ArrayList categorisedPropertyList(ArrayList propertyList)
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
  void readXAxis()
  {
    if (30 + yAxisWidth < mouseX && mouseX < width && 30 + yAxisWidth < mouseY && mouseY < height-90) {
      //line(mouseX, 0, mouseX, height);
      int compPath = int(map(mouseX, 30+yAxisWidth, width, 0, averagePricesForY.length));
      compPath = abs(compPath);
      fill(255);
      text(regionsForX[compPath], width/2, height/5);
      text(averagePricesForY[compPath], width/2, height/5+15);
    }
  }
}