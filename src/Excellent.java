public class Excellent extends StudentProfile {
    public Excellent(String name, int event, boolean feedback, String progress) {
        super (name, event, feedback, progress);
    }
    @Override
    public String getProgress() {
        return "Excellent";
    }
}
