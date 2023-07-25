INSERT INTO product_category
            (description,
             name)
VALUES      ( 'new kitchen supplies',
              'kitchen'
              );

INSERT INTO shop.product
            (price, weight,
             product_category_id,
             description,
             image_url,
             name)
VALUES (5, 2, (select id from shop.product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'spoon');

INSERT INTO shop.product
            (price, weight,
             product_category_id,
             description,
             image_url,
             name)
VALUES (5, 2, (select id from shop.product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'knife');

INSERT INTO shop.location(
	city, country, county, name, street_address)
	VALUES ('Timisoara', 'Romania', 'Timis', 'TM', 'Bulevardul Cetatii nr.93');

INSERT INTO shop.location(
	city, country, county, name, street_address)
	VALUES ('Oradea', 'Romania', 'Bihor', 'BH', 'Bulevardul Magheru nr.93');

INSERT INTO shop.location(
	city, country, county, name, street_address)
	VALUES ('Arad', 'Romania', 'Arad', 'AR', 'Bulevardul Aradului nr.93');


INSERT INTO shop.stock(
	quantity, location_id, product_id)
	VALUES (100, (select id from shop.location where name ='TM' limit 1), (select id from shop.product where name ='knife' limit 1));

INSERT INTO shop.stock(
	quantity, location_id, product_id)
	VALUES (10, (select id from shop.location where name ='AR' limit 1), (select id from shop.product where name ='knife' limit 1));

INSERT INTO shop.stock(
	quantity, location_id, product_id)
	VALUES (10, (select id from shop.location where name ='BH' limit 1), (select id from shop.product where name ='knife' limit 1));

