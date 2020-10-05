-- S6.2.
--
-- 1. Maak een view met de naam "dagcursussen". Deze view dient de gegevens op te halen: 
--      code, omschrijving en type uit de tabel curssussen met als voorwaarde dat de lengte = 1. Toon aan dat de view werkt. 
-- 2. Maak een tweede view met de naam "daguitvoeringen". 
--    Deze view dient de uitvoeringsgegevens op te halen voor de "dagcurssussen" (gebruik ook de view "dagcursussen"). Toon aan dat de view werkt
-- 3. Verwijder de views en laat zien wat de verschillen zijn bij DROP view <viewnaam> CASCADE en bij DROP view <viewnaam> RESTRICT

CREATE OR REPLACE VIEW dagcursussen AS
SELECT code, omschrijving, type FROM cursussen WHERE lengte = 1;

CREATE OR REPLACE VIEW daguitvoeringen AS
SELECT u.cursus, u.begindatum, u.docent, u.locatie FROM dagcursussen, uitvoeringen AS u
WHERE dagcursussen.code = u.cursus;

-- Als je een view verwijdert met CASCADE, verwijdert SQL automatisch alle views die die view gebruiken in hun query wordt.
-- Als je een view verwijdert met RESTRICT, geeft SQL netjes een error message dat de view die je probeert te verwijderen in een andere view gebruikt wordt.