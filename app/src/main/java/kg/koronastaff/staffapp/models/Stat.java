package kg.koronastaff.staffapp.models;

public class Stat {
    String infected;
    String recovered;
    String died;

    public Stat(String infected, String recovered, String dead) {
        this.infected = infected;
        this.recovered = recovered;
        this.died = dead;
    }

    public Stat() {
    }

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDied() {
        return died;
    }

    public void setDead(String dead) {
        this.died = died;
    }
}
