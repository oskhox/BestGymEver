package bestgymever;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberVisit {
    Path outPath = Paths.get("src/bestgymever/PT_fil.txt");

    public Path getOutPath() {
        return outPath;
    }

    public void addCheckIn(Path outPath, String name, String personID) {
        //anger som parameter att ska appenda i filen istället för att skriva över
        try (BufferedWriter bw = Files.newBufferedWriter(outPath, StandardOpenOption.APPEND)) {
            //formatterar först nuvarande datum och klockslag
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String dateToFile = now.format(formatter);

            //skriver sedan till fil
            String output = "Medlemmen " + name + " med personnummer " + personID + " checkade in " + dateToFile + ".\n";
            bw.write(output);
            System.out.println("Medlemmens besök är registrerat i PT-filen.");

        } catch (IOException e) {
            System.out.println("Det blev något fel vid utskriften till fil.");
            e.printStackTrace();
        }
    }
}