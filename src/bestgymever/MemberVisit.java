package bestgymever;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MemberVisit {

    Path outpath = Paths.get("src/bestgymever/PT_fil.txt");
    //System.out.println("memberVisit anropas");

    public void writeToFile() {
        System.out.println("writeToFile anropas");
    }

    //gör konstruktor med path igen
//try with resources igen + bufferedWriter med enkel felhantering
    //börja denna gång med TDD först
    //Använd Stringbuilder enligt Sigrun.
    //I utfilen räcker det att skriva vem (namn) som besökte gymmet och gärna tiden för när detta skedde. Använd LocalDateTime.
//fråga efter namn eller personnummer, spara i String
//if (eget anrop till checkMemberStatus, kontrollera om får returvärde 1)
//sedan returnerar den personens info från checkMember
//spara i variabel
//skriv ut Personobjektet + time till den andra filen
    //om returvärdet är något annat än 1 så skriv att det inte var giltig medlem och besöket kunde ej registreras

}
