-- S7.1.
--
-- Je maakt alle opdrachten in de 'sales' database die je hebt aangemaakt en gevuld met
-- de aangeleverde data (zie de opdracht op Canvas).
--
-- Voer het voorbeeld uit wat in de les behandeld is:
-- 1. Voer het volgende EXPLAIN statement uit:
--    EXPLAIN SELECT * FROM order_lines WHERE stock_item_id = 9;
--    Bekijk of je het resultaat begrijpt. Kopieer het explain plan onderaan de opdracht
-- 2. Voeg een index op stock_item_id toe:
--    CREATE INDEX ord_lines_si_id_idx ON order_lines (stock_item_id);
-- 3. Analyseer opnieuw met EXPLAIN hoe de query nu uitgevoerd wordt
--    Kopieer het explain plan onderaan de opdracht
-- 4. Verklaar de verschillen. Schrijf deze hieronder op.

"Gather  (cost=1000.00..6152.47 rows=1012 width=96)"
"  Workers Planned: 2"
"  ->  Parallel Seq Scan on order_lines  (cost=0.00..5051.27 rows=422 width=96)"
"        Filter: (stock_item_id = 9)"

"Bitmap Heap Scan on order_lines  (cost=20.26..2317.67 rows=1012 width=96)"
"  Recheck Cond: (stock_item_id = 9)"
"  ->  Bitmap Index Scan on ord_lines_si_id_idx  (cost=0.00..20.01 rows=1012 width=0)"
"        Index Cond: (stock_item_id = 9)"

-- In dit geval is een index super handig, want je ziet dat bij de 2de explain de "tijd" die SQL nodig had om alles op te halen meer 
-- dan 2 keer zo snel was dan een index-loze SELECT. Toen de index aangemaakt werd, zijn alle stock_item_id columns opgeslagen 
-- als key met als waarde de PK van order_lines. Hierdoor kon SQL veel sneller alle order_lines vinden op basis van de stock_item_id.