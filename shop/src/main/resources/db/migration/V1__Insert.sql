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

