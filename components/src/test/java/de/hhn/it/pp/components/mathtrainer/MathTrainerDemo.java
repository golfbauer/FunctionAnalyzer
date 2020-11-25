package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;

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
        Term staticNumb = new Term(new BigDecimal(2), new BigDecimal(3), '+');
        System.out.println(staticNumb.toString()); //... und ausgeben
        System.out.println("Nutzer gibt ein: \"3\". Ergebnis: " +tr.solveTerm("3", staticNumb)); //Falsches Ergebnis vom Nutzer testen
        System.out.println("Nutzer gibt ein: \"5\". Ergebnis: " +tr.solveTerm("5", staticNumb)); //Richtiges Ergebnis vom Nutzer testen
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


        System.out.println("");
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
}
