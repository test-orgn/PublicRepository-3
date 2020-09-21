-- S4.3. 
-- Geef nu code, begindatum en aantal inschrijvingen (`aantal_inschrijvingen`) van alle
-- cursusuitvoeringen in 2019 met minstens drie inschrijvingen.
-- DROP VIEW IF EXISTS s4_3; CREATE OR REPLACE VIEW s4_3 AS                                                     -- [TEST]

SELECT u.cursus, u.begindatum, COUNT(*) AS aantal_inschrijvingen
FROM uitvoeringen AS u JOIN inschrijvingen AS i ON i.cursus=u.cursus AND i.begindatum=u.begindatum
WHERE DATE_PART('YEAR', u.begindatum)=2019
GROUP BY u.cursus, u.begindatum
HAVING COUNT(*) >= 3;