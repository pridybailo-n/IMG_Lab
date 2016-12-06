package processor;

import models.MyImage;

import java.util.ArrayList;

public class ImageBuilder {
    ArrayList<Processor> processors = new ArrayList<>();
    MyImage image;

    public ImageBuilder (MyImage image) {
        this.image = image.clone();
    }

    public ImageBuilder add(Processor processor) {
        processors.add(processor);
        return this;
    }

    public MyImage build() {
        for (Processor processor: processors) {
            processor.process(image);
        }
        return image;
    }
}
