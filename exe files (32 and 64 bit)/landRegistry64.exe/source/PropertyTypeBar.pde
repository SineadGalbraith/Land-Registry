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

  void setup()
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

  void draw()
  {
    barChart.draw(width/7, height/8, width/1.2, height/1.5);
  }

  float[] numberOfEachType(ArrayList categorisedProperties)
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