package com.example.COVID;

public class Countries {
    private String Country;
    private String Slug;
    private Integer NewConfirmed;
    private Integer TotalConfirmed;
    private Integer NewDeaths;
    private Integer TotalDeaths;
    private Integer NewRecovered;
    private Integer TotalRecovered;

    public Countries(String Slug, String Country, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered)
    {
        this.Slug = Slug;
        this.Country = Country;
        this.NewConfirmed = newConfirmed;
        this.TotalConfirmed = totalConfirmed;
        this.NewDeaths = newDeaths;
        this.TotalDeaths = totalDeaths;
        this.NewRecovered = newRecovered;
        this.TotalRecovered = totalRecovered;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public void setNewConfirmed(Integer newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public void setNewDeath(Integer newDeath) {
        NewDeaths = newDeath;
    }

    public void setTotalDeath(Integer totalDeath) {
        TotalDeaths = totalDeath;
    }

    public void setNewRecovered(Integer newRecovered) {
        NewRecovered = newRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getCountry() {
        return Country;
    }

    public String getSlug() {
        return Slug;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getNewDeath() {
        return NewDeaths;
    }

    public int getTotalDeath() {
        return TotalDeaths;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public String toString(){
        return this.Country +"\nNew Confirmed : "+this.getNewConfirmed()+"\nTotal Confirmed :"+getTotalConfirmed();
    }
}
