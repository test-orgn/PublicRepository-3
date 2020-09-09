-- S1.4. Adressen
--
-- Maak een tabel `adressen`, waarin de adressen van de medewerkers worden
-- opgeslagen (inclusief adreshistorie). De tabel bestaat uit onderstaande
-- kolommen. Voeg minimaal één rij met adresgegevens van A DONK toe.
--
--    postcode      PK, bestaande uit 6 karakters (4 cijfers en 2 letters)
--    huisnummer    PK
--    ingangsdatum  PK
--    einddatum     moet na de ingangsdatum liggen
--    telefoon      10 cijfers, uniek
--    med_mnr       FK, verplicht

CREATE TABLE adressen
(
    postcode VARCHAR(6),
    huisnummer VARCHAR(5),
    ingangsdatum DATE,
    einddatum DATE,
    telefoon VARCHAR(10),
    med_mnr NUMERIC(4),
    CONSTRAINT ad_pk PRIMARY KEY(postcode, huisnummer, ingangsdatum),
    CONSTRAINT ad_datum_chk CHECK(einddatum > ingangsdatum),
    CONSTRAINT ad_tel_unq UNIQUE(telefoon),
    CONSTRAINT ad_tel_chk CHECK(LENGTH(telefoon)=10),
    CONSTRAINT ad_fk FOREIGN KEY(med_mnr) REFERENCES medewerkers(mnr)
);

INSERT INTO adressen VALUES('1234AB', 123, '2020-01-01', '2030-01-01', '0612345678', 8000);