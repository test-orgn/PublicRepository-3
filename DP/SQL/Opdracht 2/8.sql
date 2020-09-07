-- S2.8. Nieuwe cursus
--
-- Er wordt een nieuwe 6-daagse cursus 'Data & Persistency' in het programma opgenomen.
-- Voeg deze cursus met code 'D&P' toe, maak twee uitvoeringen in Leerdam en schrijf drie
-- mensen in.
INSERT INTO cursussen VALUES('D&P', 'Data & Persistency', 'ALG', 6);
ON CONFLICT DO NOTHING;
INSERT INTO uitvoeringen VALUES('D&P', '2021-01-01', 7369, 'LEERDAM');
ON CONFLICT DO NOTHING;
INSERT INTO uitvoeringen VALUES('D&P', '2022-02-02', 7369, 'LEERDAM');
ON CONFLICT DO NOTHING;
INSERT INTO inschrijvingen VALUES(7369, 'D&P', '2021-01-01', 1);
ON CONFLICT DO NOTHING;
INSERT INTO inschrijvingen VALUES(7499, 'D&P', '2021-01-01', 2);
ON CONFLICT DO NOTHING;
INSERT INTO inschrijvingen VALUES(7521, 'D&P', '2021-01-01', 3);
ON CONFLICT DO NOTHING;