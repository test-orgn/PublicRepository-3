-- S2.9. Salarisverhoging
--
-- De medewerkers van de afdeling VERKOOP krijgen een salarisverhoging
-- van 5.5%, behalve de manager van de afdeling, deze krijgt namelijk meer: 7%.
-- Voer deze verhogingen door.
UPDATE medewerkers SET maandsal=maandsal * 1.055 WHERE (functie<>'MANAGER');
UPDATE medewerkers SET maandsal=maandsal * 1.07 WHERE (functie='MANAGER');