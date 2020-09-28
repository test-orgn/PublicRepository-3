-- S5.8.
-- Geef de naam van de medewerkers die nog nooit een cursus hebben gegeven.
-- DROP VIEW IF EXISTS s5_8; CREATE OR REPLACE VIEW s5_8 AS                                                     -- [TEST]

SELECT naam FROM medewerkers
WHERE NOT EXISTS (SELECT docent FROM uitvoeringen WHERE docent=mnr);