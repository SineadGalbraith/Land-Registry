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

  void draw() {

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

  void takeKeys(char key) {
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

  void setType() {
    type = true;
  }

  int getQueries() {
    return this.queries;
  }
  
  void clearList(){
    this.l.clear();
  }
  
  void setDraw(boolean b){
    this.draw = b;
  }

  String[] getInput() {
    return this.inputAr;
  }

  boolean willDraw() {
    return this.draw;
  }

  boolean mouseMoved()
  {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }

  boolean mousePressed() {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }
}