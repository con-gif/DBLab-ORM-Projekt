package de.hka.iwii.db1.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Flug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="serial")
	private int id;

    @Column(nullable = false, unique = true)
    private String flugnummer;

    @Column(nullable = false)
    private String startzeit;

    @Column(nullable = false)
    private String startflughafen;

    // Getters und Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlugnummer() {
        return flugnummer;
    }

    public void setFlugnummer(String flugnummer) {
        this.flugnummer = flugnummer;
    }

    public String getStartzeit() {
        return startzeit;
    }

    public void setStartzeit(String startzeit) {
        this.startzeit = startzeit;
    }

    public String getStartflughafen() {
        return startflughafen;
    }

    public void setStartflughafen(String startflughafen) {
        this.startflughafen = startflughafen;
    }
}
