-- S2.6. Stagiairs
--
-- Neem één van je collega-studenten aan als stagiair ('STAGIAIR') en
-- voer zijn of haar gegevens in. Kies een personeelnummer boven de 8000.
INSERT INTO medewerkers VALUES(8001, 'Jansen', 'J', 'STAGIAIR', 7839, '2000-01-01', 500.0, NULL, 10);
ON CONFLICT DO NOTHING;