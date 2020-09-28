-- S5.3.
-- Geef de nummers van alle medewerkers die de Java-cursus niet hebben 
-- gevolgd.
-- DROP VIEW IF EXISTS s5_3; CREATE OR REPLACE VIEW s5_3 AS                                                     -- [TEST]

SELECT mnr FROM medewerkers
WHERE mnr NOT IN (SELECT cursist FROM inschrijvingen WHERE cursus='JAV');