import java.util.*;
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < myWords.length; ++i){
            ret+=" "+myWords[i];
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if(other.length() != length()) return false;
        for(int i = 0; i < other.length(); ++i){
            if(!other.wordAt(i).equals(myWords[i])) return false;
        }
        return true;
    }

    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for(int i = 0; i < myWords.length-1; ++i){
            out.myWords[i] = myWords[i+1];
        }
        out.myWords[out.length()-1] = word;
        return out;
    }

    public int hashCode(){
        return toString().hashCode();
    }

}