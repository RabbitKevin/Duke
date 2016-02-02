
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String lower = "abcdefghijklmnopqrstuvwxyz";
    public String encrypt(String input, int keyA, int keyB){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < input.length(); ++i){
            char tmp = input.charAt(i);
            if(Character.isAlphabetic(tmp)){
               int index = 0;
                boolean lowerCase = false;
                if(tmp >= 'a' && tmp <= 'z'){
                    index = tmp-'a';
                    lowerCase = true;
                }
                else index = tmp-'A';
                if((i & 1) == 0) index+=keyA;
                else index+=keyB;
                index = index % 26;
                tmp = lowerCase?lower.charAt(index):upper.charAt(index);
            }
            builder.append(tmp); 
        }
        return builder.toString();
    }
    
    public static void main(String[] args){
        CaesarCipher x = new CaesarCipher();
        System.out.println(x.encrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.", 12, 2));
    }
}
