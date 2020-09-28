-- S5.6.
-- Geef van alle cursusuitvoeringen: de cursuscode, de begindatum en het 
-- aantal inschrijvingen (`aantal_inschrijvingen`). Sorteer op begindatum.
-- DROP VIEW IF EXISTS s5_6; CREATE OR REPLACE VIEW s5_6 AS                                                     -- [TEST]

SELECT cursus, begindatum, 
(SELECT COUNT(*) FROM inschrijvingen AS i WHERE i.cursus=u.cursus AND i.begindatum=u.begindatum) 
AS aantal_inschrijvingen FROM uitvoeringen AS u
GROUP BY cursus, begindatum
ORDER BY begindatum;