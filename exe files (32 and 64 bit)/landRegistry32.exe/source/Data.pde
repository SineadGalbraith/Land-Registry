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
  float getMonth() {

    String[] ar = dateOfSale.split("/");
    return Integer.parseInt(ar[1]);
  }

  //returns year of dat object as float
  float getYear() {
    String[] ar = dateOfSale.split("/");
    String[] ar2 = ar[2].split(" ");
    return Float.parseFloat(ar2[0]);
  }

  int getPrice() {
    return Integer.parseInt(this.price);
  }
  
// getDateOfSale and getPropType added by Sinéad Galbraith
  String getDateOfSale()
  {
    return this.dateOfSale;
  }
  
  String getPropType()
  {
    return this.propertyType;
  }

  String getCounty() {
    return this.county;
  }

  String toString() {
    return this.s;
  }
}