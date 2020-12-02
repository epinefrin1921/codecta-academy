package ba.codecta.academy.assignmentone.characters;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.ShapeRasterizer;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.PictureWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class DisneyCharacter {
    static final Logger logger = LogManager.getLogger(DisneyCharacter.class.getName());

    protected String imageFilename=null;
    public abstract String getName();
    public String greetings(){
        return "Hi!";
    }

    public String picture()
    {
        try{
        if(imageFilename == null) {
            logger.warn("Nema slike!");
            return "Sorry, no picture available";
        }
        ClassLoader classLoader = DisneyCharacter.class.getClassLoader();

        InputStream resourceAsStream = classLoader.getResourceAsStream(imageFilename);
        try {
            BufferedImage image = ImageIO.read(resourceAsStream);
            TextWidget widget = new PictureWidget(new TextBoxSize(80, 30),
                    image, new ShapeRasterizer());
            Raster raster = new ExtensibleCharacterRaster();

            widget.render(new RasterContext(raster));
            return raster.toString();

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("ERROR IN CONVERTING PICTURE TO ASCII");
            return "Error in converting";
        }}
        catch (Exception e){
            return e.getMessage();
        }
    }

}
