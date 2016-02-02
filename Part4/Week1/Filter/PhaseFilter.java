public class PhaseFilter implements Filter
{
    private String type;
    private String phase;
    public PhaseFilter(String type, String phase) {
        this.type = type;
        this.phase = phase;
    }

    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        int index = title.indexOf(phase);
        if(index == -1) return false;
        switch(type){
            case "start":
                return index == 0;
            case "any":
                return true;
            case "end":
                index = title.lastIndexOf(phase);
                return index+phase.length() == title.length();
            default:
                break;
        }
        return false;
    }

    public String getName(){
        return "Phase Filter";
    }
}