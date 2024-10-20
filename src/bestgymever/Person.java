package bestgymever;

public class Person {

    private final String personID;
    private final String name;
    private final String date;

    public Person(String personID, String name , String date) {
        this.personID = personID;
        this.name = name;
        this.date = date;
    }

    public String getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}