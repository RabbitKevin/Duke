public class MinutesFilter implements Filter{
    int min;
    int max;
    public MinutesFilter(int min, int max){
        this.min = min;
        this.max = max;
    }
    @Override
    public boolean satisfies(String id) {
       int time = MovieDatabase.getMinutes(id);
       return (time >= min) && (time <= max);
    }
}