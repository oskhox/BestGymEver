package bestgymever;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MemberVisit {

    Path outpath = Paths.get("src/bestgymever/PT_fil.txt");
        //System.out.println("memberVisit anropas");

    public void writeToFile() {
        System.out.println("writeToFile anropas");
    }

    //skrivandet hanteras från memberVisit enbart. Gör TDD.
//memberVisit (void), checkar in kund enbart
//try-catch bufferedWriter med enkel felhantering
//fråga efter namn eller personnummer, spara i String
//if (eget anrop till checkMember, kontrollera om får returvärde 1)
//sedan returnerar den personens info från checkMember
//spara i variabel
//skriv ut Personobjektet + time till den andra filen

}
