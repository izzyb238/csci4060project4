package edu.uga.cs.project4;

/**
 * POJO class, represents a single country
 * includes name and capital city name
 */
public class Country {
    private long id;
    private String countryName;
    private String capitalCity;

    /** default constructor */
    public Country() {
        this.id = -1;
        this.countryName = countryName;
        this.capitalCity = capitalCity;
    }

    /** constructor with arguments */
    public Country(String countryName, String capitalCity) {
        this.id = -1;
        this.countryName = countryName;
        this.capitalCity = capitalCity;
    }

    /** getter methods */
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getCountryName() {return countryName;}
    public String getCapitalCity() {return capitalCity;}

    /** toString method */
    public String toString() {return id + ": " + countryName + ", " + capitalCity;}

}
