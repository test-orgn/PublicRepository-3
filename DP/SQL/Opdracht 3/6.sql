-- S3.6.
-- Geef de namen van alle medewerkers en hun toelage.
-- DROP VIEW IF EXISTS s3_6; CREATE OR REPLACE VIEW s3_6 AS                                                     -- [TEST]

SELECT m.naam, s.toelage FROM medewerkers AS m, schalen AS s
WHERE m.maandsal >= s.ondergrens AND m.maandsal <= s.bovengrens;