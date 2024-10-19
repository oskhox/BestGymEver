package bestgymever;

public class Person {

    private String personID;
    private String name;
    private String date;

    public Person(String personID, String name , String date) {
        this.personID = personID;
        this.name = name;
        this.date = date;
    }

    public String getPersonID() {
        return personID;
    }

    //rensa setters?
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}