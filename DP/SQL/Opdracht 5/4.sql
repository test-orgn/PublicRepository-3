-- S5.4.
-- Welke medewerkers (naam) hebben ondergeschikten? En welke niet? (Je mag twee
-- queries gebruiken.)
-- DROP VIEW IF EXISTS s5_4; CREATE OR REPLACE VIEW s5_4 AS                                                     -- [TEST]

SELECT naam FROM medewerkers AS m
WHERE EXISTS (SELECT chef FROM medewerkers WHERE chef=m.mnr);

SELECT naam FROM medewerkers AS m
WHERE NOT EXISTS (SELECT chef FROM medewerkers WHERE chef=m.mnr);