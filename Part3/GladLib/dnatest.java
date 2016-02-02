public class dnatest{
    public static void main(String[] args){
        condon test = new condon();
        test.test();
        System.out.println("Total words :"+test.mapSize());
        test.printCodonCounts(7,7);
    }
}