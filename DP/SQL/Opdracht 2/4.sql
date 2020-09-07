-- S2.4. Namen
--
-- Geef de naam en voorletters van alle medewerkers, behalve van R. Jansen.
DROP VIEW IF EXISTS s2_4; CREATE OR REPLACE VIEW s2_4 AS
SELECT naam, voorl FROM medewerkers WHERE (naam<>'JANSEN' OR voorl<>'R');