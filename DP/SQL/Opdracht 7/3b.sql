-- S7.3.B
--
-- 1. Vraag het EXPLAIN plan op van je query (kopieer hier, onder de opdracht)
-- 2. Kijk of je met 1 of meer indexen de query zou kunnen versnellen
-- 3. Maak de index(en) aan en run nogmaals het EXPLAIN plan (kopieer weer onder de opdracht) 
-- 4. Wat voor verschillen zie je? Verklaar hieronder.

"Nested Loop  (cost=0.29..1897509.18 rows=329 width=24)"
"  ->  Seq Scan on order_lines ol  (cost=0.00..6738.65 rows=987 width=8)"
"        Filter: (quantity > 250)"
"  ->  Index Scan using pk_sales_orders on orders o  (cost=0.29..1915.67 rows=1 width=16)"
"        Index Cond: (order_id = ol.order_id)"
"        Filter: ((SubPlan 1) > '1.45'::double precision)"
"        SubPlan 1"
"          ->  Aggregate  (cost=1911.94..1911.95 rows=1 width=8)"
"                ->  Seq Scan on orders sq  (cost=0.00..1819.94 rows=7360 width=8)"
"                      Filter: (salesperson_person_id = o.salesperson_person_id)"

CREATE INDEX order_lines_quantity ON order_lines(quantity);
CREATE INDEX orders_salesperson_person_id ON orders(salesperson_person_id);

"Nested Loop  (cost=20.36..1215371.68 rows=329 width=24)"
"  ->  Bitmap Heap Scan on order_lines ol  (cost=20.07..2280.34 rows=987 width=8)"
"        Recheck Cond: (quantity > 250)"
"        ->  Bitmap Index Scan on order_lines_quantity  (cost=0.00..19.82 rows=987 width=0)"
"              Index Cond: (quantity > 250)"
"  ->  Index Scan using pk_sales_orders on orders o  (cost=0.29..1229.07 rows=1 width=16)"
"        Index Cond: (order_id = ol.order_id)"
"        Filter: ((SubPlan 1) > '1.45'::double precision)"
"        SubPlan 1"
"          ->  Aggregate  (cost=1225.34..1225.35 rows=1 width=8)"
"                ->  Bitmap Heap Scan on orders sq  (cost=141.33..1133.33 rows=7360 width=8)"
"                      Recheck Cond: (salesperson_person_id = o.salesperson_person_id)"
"                      ->  Bitmap Index Scan on orders_salesperson_person_id  (cost=0.00..139.49 rows=7360 width=0)"
"                            Index Cond: (salesperson_person_id = o.salesperson_person_id)"

-- Ik heb ervoor gekozen om een index te maken voor quantity en salesperson_person_id, omdat de tijden daarvan zeer hoog waren. Na het maken van de indexen
-- is de tijd van quantity met 77% versneld en de tijd van salesperson_person_id met 38%!