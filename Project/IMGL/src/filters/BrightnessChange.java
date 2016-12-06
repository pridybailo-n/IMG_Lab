package filters;

import processor.FilterProcessor;
import models.MyRGB;

public class BrightnessChange extends FilterProcessor {
    double a;

    public BrightnessChange(double a) {
        this.a = a;
    }

    @Override
    protected int simpleOperation(int rgb) {
        MyRGB myRGB = new MyRGB(rgb);
        myRGB.setRed((int)(myRGB.getRed() + a));
        myRGB.setGreen((int)(myRGB.getGreen() + a));
        myRGB.setBlue((int)(myRGB.getBlue() + a));
        return myRGB.toInteger();
    }
}
