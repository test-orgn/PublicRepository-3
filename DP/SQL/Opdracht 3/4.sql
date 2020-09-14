-- S3.4.
-- Geef de namen van alle medewerkers, de naam van hun afdeling (`afdeling`)
-- en de bijbehorende locatie.
-- DROP VIEW IF EXISTS s3_4; CREATE OR REPLACE VIEW s3_4 AS                                                     -- [TEST]

SELECT medewerkers.naam, afdelingen.naam as afdeling, afdelingen.locatie 
FROM medewerkers, afdelingen
WHERE medewerkers.afd=afdelingen.anr;