-- S2.11. Nieuwe afdeling
--
-- Je wordt hoofd van de nieuwe afdeling 'FINANCIEN' te Leerdam,
-- onder de hoede van De Koning. Kies een personeelnummer boven de 8000.
-- Zorg voor de juiste invoer van deze gegevens.
INSERT INTO afdelingen VALUES(80, 'FINANCIEN', 'LEERDAM', NULL);
ON CONFLICT DO NOTHING;
INSERT INTO medewerkers VALUES(8069, 'CORNEL', 'HP', 'FINANCIEEL', 7839, '2000-04-17', 5000.0, NULL, 80);
UPDATE afdelingen SET hoofd=8069 WHERE anr=80
ON CONFLICT DO NOTHING;