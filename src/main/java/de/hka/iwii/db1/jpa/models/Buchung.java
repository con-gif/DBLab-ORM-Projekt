package de.hka.iwii.db1.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Buchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Kunde kunde;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Flug flug;

    @Column(nullable = false)
    private int plaetze;

    @Column(nullable = false)
    private Date buchungsdatum;

    // Getters und Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Flug getFlug() {
        return flug;
    }

    public void setFlug(Flug flug) {
        this.flug = flug;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public Date getBuchungsdatum() {
        return buchungsdatum;
    }

    public void setBuchungsdatum(Date buchungsdatum) {
        this.buchungsdatum = buchungsdatum;
    }
}
