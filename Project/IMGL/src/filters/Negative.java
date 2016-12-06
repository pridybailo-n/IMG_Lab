package filters;

import processor.FilterProcessor;
import models.MyRGB;

public class Negative extends FilterProcessor {

    @Override
    protected int simpleOperation(int rgb) {
        MyRGB myRGB = new MyRGB(rgb);
        myRGB.setRed(255 - myRGB.getRed());
        myRGB.setGreen(255 - myRGB.getGreen());
        myRGB.setBlue(255 - myRGB.getBlue());
        return myRGB.toInteger();
    }
}
