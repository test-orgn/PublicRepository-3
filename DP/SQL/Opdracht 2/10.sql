-- S2.10. Concurrent
--
-- Martens heeft als verkoper succes en wordt door de concurrent
-- weggekocht. Verwijder zijn gegevens.
DELETE FROM medewerkers WHERE naam='MARTENS';
-- Zijn collega Alders heeft ook plannen om te vertrekken. Verwijder ook zijn gegevens.
-- Waarom lukt dit (niet)?
DELETE FROM medewerkers WHERE naam='ALDERS';
-- ERROR:  update or delete on table "medewerkers" violates foreign key constraint "i_cursist_fk" on table "inschrijvingen"
-- DETAIL:  Key (mnr)=(7499) is still referenced from table "inschrijvingen".
-- SQL state: 23503
-- Antwoord: Hij staat nog ingeschreven bij een cursus en bij een inschrijving mag de cursist FK niet leeg zijn. 
-- Oplossing: eerst inschrijving verwijderen en pas daarna de medewerker.