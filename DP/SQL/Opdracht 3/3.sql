-- S3.3.
-- Geef elke afdeling (`afdeling`) met de naam van het hoofd van die
-- afdeling (`hoofd`).
-- DROP VIEW IF EXISTS s3_3; CREATE OR REPLACE VIEW s3_3 AS                                                     -- [TEST]

SELECT afdelingen.naam as afdeling, medewerkers.naam as hoofd FROM afdelingen
INNER JOIN medewerkers ON afdelingen.hoofd=medewerkers.mnr;