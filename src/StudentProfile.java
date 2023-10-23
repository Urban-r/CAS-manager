public class StudentProfile {
    private String name;
    private int event;
    private boolean feedback;
    private String progress;
    public StudentProfile(String name, int event, boolean feedback, String progress) {
        this.name = name;
        this.event = event;
        this.feedback = feedback;
        this.progress = progress;

    }

    public String getName() {
        return name;
    }
    public int getEvent() {
        return event;
    }
    public boolean getFeedback() {
        return feedback;
    }
    public String getProgress() {
        return "";
    }
    public boolean attention() {
        return false;
    }
}

