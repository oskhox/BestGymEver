package bestgymever;

import java.util.Scanner;

public class UserInteraction {

    public boolean checkYesNoInput(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Du skrev inte in något alls eller bara mellanslag. Pröva igen.");
            return false;
        } else if (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nej")) {
            System.out.println("Ditt val är ogiltigt. Skriv ja eller nej.");
            return false;
        }
        //all tillåten input, alltså "ja" eller "nej" returnerar true
        return true;
    }

    public void ask() {
        try (Scanner input = new Scanner(System.in)) {
            boolean keepAsking = true;
            //KONTROLL AV MEDLEMSKAP
            while (keepAsking) {
                System.out.println("Vill du kontrollera någons medlemskap? Skriv ja eller nej:");
                String firstReply = input.nextLine();
                if (!checkYesNoInput(firstReply)) {
                    continue;
                }
                //om svaret är ja, skapar upp en lista med personobjekt
                if (firstReply.equalsIgnoreCase("ja")) {
                    CheckMemberStatus c1 = new CheckMemberStatus(); //hela listan skapas upp

                    System.out.println("Skriv för- och efternamn eller personnummer:");
                    //lagrar svaret och skicka hela listan samt svaret in i sökmetoden
                    String searchTerm = input.nextLine().toLowerCase();
                    int validationResponse = c1.search(c1.allPersons, searchTerm);

                    //skriver ut beroende vad som returneras
                    switch (validationResponse) {
                        case 1 -> {
                            System.out.println("Kunden är en nuvarande medlem. Årsavgiften är betald det senare året." +
                                    " Incheckningen är registrerad i PT-filen.");
                            MemberVisit m1 = new MemberVisit(); //skapar up MemberVisit-filen och låta konstruktorn göra sitt jobb.
                        }
                        case 2 ->
                                System.out.println("Kunden är en före detta medlem. Årsavgiften betalades för mer än ett år sedan.");
                        case 3 ->
                                System.out.println("Du skrev inte in något alls eller bara mellanslag. Pröva igen..");
                        case 4 ->
                                System.out.println("Personen inte finns i filen och har sålunda aldrig varit medlem och är obehörig.");
                    }
                    //AVSLUTA PROGRAMMET
                } else if (firstReply.equalsIgnoreCase("nej")) {
                    System.out.println("Vill du avsluta programmet, Skriv ja eller nej:");
                    String thirdReply = input.nextLine();
                    if (!checkYesNoInput(thirdReply)) {
                        continue;
                    }
                    if (thirdReply.equalsIgnoreCase("ja")) {
                        System.out.println("Programmet avslutas.");
                        keepAsking = false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ett fel på generell nivå har inträffat.");
            e.printStackTrace();
        }
    }
}