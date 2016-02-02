public class WordPlay{
   public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
   }
   public String replaceVowels(String phrase, char ch){
        StringBuilder builder = new StringBuilder(phrase);
        for(int i = 0; i < builder.length(); ++i){
            if(isVowel(builder.charAt(i))) builder.setCharAt(i, ch);
        }
        return builder.toString();
   }
   public String emphasize(String phrase, char ch){
        char[] symbol = {'*', '+'};
        StringBuilder builder = new StringBuilder(phrase);
        for(int i = 0; i < builder.length(); ++i){
            if(Character.toLowerCase(builder.charAt(i)) == ch) builder.setCharAt(i, symbol[(i & 1)]);
        }
        return builder.toString();
   }
   public static void main(String[] args){
        WordPlay x = new WordPlay();
        System.out.println(x.replaceVowels("aeiofuqr", 'x'));
        System.out.println(x.emphasize("xxaxxabbxx", 'x'));
   }
}
