public class DepthFilter implements Filter
{
    private double max;
    private double min;
    public DepthFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double magnitude = qe.getDepth();
        return magnitude >= max && magnitude <= min;
    }
    public String getName(){
        return "Depth Filter";
    }
}