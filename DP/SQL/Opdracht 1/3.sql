-- S1.3. Verbetering op afdelingentabel
--
-- We gaan een aantal verbeteringen doorvoeren aan de tabel `afdelingen`:
--   a) Maak een sequence die afdelingsnummers genereert. Denk aan de beperking
--      dat afdelingsnummers veelvouden van 10 zijn.
--   b) Voeg een aantal afdelingen toe aan de tabel, maak daarbij gebruik van
--      de nieuwe sequence.
--   c) Op enig moment gaat het mis. De betreffende kolommen zijn te klein voor
--      nummers van 3 cijfers. Los dit probleem op.

CREATE SEQUENCE afdelingen_anr_sequence START 10 INCREMENT 10;

-- ERROR:  numeric field overflow

ALTER TABLE afdelingen ALTER COLUMN anr TYPE NUMERIC(3)

-- ERROR:  cannot alter type of a column used by a view or rule
-- DETAIL:  rule _RETURN on view s1_2_test depends on column "anr"
-- SQL state: 0A000

DROP SEQUENCE afdelingen_anr_sequence;
CREATE SEQUENCE afdelingen_anr_sequence START 10 INCREMENT 10 MAXVALUE 90;

-- ERROR:  nextval: reached maximum value of sequence "afdelingen_anr_sequence" (90)
-- SQL state: 2200H