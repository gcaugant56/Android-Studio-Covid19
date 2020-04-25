package com.example.COVID;
import java.util.List;

public class RestAllCountry {
    List<Countries> Countries;
    GlobalStats Global;
    public List<Countries> getCountries() {
        return Countries;
    }

    public void setGlobalStats(GlobalStats globalStats) {
        this.Global = globalStats;
    }

    public GlobalStats getGlobalStats() {
        return Global;
    }

    public void setCountries(List<Countries> countries) {
        Countries = countries;
    }
}
