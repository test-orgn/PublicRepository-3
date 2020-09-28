-- S5.2.
-- Geef de nummers van alle medewerkers die niet aan de afdeling 'OPLEIDINGEN'
-- zijn verbonden.
-- DROP VIEW IF EXISTS s5_2; CREATE OR REPLACE VIEW s5_2 AS                                                     -- [TEST]

SELECT mnr FROM medewerkers
WHERE afd NOT IN (SELECT anr FROM afdelingen WHERE naam='OPLEIDINGEN');