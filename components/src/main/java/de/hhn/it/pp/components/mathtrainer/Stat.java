package de.hhn.it.pp.components.mathtrainer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stat {
    private static ArrayList<Stat> allSavedOnes;

    Section section;
    Difficulty difficulty;
    String username;
    int points;
    Date date;

    /**
     * Constructor to create a stat.
     *
     * @param section In what section the player achieved this stat.
     * @param difficulty In what difficulty the player.
     * @param username how is player called?
     * @param points how many points did he achieve.
     * @param date when the stat was taken.
     */
    public Stat(Section section, Difficulty difficulty, String username, int points, Date date) {
        this.section = section;
        this.difficulty = difficulty;
        this.username = username;
        this.points = points;
    }

    /**
     * Get all stats according to what section and difficulty is taken.
     *
     * @param section What section the player wants to see the stats.
     * @param difficulty What difficulty the player wants to see the stats.
     */
    public static ArrayList<Stat> getStats(Section section, Difficulty difficulty) {

        ArrayList<Stat> stats = new ArrayList<>();

        for(Stat s : allSavedOnes) {
            if(s.section == section && s.difficulty == difficulty) {
                stats.add(s);
            }
        }

        return stats;
    }
}
