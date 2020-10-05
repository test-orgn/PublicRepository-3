-- S7.2.
--
-- 1. Maak de volgende twee query’s:
--    A. Toon uit de order tabel de order met order_id = 73590
--    B. Toon uit de order tabel de order met customer_id = 1028
-- 2. Analyseer met EXPLAIN hoe de query’s uitgevoerd worden en kopieer het explain plan onderaan de opdracht
-- 3. Verklaar de verschillen en schrijf deze op
-- 4. Voeg een index toe, waarmee query B versneld kan worden
-- 5. Analyseer met EXPLAIN en kopieer het explain plan onder de opdracht
-- 6. Verklaar de verschillen en schrijf hieronder op

"Index Scan using pk_sales_orders on orders  (cost=0.29..8.31 rows=1 width=155)"
"  Index Cond: (order_id = 73590)"

"Seq Scan on orders  (cost=0.00..1819.94 rows=107 width=155)"
"  Filter: (customer_id = 1028)"

-- De reden dat de SELECT met order_id VEEL sneller is, is omdat SQL standaard alle primary keys indexeert. Je ziet bij de 1ste explain ook "Index Cond" staan.

CREATE INDEX orders_customer_id ON orders(customer_id);

"Bitmap Heap Scan on orders  (cost=5.12..308.96 rows=107 width=155)"
"  Recheck Cond: (customer_id = 1028)"
"  ->  Bitmap Index Scan on orders_customer_id  (cost=0.00..5.10 rows=107 width=0)"
"        Index Cond: (customer_id = 1028)"

-- Na het maken van de index voor customer_id, is de tijd die nodig was voor de SELECT met bijna 85% versneld!
-- Dit komt omdat nu alle customer_ids geindexeerd zijn, zodat SQL die heel snel op kan halen