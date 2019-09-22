public class PieCharts extends AvgPriceBar {

  int detached;
  int semiDetached;
  int terraced;
  int flatsMaisonettes;
  int other;


  final float diameter = 300;

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


  void setup()
  {
    this.angles = angles(this.properties);
    println(angles[0] + " " + angles[1] + " " + angles[2] + " " + angles[0] + " " + angles[0]);
    //noLoop();
    noStroke();
  }

  void draw()
  {
    pieChart(this.diameter, this.angles);
    drawText();
  }



  float[] angles(ArrayList categorisedProperties)
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

  void pieChart(float diameter, float[] data) {
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
      arc(width/2, height/2, diameter, diameter, lastAngle, lastAngle+radians(angles[i]));
      lastAngle += radians(angles[i]);
    }
  }

  void drawText()
  {
    int ypos = width/9;
    fill(255);
    text("Detached = Red", 150, ypos);
    text("Semi-Detached = Green", 150, ypos+30);
    text("Terraced = Blue", 150, ypos+60);
    text("Flats/Maisonettes = Yellow", 150, ypos+90);
    text("Other = Purple", 150, ypos+120);
  }
}