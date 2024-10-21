package bestgymever;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class MemberVisitTest {

    String mockName = "Nahema Ninsson";
    String mockPersonID = "7805211234";
    Path realPath = Paths.get("src/bestgymever/PT_fil.txt");
    Path testPath = Paths.get("test/bestgymever/PT_fil.txt");

    //skapar upp en instans av produktionsklassen
    MemberVisit m1 = new MemberVisit();

    //testar att path är korrekt genom skapa upp motsvarande path och jämföra
    @Test
    final void testRealPath() {
        //skapar upp en förväntad path för produktionsklassen
        assertEquals(realPath, m1.getOutPath());
    }

    //testar att utskriften är korrekt genom att skriva ut, läsa in och jämföra
    @Test
    final void testOutputString() {
        //skapar en tom sträng som ska innehålla det som läses in
        String lineInFile = "";

        //skriver till fil (anropar void-metod)
        m1.addCheckIn(testPath, mockName, mockPersonID);
        //läser den nyss skriva raden med en BufferedReader
        try (BufferedReader br = Files.newBufferedReader(testPath)) {
            lineInFile = br.readLine();
        } catch (IOException e) {
            System.out.println("Fel vid läsning av fil.");
            e.printStackTrace();
        }

        //hämtar nuvarande tid, formaterar, och lagrar i sträng
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedDateTime = now.format(formatter);

        //skapar förväntad utdata och kontrollerar gentemot förväntad utdata
        String expectedOutput = "Medlemmen " + mockName + " med personnummer " + mockPersonID + " checkade in " + expectedDateTime + ".";
        assertEquals(expectedOutput, lineInFile);

        //rensar testfilen när testet är klart, inför nästa test
        try {
            Files.writeString(testPath, "");
        } catch (IOException e) {
            System.out.println("Fel vid rensning av testfil.");
            e.printStackTrace();
        }
    }
}