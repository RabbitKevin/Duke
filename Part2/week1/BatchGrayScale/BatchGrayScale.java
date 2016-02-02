import edu.duke.*;
import java.io.*;
public class BatchGrayScale{
    public static void saveGrayCopy() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            image = new GrayScaleConverter().makeGray(image);
            image.setFileName(newName);
            image.save();
        }
    }
    public static void main(String[] args){
        saveGrayCopy();
    }
}