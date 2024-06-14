package de.hka.iwii.db1.jpa;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hka.iwii.db1.jpa.models.Buchung;
import de.hka.iwii.db1.jpa.models.Kunde;
import de.hka.iwii.db1.jpa.models.Flug;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class JPAApplication {
    private EntityManagerFactory entityManagerFactory;

    public JPAApplication() {
        Logger.getLogger("org.hibernate").setLevel(Level.ALL);
        entityManagerFactory = Persistence.createEntityManagerFactory("DB1");
    }

    public void testFlights() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Kunden erstellen
            Kunde kunde1 = new Kunde();
            kunde1.setVorname("John");
            kunde1.setNachname("Doe");
            kunde1.setEmail("john.doe@example.com");

            Kunde kunde2 = new Kunde();
            kunde2.setVorname("Jane");
            kunde2.setNachname("Smith");
            kunde2.setEmail("jane.smith@example.com");

            entityManager.persist(kunde1);
            entityManager.persist(kunde2);

            // Flüge erstellen
            Flug flug1 = new Flug();
            flug1.setFlugnummer("FL123");
            flug1.setStartzeit("2024-06-15T10:00:00");
            flug1.setStartflughafen("JFK");

            Flug flug2 = new Flug();
            flug2.setFlugnummer("FL456");
            flug2.setStartzeit("2024-06-16T15:00:00");
            flug2.setStartflughafen("LAX");

            Flug flug3 = new Flug();
            flug3.setFlugnummer("FL789");
            flug3.setStartzeit("2024-06-17T20:00:00");
            flug3.setStartflughafen("ORD");

            entityManager.persist(flug1);
            entityManager.persist(flug2);
            entityManager.persist(flug3);

            // Buchungen erstellen
            Buchung buchung1 = new Buchung();
            buchung1.setKunde(kunde1);
            buchung1.setFlug(flug1);
            buchung1.setPlaetze(2);
            buchung1.setBuchungsdatum(new Date());

            Buchung buchung2 = new Buchung();
            buchung2.setKunde(kunde1);
            buchung2.setFlug(flug2);
            buchung2.setPlaetze(2);
            buchung2.setBuchungsdatum(new Date());

            Buchung buchung3 = new Buchung();
            buchung3.setKunde(kunde2);
            buchung3.setFlug(flug2);
            buchung3.setPlaetze(2);
            buchung3.setBuchungsdatum(new Date());

            Buchung buchung4 = new Buchung();
            buchung4.setKunde(kunde2);
            buchung4.setFlug(flug3);
            buchung4.setPlaetze(2);
            buchung4.setBuchungsdatum(new Date());

            entityManager.persist(buchung1);
            entityManager.persist(buchung2);
            entityManager.persist(buchung3);
            entityManager.persist(buchung4);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void getBookingsByCustomerLastName(String nachname) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Suche nach Kunden mit dem angegebenen Nachnamen
            TypedQuery<Kunde> kundenQuery = entityManager.createQuery(
                "SELECT k FROM Kunde k WHERE k.nachname = :nachname", Kunde.class);
            kundenQuery.setParameter("nachname", nachname);
            List<Kunde> kunden = kundenQuery.getResultList();

            // Durchlaufen der gefundenen Kunden und Abrufen ihrer Buchungen
            for (Kunde kunde : kunden) {
                TypedQuery<Buchung> buchungsQuery = entityManager.createQuery(
                    "SELECT b FROM Buchung b WHERE b.kunde = :kunde", Buchung.class);
                buchungsQuery.setParameter("kunde", kunde);
                List<Buchung> buchungen = buchungsQuery.getResultList();

                // Ausgeben der Buchungen
                System.out.println("Buchungen für Kunde: " + kunde.getVorname() + " " + kunde.getNachname());
                for (Buchung buchung : buchungen) {
                    System.out.println("Flug: " + buchung.getFlug().getFlugnummer() +
                        ", Plätze: " + buchung.getPlaetze() +
                        ", Buchungsdatum: " + buchung.getBuchungsdatum());
                }
            }
        } finally {
            entityManager.close();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void main(String[] args) {
        JPAApplication app = new JPAApplication();
        app.testFlights();
        app.getBookingsByCustomerLastName("Smith");
    }
}
