package filters;

import processor.Processor;
import models.MyImage;
import models.MyRGB;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Equalization implements Processor {
    Double[] histogramRed = new Double[256];
    Double[] histogramGreen = new Double[256];
    Double[] histogramBlue = new Double[256];

    public Equalization() {
        Arrays.fill(histogramRed, 0.0);
        Arrays.fill(histogramGreen, 0.0);
        Arrays.fill(histogramBlue, 0.0);
    }

    @Override
    public void process(MyImage destinationImage) {
        BufferedImage bufferedImage = destinationImage.getBufferedImage();
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                histogramRed[new MyRGB(bufferedImage.getRGB(i, j)).getRed()]++;
                histogramGreen[new MyRGB(bufferedImage.getRGB(i, j)).getGreen()]++;
                histogramBlue[new MyRGB(bufferedImage.getRGB(i, j)).getBlue()]++;
            }
        }
        for (int i = 0; i < histogramRed.length; i++) {
            histogramRed[i] /= (bufferedImage.getHeight() * bufferedImage.getWidth());
            histogramGreen[i] /= (bufferedImage.getHeight() * bufferedImage.getWidth());
            histogramBlue[i] /= (bufferedImage.getHeight() * bufferedImage.getWidth());
        }
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Double r = 0.0;
                Double g = 0.0;
                Double b = 0.0;
                Integer brightnessRed = new MyRGB(bufferedImage.getRGB(i, j)).getRed();
                Integer brightnessGreen = new MyRGB(bufferedImage.getRGB(i, j)).getGreen();
                Integer brightnessBlue = new MyRGB(bufferedImage.getRGB(i, j)).getBlue();
                for (int k = 0; k < brightnessRed; k++)
                    r+= histogramRed[k];
                for (int k = 0; k < brightnessGreen; k++)
                    g+= histogramGreen[k];
                for (int k = 0; k < brightnessBlue; k++)
                    b+= histogramBlue[k];
                bufferedImage.setRGB(i, j, new MyRGB((int)(r * 255), (int)(g * 255), (int)(b * 255)).toInteger());
            }
        }
    }


}
