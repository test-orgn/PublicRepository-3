-- S5.1. 
-- Welke medewerkers hebben zowel de Java als de XML cursus
-- gevolgd? Geef hun personeelsnummers.
-- DROP VIEW IF EXISTS s5_1; CREATE OR REPLACE VIEW s5_1 AS                                                     -- [TEST]

SELECT DISTINCT cursist FROM inschrijvingen
WHERE cursus='XML' AND cursist IN(SELECT DISTINCT cursist FROM inschrijvingen WHERE cursus='JAV');