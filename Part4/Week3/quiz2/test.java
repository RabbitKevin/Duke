import edu.duke.*;
import java.util.*;

class test{
    public static void main(String[] args){
        //new MarkovRunnerWithInterface().compareMethod();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel model = new EfficientMarkovModel(2);
        model.setRandom(42);
        model.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
    }
}