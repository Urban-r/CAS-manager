public class OnTrack extends StudentProfile{
    public OnTrack(String name, int event, boolean feedback, String progress) {
        super (name, event, feedback, progress);
    }
    @Override
    public String getProgress() {
        return "On-track";
    }
}
