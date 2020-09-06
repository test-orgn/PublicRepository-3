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