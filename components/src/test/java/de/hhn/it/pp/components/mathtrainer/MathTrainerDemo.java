package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.example.coffeemakerservice.CoffeeMakerServiceUsageDemo;
import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MathTrainerDemo {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MathTrainerDemo.class);

    public static BiKrMathTrainer tr;

    public static void main(String[] args) throws IllegalParameterException {
        tr = new BiKrMathTrainer();

        //Nutzername
        tr.setUsername("Peter");
        logger.debug("Nutzername eingegeben: " + tr.getUsername());
        logger.debug("");

        //Anzahl Dezimalstellen setzen
        tr.setDecimalPlace(2);
        logger.debug("Anzahl Nachkommastellen gesetzt: " +tr.getDecimalPlace());
        logger.debug("");

        //Fester Term testen...
        logger.debug("Fester Term testen:");
        Term staticNumb = new Term(new BigDecimal(20), new BigDecimal(3), '/',tr.getDecimalPlace());
        logger.debug(staticNumb.toString()+ staticNumb.getSolution()); //... und ausgeben
        logger.debug("Nutzer gibt ein: \"6,67\". Ergebnis: " +tr.solveTerm("6,67", staticNumb)); //Richtiges Ergebnis von Nutzer testen
        logger.debug("Nutzer gibt ein: \"5\". Ergebnis: " +tr.solveTerm("5", staticNumb)); //Falsches Ergebnis vom Nutzer testen
        //logger.debug("Nutzer gibt ein: \"s\". Ergebnis: " +tr.solveTerm("s", staticNumb)); //Ungültuge Eingabe vom Nutzer testen
        logger.debug("");

        //Terme in allen Schwierigkeitsgrade testen
        logger.debug("Schwierigkeitsgrad: EASY");
        tr.setDifficulty(Difficulty.EASY);
        testRandomTerms();
        logger.debug("");

        logger.debug("Schwierigkeitsgrad: MEDIUM");
        tr.setDifficulty(Difficulty.MEDIUM);
        testRandomTerms();
        logger.debug("");

        logger.debug("Schwierigkeitsgrad: DIFFICULT");
        tr.setDifficulty(Difficulty.HARD);
        testRandomTerms();
        logger.debug("");

        tr.setUserScore(20);
        logger.debug("User Score auf 20 setzen: "+ tr.getUserScore());
        tr.addToUserScore(10);
        //Ergebnis der User Score Erhoehung ist auch abhaengig von dem Schwierigkeitsgrad der geloesten Aufgabe.
        logger.debug("User Score mit Zeitbonus 10 fuer schnelle Loesungen erhoehen: "+ tr.getUserScore());
        logger.debug("");

        logger.debug("Output der Historie von vorherigen Rechnungsdurchlaeufen: ");
        tr.addToHistory();
        testHistoryEntries(tr.getHistory());
        logger.debug("");

        logger.debug("Testen der Bonuspunkte je nach Schnelligkeit der Eingabe");
        tr.setUserScore(0); //User Score zuruecksetzen fuer Test der Bonuspunkte
        Term gradedTerm = new Term(new BigDecimal(2), new BigDecimal(3), '*',tr.getDecimalPlace());
        logger.debug(gradedTerm.toString()+ gradedTerm.getSolution()); //... und ausgeben
        logger.debug("Auf der Schwierigkeit HARD werden fuer eine richtige Loesung standardmaessig 3 Pkt berechnet");
        testBonuspointsCalculation(gradedTerm);
        logger.debug("");

        logger.debug("Testen der User Hilfe zur Anzeige des Ergebnisses");
        logger.debug("Das vom Programm generierte Ergebnis der Gleichung "+ gradedTerm.toString() + " ist "+ tr.helpUser(gradedTerm));
        logger.debug("");

        logger.debug("Testen eines Spieldurchlaufs mit 20 Rechnungen");
        logger.debug("Bei diesem Test werden alle 20 Fragen bewusst falsch beantwortet.");
        logger.debug("Im Warmup mode: ");
        tr.startGame(true);
        logger.debug("Erwartetes Ergebnis fuer Userscore = 1, tatsaechlicher Userscore: "+tr.getUserScore());
        tr.setUserScore(0); //UserScore nach Test auf Ausgangszustand zuruecksetzen.
        logger.debug("");

        logger.debug("Spieldurchlauf abbrechen testen (Spiel wird in der 1. Runde abgebrochen)");
        tr.setWantsToExit(true);
        tr.startGame(true);
        logger.debug("Erwartetes Ergebnis fuer Userscore = 0, tatsaechlicher Userscore: "+tr.getUserScore());
        tr.setUserScore(0); //UserScore nach Test auf Ausgangszustand zuruecksetzen.
        tr.setWantsToExit(false); //Ausgangszustand wiederherstellen
        logger.debug("");

        logger.debug("Testen eines Spieldurchlaufs mit 20 Rechnungen");
        logger.debug("Bei diesem Test werden alle 20 Fragen bewusst falsch beantwortet.");
        logger.debug("Im Countdown mode: ");
        tr.startGame(false);
        logger.debug("Erwartetes Ergebnis fuer Userscore = 1, tatsaechlicher Userscore: "+tr.getUserScore());
        tr.setUserScore(0); //UserScore nach Test auf Ausgangszustand zuruecksetzen.
        logger.debug("");

        logger.debug("Spieldurchlauf abbrechen testen (Spiel wird in der 1. Runde abgebrochen)");
        tr.setWantsToExit(true);
        tr.startGame(false);
        logger.debug("Erwartetes Ergebnis fuer Userscore = 0, tatsaechlicher Userscore: "+tr.getUserScore());
        tr.setUserScore(0); //UserScore nach Test auf Ausgangszustand zuruecksetzen.
        tr.setWantsToExit(false); //Ausgangszustand wiederherstellen
        logger.debug("");




    }

    private static void testRandomTerms() {
        //Random Zahl erstellen...
        logger.debug("Zufallsterme:");
        Term t1 = tr.createTerm();
        Term t2 = tr.createTerm();
        Term t3 = tr.createTerm();
        logger.debug(t1.toString() + t1.getSolution()); //... und ausgeben
        logger.debug(t2.toString() + t2.getSolution()); //... und ausgeben
        logger.debug(t3.toString() + t3.getSolution()); //... und ausgeben
    }

    private static void testHistoryEntries(List<String> historylist){
        for(String entry : historylist){
            logger.debug(entry);
        }
    }

    private static void testBonuspointsCalculation(Term gradedTerm) throws IllegalParameterException{
        for(int i=1; i<=5; i++){
            tr.solveTerm("6", gradedTerm, i);
            logger.debug("Nutzer gibt 6 nach "+i+" Sekunden ein:  Ergebnis mit Bonuspunkten: "+ tr.getUserScore() ); //nach einer Sekunde
            tr.setUserScore(0);
        }
    }
}
