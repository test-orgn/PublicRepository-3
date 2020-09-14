-- S3.2.
-- Geef in twee kolommen naast elkaar de naam van elke cursist (`cursist`)
-- die een S02-cursus heeft gevolgd, met de naam van de docent (`docent`).
-- DROP VIEW IF EXISTS s3_2; CREATE OR REPLACE VIEW s3_2 AS                                                     -- [TEST]

SELECT c.naam as cursist, d.naam as docent 
FROM inschrijvingen, uitvoeringen, medewerkers c, medewerkers d
WHERE inschrijvingen.cursus='S02' 
AND uitvoeringen.begindatum=inschrijvingen.begindatum 
AND inschrijvingen.cursist=c.mnr
AND uitvoeringen.docent=d.mnr
AND uitvoeringen.cursus=inschrijvingen.cursus;