-- S1.2. Nieuwe afdeling
--
-- Het bedrijf krijgt een nieuwe onderzoeksafdeling 'ONDERZOEK' in Zwolle.
-- Om de onderzoeksafdeling op te zetten en daarna te leiden wordt de
-- nieuwe medewerker A DONK aangenomen. Hij krijgt medewerkersnummer 8000
-- en valt direct onder de directeur.
-- Voeg de nieuwe afdeling en de nieuwe medewerker toe aan de database.

INSERT INTO medewerkers
VALUES(8000, 'DONK', 'A', 'ONDERZOEK', 7839, '1900-12-5', 3000.00, NULL, NULL);

INSERT INTO afdelingen
VALUES(50, 'ONDERZOEK', 'ZWOLLE', 8000);

UPDATE medewerkers SET afd=50 WHERE mnr=8000;