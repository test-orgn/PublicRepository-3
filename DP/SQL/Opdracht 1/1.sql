-- S1.1. Geslacht
--
-- Voeg een kolom `geslacht` toe aan de medewerkerstabel.
-- Voeg ook een beperkingsregel `m_geslacht_chk` toe aan deze kolom,
-- die ervoor zorgt dat alleen 'M' of 'V' als geldige waarde wordt
-- geaccepteerd. Test deze regel en neem de gegooide foutmelding op als
-- commentaar in de uitwerking.

ALTER TABLE medewerkers
ADD geslacht varchar(1) CONSTRAINT m_geslacht_chk CHECK(geslacht='M' OR geslacht='V');
-- ERROR:  new row for relation "medewerkers" violates check constraint "m_geslacht_chk"