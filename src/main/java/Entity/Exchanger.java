package Entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Exchanger {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "usd")
    private Double usd;

    @Column(name = "eur")
    private Double eur;

    @Column(name = "uah")
    private Double uah;

    public Exchanger() {
    }

    public Exchanger(String id, Double usd, Double eur, Double uah) {
        this.id = id;
        this.usd = usd;
        this.eur = eur;
        this.uah = uah;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Double getUah() {
        return uah;
    }

    public void setUah(Double uah) {
        this.uah = uah;
    }
}
