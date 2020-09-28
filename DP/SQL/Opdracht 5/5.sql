-- S5.5.
-- Geef cursuscode en begindatum van alle uitvoeringen van programmeercursussen
-- ('BLD') in 2020.
-- DROP VIEW IF EXISTS s5_5; CREATE OR REPLACE VIEW s5_5 AS                                                     -- [TEST]

SELECT cursus, begindatum FROM uitvoeringen
WHERE cursus IN (SELECT code FROM cursussen WHERE type='BLD' AND DATE_PART('year', begindatum)=2020);