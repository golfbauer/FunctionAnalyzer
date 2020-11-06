package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;

public class MathTrainerDemo {
    public static void main(String[] args) {
        BiKrMathTrainer first = new BiKrMathTrainer();

        first.setUsername("Peter");
        System.out.println("Username set: " + first.getUsername());

        //Feste Zahl erstellen...
        Term staticNumb = new Term(new BigDecimal(2), new BigDecimal(3), '+');
        System.out.println(staticNumb.toString()); //... und ausgeben
        System.out.println("Nutzereingabe: 3. Ergebnis: " +first.solveTerm("3", staticNumb)); //Falsches Ergebnis vom Nutzer testen
        System.out.println("Nutzereingabe: 5. Ergebnis: " +first.solveTerm("5", staticNumb)); //Richtiges Ergebnis vom Nutzer testen
        System.out.println("Nutzereingabe: s. Ergebnis: " +first.solveTerm("s", staticNumb)); //Ungültuge Eingabe vom Nutzer testen
        System.out.println();

        //Random Zahl erstellen...
        Term t = first.createTerm();
        System.out.println(t.toString()); //... und ausgeben


    }
}
