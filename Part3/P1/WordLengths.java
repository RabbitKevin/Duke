public class WordLengths{
    public static void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            int index = wordLength(word);
            if(index >= counts.length) index = counts.length-1;
            counts[index]++;
        }
    }
    private static int wordLength(String word){
        int left = 0;
        int right = word.length() - 1;
        left = Character.isLetter(word.charAt(left))?left:left+1;
        right = Character.isLetter(word.charAt(right))?right:right-1;
        int length = right - left + 1;
        return length > 0?length:0;
    }
    public static int indexOfMax(int[] values){
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i = 0; i < values.length; ++i){
            if(values[i] > max){
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    public static void main (String[] args){
        int[] values = new int[31];
        FileResource resource = new FileResource("text/manywords.txt");
        countWordLengths(resource, values);
        System.out.println("Largest index "+indexOfMax(values));
        for(int x : values){
            System.out.println(x);
        }
    }
}