ALTER TABLE medewerkers ADD CONSTRAINT m_comm_chk 
CHECK((functie='VERKOPER' AND comm IS NOT NULL) OR (functie<>'VERKOPER' AND comm IS NULL));