ALTER TABLE medewerkers
ADD geslacht varchar(1) CONSTRAINT m_geslacht_chk CHECK(geslacht='M' OR geslacht='V');
-- ERROR:  new row for relation "medewerkers" violates check constraint "m_geslacht_chk"