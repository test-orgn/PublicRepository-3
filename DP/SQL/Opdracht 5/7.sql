-- S5.7.
-- Geef voorletter(s) en achternaam van alle trainers die ooit tijdens een
-- algemene cursus hun eigen chef als cursist hebben gehad.
-- DROP VIEW IF EXISTS s5_7; CREATE OR REPLACE VIEW s5_7 AS                                                     -- [TEST]

SELECT voorl, naam FROM medewerkers
WHERE mnr IN (SELECT docent FROM uitvoeringen AS u WHERE cursus IN (SELECT code FROM cursussen WHERE type='ALG') AND
chef IN (SELECT cursist FROM inschrijvingen AS i WHERE i.cursus=u.cursus));