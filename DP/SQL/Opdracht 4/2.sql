-- S4.2. 
-- Geef de naam van de medewerkers met een voorvoegsel.
-- DROP VIEW IF EXISTS s4_2; CREATE OR REPLACE VIEW s4_2 AS                                                     -- [TEST]

SELECT naam FROM medewerkers
WHERE (TRIM(naam) LIKE '% %');