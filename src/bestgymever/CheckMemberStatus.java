package bestgymever;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckMemberStatus {
    List<Person> allPersons; //den färdiga listan som kan användas till sökning sen

    //konstruktor som skapar upp path och lagrar resultatet av readFile i instansvariabeln allPersons
    CheckMemberStatus() {
        Path inpath = Paths.get("src/bestgymever/data_inlamningsuppg2.txt");
        this.allPersons = readFile(inpath);
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
            if (person.getPersonID().toLowerCase().contains(searchTerm) || person.getName().toLowerCase().contains(searchTerm)) {
                //anropar datummetoden och skickar in person.date
                boolean hasValidMembershipDate = compareDates(person.getDate());
                //om medlemskapet är giltigt
                if (hasValidMembershipDate) {
                    return 1;
                    //om medlemskapet är ogiltigt
                } else {
                    return 2;
                }
            }
        }
        //om ingen matchning alls i sökningen så returnerar alltid 3
        return 3;
    }

    //jämför datum och skickar true om datumet är inom ett år från idag, false om mer än ett år
    public boolean compareDates(String personDate) {
        //skapar en egen formaterare
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //skapar en LocalDate från datumet i person-objektet
        LocalDate personLocalDate = LocalDate.parse(personDate, formatter);
        //läser in dagens datum
        LocalDate todayDate = LocalDate.now();
        //läser in datumet för att år sedan
        LocalDate oneYearAgo = todayDate.minusYears(1);
        //kontrollerar om personens datum är inom ett år sedan
        return personLocalDate.isAfter(oneYearAgo);
    }
}