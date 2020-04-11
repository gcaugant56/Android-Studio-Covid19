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

    public Integer getNewConfirmed() {
        return NewConfirmed;
    }

    public Integer getTotalConfirmed() {
        return TotalConfirmed;
    }

    public Integer getNewDeath() {
        return NewDeaths;
    }

    public Integer getTotalDeath() {
        return TotalDeaths;
    }

    public Integer getNewRecovered() {
        return NewRecovered;
    }

    public Integer getTotalRecovered() {
        return TotalRecovered;
    }
    public String toString(){
        return this.Country +"\nNew Confirmed : "+this.getNewConfirmed()+"\nTotal Confirmed :"+getTotalConfirmed();
    }
}
