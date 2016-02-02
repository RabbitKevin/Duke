class CaesarBreaker{
    public static String decryptTwoKeys(String encrypted){
        String one = halfOfString(encrypted, 0);
        String two = halfOfString(encrypted, 1);
        int keyOne = getKey(one);
        int keyTwo = getKey(two); 
        one = decrypt(one, keyOne);
        two = decrypt(two, keyTwo);
        StringBuilder sb = new StringBuilder();
        int i = 0,j = 0;
        System.out.println(one);
        System.out.println(two);
        while(i < one.length() || j < two.length()){
            if(i < one.length()) sb.append(one.charAt(i++));
            if(j < two.length()) sb.append(two.charAt(j++));
        }
        return sb.toString();
    }
    private static String decrypt(String str, int key){
        key = 26 - key;
        return new CaesarCipher().encrypt(str, key, key);
    }
    private static int getKey(String s){
        int bias = maxIndex(countLetters(s));
        return bias >= 4?bias-4:bias+26-4;
    }
    private static int maxIndex(int[] value){
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i = 0; i < value.length; ++i){
            if(value[i] > max){
                max = value[i];
                index = i;
            }
        }
        return index;
    }
    private static int[] countLetters(String str){
        int[] count = new int[26];
        str = str.toLowerCase();
        for(int i = 0; i < str.length(); ++i){
            if(!Character.isLetter(str.charAt(i))) continue;
            count[(str.charAt(i)-'a')]++;
        }
        return count;
    }
    private static String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < message.length(); i+=2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args){
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        FileResource resource = new FileResource("lotsOfWords.txt");
        String input = resource.asString();
        System.out.println(decryptTwoKeys(message));
    }
}