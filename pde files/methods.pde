//Author- Sinéad Galbraith
import java.util.*;

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

ArrayList<Data> getCounties(String c) {
  ArrayList<Data> cA = new ArrayList<Data>();
  for (int i=0; i<DATA.size(); i++) {
    String county = DATA.get(i).getCounty();
    if (c.equals(county)) {
      cA.add(DATA.get(i));
    }
  }
  return cA;
}

ArrayList<Data> getDistricts(String c, String d) {
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

ArrayList<Data> getTowns(String c, String d, String t) {
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