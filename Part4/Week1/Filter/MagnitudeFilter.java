public class MagnitudeFilter implements Filter
{
    private double max;
    private double min;
    public MagnitudeFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double magnitude = qe.getMagnitude();
        return magnitude >= min && magnitude <= max;
    }

    public String getName(){
        return "Magnitude Filter";
    }
}