-- S6.1.
--
-- 1. Maak een view met de naam "deelnemers" waarmee je de volgende gegevens uit de tabellen inschrijvingen en uitvoering combineert:
--    inschrijvingen.cursist, inschrijvingen.cursus, inschrijvingen.begindatum, uitvoeringen.docent, uitvoeringen.locatie
-- 2. Gebruik de view in een query waarbij je de "deelnemers" view combineert met de "personeels" view (behandeld in de les):
--     CREATE OR REPLACE VIEW personeel AS
--       SELECT mnr, voorl, naam as medewerker, afd, functie
--       FROM medewerkers;
-- 3. Is de view "deelnemers" updatable ? Waarom ?

CREATE OR REPLACE VIEW deelnemers AS
SELECT inschrijvingen.cursist, inschrijvingen.cursus, inschrijvingen.begindatum, uitvoeringen.docent, uitvoeringen.locatie
FROM inschrijvingen JOIN uitvoeringen on inschrijvingen.cursus = uitvoeringen.cursus AND inschrijvingen.begindatum = uitvoeringen.begindatum;

CREATE OR REPLACE VIEW personeel AS
SELECT mnr, voorl, naam as medewerker, afd, functie
FROM medewerkers;

SELECT DISTINCT mnr FROM deelnemers, personeel WHERE mnr = docent;

-- Ik weet niet in welke context "update" hier bedoelt wordt, dus ik 2 antwoorden:
-- De view deelnemers is niet updatable tenzij je naast CREATE ook OR REPLACE zet, zodat elke keer het wordt overschreven / geupdate.
-- De view deelnemers is niet updatable tenzij je "WITH CHECK OPTION" gebruikt, zodat je bijvoorbeeld UPDATE deelnemers SET cursist = ...