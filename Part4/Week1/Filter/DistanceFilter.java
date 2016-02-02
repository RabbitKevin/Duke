public class DistanceFilter implements Filter
{
    private Location loc;
    private float max;
    public DistanceFilter(Location loc, float max) {
        this.loc = loc;
        this.max = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < max;
    }

    public String getName(){
        return "Distance Filter";
    }
}