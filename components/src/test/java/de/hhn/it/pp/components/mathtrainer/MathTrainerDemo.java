package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MathTrainerDemo {
    public static BiKrMathTrainer tr;

    public static void main(String[] args) {
        tr = new BiKrMathTrainer();

        //Nutzername
        tr.setUsername("Peter");
        System.out.println("Nutzername eingegeben: " + tr.getUsername());
        System.out.println();

        //Anzahl Dezimalstellen setzen
        tr.setDecimalPlace(2);
        System.out.println("Anzahl Nachkommastellen: " +tr.getDecimalPlace());
        System.out.println();

        //Fester Term testen...
        System.out.println("Fester Term testen:");
        Term staticNumb = new Term(new BigDecimal(20), new BigDecimal(3), '/',tr.getDecimalPlace());
        System.out.println(staticNumb.toString()+ staticNumb.getSolution()); //... und ausgeben
        System.out.println("Nutzer gibt ein: \"6,67\". Ergebnis: " +tr.solveTerm("6,67", staticNumb)); //Richtiges Ergebnis von Nutzer testen
        System.out.println("Nutzer gibt ein: \"5\". Ergebnis: " +tr.solveTerm("5", staticNumb)); //Falsches Ergebnis vom Nutzer testen
        System.out.println("Nutzer gibt ein: \"s\". Ergebnis: " +tr.solveTerm("s", staticNumb)); //Ungültuge Eingabe vom Nutzer testen
        System.out.println();

        //Terme in allen Schwierigkeitsgrade testen
        System.out.println("Schwierigkeitsgrad: EASY");
        tr.setDifficulty(Difficulty.EASY);
        testRandomTerms();
        System.out.println();

        System.out.println("Schwierigkeitsgrad: MEDIUM");
        tr.setDifficulty(Difficulty.MEDIUM);
        testRandomTerms();
        System.out.println();

        System.out.println("Schwierigkeitsgrad: DIFFICULT");
        tr.setDifficulty(Difficulty.HARD);
        testRandomTerms();
        System.out.println();

        tr.setUserScore(20);
        System.out.println("User Score auf 20 setzen: "+ tr.getUserScore());
        tr.addToUserScore(10);
        //Ergebnis der User Score Erhoehung ist auch abhaengig von dem Schwierigkeitsgrad der geloesten Aufgabe.
        System.out.println("User Score mit Zeitbonus 10 fuer schnelle Loesungen erhoehen: "+ tr.getUserScore());
        System.out.println();

        System.out.println("Output der Historie von vorherigen Rechnungsdurchlaeufen: ");
        tr.addToHistory();
        testHistoryEntries(tr.getHistory());
        System.out.println();

        System.out.println("Testen der Bonuspunkte je nach Schnelligkeit der Eingabe");
        tr.setUserScore(0); //User Score zuruecksetzen fuer Test der Bonuspunkte
        Term gradedTerm = new Term(new BigDecimal(2), new BigDecimal(3), '*',tr.getDecimalPlace());
        System.out.println(gradedTerm.toString()+ gradedTerm.getSolution()); //... und ausgeben
        System.out.println("Auf der Schwierigkeit HARD werden fuer eine richtige Loesung standardmaessig 3 Pkt berechnet");
        testBonuspointsCalculation(gradedTerm);
        System.out.println();


    }

    private static void testRandomTerms() {
        //Random Zahl erstellen...
        System.out.println("Zufallsterme:");
        Term t1 = tr.createTerm();
        Term t2 = tr.createTerm();
        Term t3 = tr.createTerm();
        System.out.println(t1.toString() + t1.getSolution()); //... und ausgeben
        System.out.println(t2.toString() + t2.getSolution()); //... und ausgeben
        System.out.println(t3.toString() + t3.getSolution()); //... und ausgeben
    }

    private static void testHistoryEntries(List<String> historylist){
        for(String entry : historylist){
            System.out.println(entry);
        }
    }

    private static void testBonuspointsCalculation(Term gradedTerm){
        for(int i=1; i<=5; i++){
            tr.solveTerm("6", gradedTerm, i);
            System.out.println("Nutzer gibt 6 nach "+i+" Sekunden ein:  Ergebnis mit Bonuspunkten: "+ tr.getUserScore() ); //nach einer Sekunde
            tr.setUserScore(0);
        }
    }
}
