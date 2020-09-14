-- S3.1.
-- Produceer een overzicht van alle cursusuitvoeringen; geef de
-- cursuscode, de begindatum, de cursuslengte en de naam van de docent.
-- DROP VIEW IF EXISTS s3_1; CREATE OR REPLACE VIEW s3_1 AS                                                     -- [TEST]

SELECT uitvoeringen.cursus, uitvoeringen.begindatum, cursussen.lengte, medewerkers.naam FROM uitvoeringen
INNER JOIN cursussen ON uitvoeringen.cursus=cursussen.code
INNER JOIN medewerkers ON uitvoeringen.docent=medewerkers.mnr;