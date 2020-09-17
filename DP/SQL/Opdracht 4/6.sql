-- S4.6. 
-- Bepaal hoeveel jaar leeftijdsverschil er zit tussen de oudste en de 
-- jongste medewerker (`verschil`) en bepaal de gemiddelde leeftijd van
-- de medewerkers (`gemiddeld`).
-- Je mag hierbij aannemen dat elk jaar 365 dagen heeft.
-- DROP VIEW IF EXISTS s4_6; CREATE OR REPLACE VIEW s4_6 AS                                                     -- [TEST]

SELECT DATE_PART('YEAR', AGE(MAX(gbdatum), MIN(gbdatum))) AS verschil, 
AVG(DATE_PART('YEAR', AGE(NOW(), gbdatum))) AS gemiddeld