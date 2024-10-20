package bestgymever;

public class GymProgram {

    public GymProgram() {
        UserInteraction ui = new UserInteraction();
        ui.ask();
    }

    public static void main(String[] args) {
        new GymProgram();
    }
}