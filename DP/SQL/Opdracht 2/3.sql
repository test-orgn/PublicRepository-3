-- S2.3. Door het land
--
-- Welke cursussen zijn in Utrecht en/of in Maastricht uitgevoerd? Geef
-- code en begindatum.
DROP VIEW IF EXISTS s2_3; CREATE OR REPLACE VIEW s2_3 AS
SELECT cursus, begindatum FROM uitvoeringen WHERE (locatie='UTRECHT' OR locatie='MAASTRICHT');