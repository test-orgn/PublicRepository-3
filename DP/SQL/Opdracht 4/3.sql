-- S4.3. 
-- Geef nu code, begindatum en aantal inschrijvingen (`aantal_inschrijvingen`) van alle
-- cursusuitvoeringen in 2019 met minstens drie inschrijvingen.
-- DROP VIEW IF EXISTS s4_3; CREATE OR REPLACE VIEW s4_3 AS                                                     -- [TEST]

SELECT u.cursus, u.begindatum, 
    (SELECT COUNT(*) FROM inschrijvingen AS i WHERE i.cursus=u.cursus AND i.begindatum=u.begindatum) 
    as aantal_inschrijvingen
    
FROM uitvoeringen AS u

WHERE (DATE_PART('YEAR', begindatum)=2019 AND 
       (SELECT COUNT(*) FROM inschrijvingen AS i WHERE i.cursus=u.cursus AND i.begindatum=u.begindatum) >= 3)

GROUP BY cursus, begindatum;