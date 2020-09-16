-- S3.1.
-- Produceer een overzicht van alle cursusuitvoeringen; geef de
-- cursuscode, de begindatum, de cursuslengte en de naam van de docent.
-- DROP VIEW IF EXISTS s3_1; CREATE OR REPLACE VIEW s3_1 AS                                                     -- [TEST]

SELECT u.cursus, u.begindatum, c.lengte, m.naam FROM uitvoeringen AS u
INNER JOIN cursussen AS c ON u.cursus=c.code
INNER JOIN medewerkers AS m ON u.docent=m.mnr;