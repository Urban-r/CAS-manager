public class Concern extends StudentProfile {
    public Concern(String name, int event, boolean feedback, String progress) {
        super (name, event, feedback, progress);
    }
    @Override
    public boolean attention() {
        return true;
    }

    @Override
    public String getProgress() {
        return "Concern";
    }
}
