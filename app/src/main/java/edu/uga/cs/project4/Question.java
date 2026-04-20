package edu.uga.cs.project4;

import java.util.ArrayList;
import java.util.List;


/**
 * POJO class, represents a single question
 * 1 country, 1 real capital city, 2 random answers
 */
public class Question {
    private Country country;
    private ArrayList<String> options;
    private int selectedCapital;

    /** default constructor */
    public Question() {
        this.country = null;
        this.options = new ArrayList<>();
        this.selectedCapital = -1;
    }

    /** constructor with country argument,
     * inputs capital city into list of answers */
    public Question(Country country) {
        this.country = country;
        this.options = new ArrayList<>();
        this.options.add(country.getCapitalCity());
        this.selectedCapital = -1;
    }

    public Question(Country country, ArrayList<String> options) {
        this.country = country;
        this.options = options;
        this.selectedCapital = -1;
    }

    /** adds other capital cities to question answers,
     * input is country */
    public void addOptions(Country country) {
        this.options.add(country.getCapitalCity());
    }

    /** getters and setters */
    public ArrayList<String> getOptions() {return options;}
    public Country getCountry() {return country;}
    public void setCountry(Country country) {this.country = country;}
    public void setSelectedCapital(int selectedCapital) {this.selectedCapital = selectedCapital;}
    public int getSelectedCapital() {return selectedCapital;}
    public boolean isAnswered() {return selectedCapital != -1;}
    public int getScore() {
        if(selectedCapital < 0 || selectedCapital >= options.size()) {
            return 0;
        } else {
            return options.get(selectedCapital).equals(country.getCapitalCity()) ? 1 : 0;
        }
    }
}
