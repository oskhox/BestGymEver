package bestgymever;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckMemberStatus {
    Path inpath = Paths.get("src/bestgymever/data_inlamningsuppg2.txt");;

    List<Person> allPersons;
    private Person matchedMember; //håller matchad person för senare utskrift

    //konstruktor som lagrar resultatet av readFile i instansvariabeln allPersons
    CheckMemberStatus() {
        this.allPersons = readFile(getInpath());
    }

    public Path getInpath() {
        return inpath;
    }

    //hämta matchad person som ska skrivas ut
    public Person getMatchedMember() {
        return matchedMember;
    }

    //readFile som sköter inläsningen av filen
    public List<Person> readFile(Path inpath) {
        List<Person> persons = new ArrayList<>(); //lista för att lagra inläsningen

        try (BufferedReader br = Files.newBufferedReader(inpath)) {
            String temp;
            String personIDName;
            String personID;
            String name;
            String date;

            while ((temp = br.readLine()) != null) {
                //har läst in första raden, delar och lagrar
                personIDName = temp;
                personID = personIDName.substring(0, 10);
                name = personIDName.substring(12);
                //läser in andra raden för datum
                date = br.readLine();
                //skapar en person-instans för varje körning och lägg till i listan
                Person person = new Person(personID, name, date);
                persons.add(person);
            }

        } catch (IOException e) {
            System.out.println("Det blev något fel vid inläsningen av filen.");
            e.printStackTrace();
        }
        return persons;
    }

    //söker igenom listan efter matchning på personnummer eller namn
    public int search(List<Person> fullList, String searchTerm) {
        for (Person person : fullList) {
            if (person.getPersonID().toLowerCase().contains(searchTerm) && (!searchTerm.trim().isEmpty())
                    || person.getName().toLowerCase().contains(searchTerm) && (!searchTerm.trim().isEmpty())) {
                //anropar datummetoden och skickar in person.date
                boolean hasValidMembershipDate = compareDates(person.getDate());
                //om medlemskapet är giltigt
                if (hasValidMembershipDate) {
                    this.matchedMember = person;
                    return 1;
                    //om medlemskapet är ogiltigt
                } else {
                    return 2;
                }
            }
        }
        //om söktermen är tom
        if (searchTerm.trim().isEmpty()) {
            return 3;
        }
        //om ingen matchning alls i sökningen
        return 4;
    }

    //jämför datum och skickar true om datumet är inom ett år från idag, false om mer än ett år
    public boolean compareDates(String personDate) {
        //skapar en egen formaterare och LocalDate från personens datum
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate personLocalDate = LocalDate.parse(personDate, formatter);
        //läser in dagens datum samt för ett år sedan
        LocalDate todayDate = LocalDate.now();
        LocalDate oneYearAgo = todayDate.minusYears(1);
        //kontrollerar om personens datum är inom ett år sedan
        return personLocalDate.isAfter(oneYearAgo);
    }
}