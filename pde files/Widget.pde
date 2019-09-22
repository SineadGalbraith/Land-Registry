//Author - Arthur Sasunts
public boolean down = false;

class Widget {
  int x, y, width, height;
  String label; 
  int event;
  color widgetColor, labelColor;
  PFont widgetFont;

  Widget(int x, int y, int width, int height, String label, 
    color widgetColor, color labelColor, PFont widgetFont, int event, int textSize) {
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
  void draw() {
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
  int getEvent(int mX, int mY) {
    if (mX>x && mX < x+width && mY >y && mY <y+height) {
      return event;
    }
    return EVENT_NULL;
  }

  boolean mouseMoved()
  {
    if (mouseX>x && mouseX < x+width && mouseY >y && mouseY <y+height)
      return true;
    else
      return false;
  }

  void pullDownMenu(Widget w1, Widget w2, Widget w3, Widget w4, Widget w5, Widget w6)
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