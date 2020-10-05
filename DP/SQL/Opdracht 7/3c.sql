-- S7.3.C
--
-- Zou je de query ook heel anders kunnen schrijven om hem te versnellen?

-- Je zou een JOIN kunnen gebruiken in plaats van een subquery voor het berekenen van de gemiddelde levertijd. Dit was maar een paar honderd milliseconden 
-- sneller. Toen heb ik het geprobeerd met GROUP BY en HAVING, alleen daar kreeg ik maar 275 rijen terug (ipv 377).

SELECT o.order_id, o.order_date, o.salesperson_person_id AS verkoper, 
DATE_PART('day', AGE(o.expected_delivery_date, o.order_date)) AS levertijd, ol.quantity
FROM orders AS o
JOIN order_lines AS ol ON o.order_id = ol.order_id
WHERE ol.quantity > 250
GROUP BY o.order_id, o.order_date, verkoper, levertijd, ol.quantity
HAVING AVG(DATE_PART('day', AGE(expected_delivery_date, order_date))) > 1.45;