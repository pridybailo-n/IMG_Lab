package filters;

import processor.FilterProcessor;
import models.MyRGB;

public class Binarization extends FilterProcessor {
    private int max;

    public Binarization(int max) {
        this.max = max;
    }

    @Override
    protected int simpleOperation(int rgb) {
        MyRGB myRGB = new MyRGB(rgb);
        if(myRGB.getBrightness() < max) return 0;
        else return new MyRGB(255, 255, 255).toInteger();
    }
}
