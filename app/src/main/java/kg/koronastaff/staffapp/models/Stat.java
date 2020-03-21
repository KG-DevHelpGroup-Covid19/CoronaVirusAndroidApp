package kg.koronastaff.staffapp.models;

public class Stat {
    int infected;
    int recovered;
    int dead;

    public Stat(int infected, int recovered, int dead) {
        this.infected = infected;
        this.recovered = recovered;
        this.dead = dead;
    }

    public Stat() {
    }

    public int getInfected() {
        return infected;
    }

    public void setInfected(int infected) {
        this.infected = infected;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }
}
