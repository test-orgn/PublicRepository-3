-- S4.4. 
-- Welke medewerkers hebben een bepaalde cursus meer dan één keer gevolgd?
-- Geef medewerkernummer en cursuscode.
-- DROP VIEW IF EXISTS s4_4; CREATE OR REPLACE VIEW s4_4 AS                                                     -- [TEST]

SELECT cursist, cursus
FROM inschrijvingen AS i
GROUP BY cursist, cursus
HAVING COUNT(*) > 1;