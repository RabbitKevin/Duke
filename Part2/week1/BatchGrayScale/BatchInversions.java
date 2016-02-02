import edu.duke.*;
import java.io.*;
public class BatchInversions{
    public static ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //set pixel's red to average
            pixel.setRed(255 - inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(255 - inPixel.getBlue());
            //set pixel's blue to average
            pixel.setBlue(255 - inPixel.getGreen());
        }
        //outImage is your answer
        return outImage;
    }
    public static void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f.toString());
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            image = makeInversion(image);
            image.setFileName(newName);
            image.save();
        }
    }
    public static void main(String[] args){
        selectAndConvert();
    }
}