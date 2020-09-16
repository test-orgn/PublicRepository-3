-- S4.1. 
-- Geef nummer, functie en geboortedatum van alle medewerkers die vóór 1980
-- geboren zijn, en trainer of verkoper zijn.
-- DROP VIEW IF EXISTS s4_1; CREATE OR REPLACE VIEW s4_1 AS                                                     -- [TEST]

SELECT mnr, functie, gbdatum FROM medewerkers 
WHERE gbdatum < '1980-01-01' AND (functie='TRAINER' OR functie='VERKOPER');