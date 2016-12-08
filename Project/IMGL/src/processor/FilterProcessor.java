package processor;

/**
 * Created by pridybailo-n on 02.12.2016.
 */

import models.MyImage;

import java.awt.image.BufferedImage;

public abstract class FilterProcessor implements Processor {
    protected abstract int simpleOperation(int rgb);

    @Override
    public void process(MyImage image) {
        BufferedImage bufferedImage = image.getBufferedImage();
        for(int i = 0; i < bufferedImage.getWidth(); i++)
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                bufferedImage.setRGB(i, j, simpleOperation(bufferedImage.getRGB(i, j)));
            }
    }
}
