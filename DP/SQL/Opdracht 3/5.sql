-- S3.5.
-- Geef de namen van alle cursisten die staan ingeschreven voor de cursus S02 van 12 april 2019
-- DROP VIEW IF EXISTS s3_5; CREATE OR REPLACE VIEW s3_5 AS  
                                                   -- [TEST]
SELECT medewerkers.naam FROM medewerkers, inschrijvingen
WHERE inschrijvingen.cursist=medewerkers.mnr AND inschrijvingen.cursus='S02' AND inschrijvingen.begindatum='2019-04-12';