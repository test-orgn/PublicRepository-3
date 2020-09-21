-- S4.7. 
-- Geef van het hele bedrijf een overzicht van het aantal medewerkers dat
-- er werkt (`aantal_medewerkers`), de gemiddelde commissie die ze
-- krijgen (`commissie_medewerkers`), en hoeveel dat gemiddeld
-- per verkoper is (`commissie_verkopers`).
-- DROP VIEW IF EXISTS s4_7; CREATE OR REPLACE VIEW s4_7 AS                                                     -- [TEST]

SELECT COUNT(*) AS aantal_medewerkers, 
AVG(COALESCE(comm, 0)) AS commissie_medewerkers, AVG(comm) AS commissie_verkopers 
FROM medewerkers;