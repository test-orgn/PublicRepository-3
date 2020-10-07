-- S7.3.C
--
-- Zou je de query ook heel anders kunnen schrijven om hem te versnellen?

SELECT o.order_id, o.order_date, o.salesperson_person_id AS verkoper, 
DATE_PART('day', AGE(o.expected_delivery_date, o.order_date)) AS levertijd, ol.quantity
FROM orders AS o, order_lines AS ol
WHERE o.order_id = ol.order_id AND ol.quantity > 250 AND o.salesperson_person_id IN
(SELECT sq.salesperson_person_id FROM orders as sq GROUP BY sq.salesperson_person_id 
HAVING AVG(DATE_PART('day', AGE(sq.expected_delivery_date, sq.order_date))) > 1.45);