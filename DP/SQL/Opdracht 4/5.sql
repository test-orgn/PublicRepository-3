-- S4.5. 
-- Hoeveel uitvoeringen (`aantal`) zijn er gepland per cursus?
-- Een voorbeeld van het mogelijke resultaat staat hieronder.
--
--   cursus | aantal   
--  --------+-----------
--   ERM    | 1 
--   JAV    | 4 
--   OAG    | 2 
-- DROP VIEW IF EXISTS s4_5; CREATE OR REPLACE VIEW s4_5 AS                                                     -- [TEST]

SELECT cursus, COUNT(*) as aantal
FROM uitvoeringen
GROUP BY cursus;