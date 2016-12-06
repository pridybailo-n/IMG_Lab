package filters;

import processor.FilterProcessor;
import models.MyRGB;

public class Slice extends FilterProcessor {
    double toothSize;

    public Slice(int sawtooth) {
        if(sawtooth > 128) throw new IllegalArgumentException(" sawtooth > 128 ");
        if(sawtooth < 1) sawtooth = 1;
        this.toothSize = 256 / sawtooth;
    }

    @Override
    protected int simpleOperation(int rgb) {
        MyRGB myRGB = new MyRGB(rgb);
        myRGB.setRed((int)((((double)myRGB.getRed() % toothSize)/ toothSize) * 255));
        myRGB.setGreen((int)((((double)myRGB.getGreen() % toothSize)/ toothSize) * 255));
        myRGB.setBlue((int)((((double)myRGB.getBlue() % toothSize)/ toothSize) * 255));
        return myRGB.toInteger();
    }
}
