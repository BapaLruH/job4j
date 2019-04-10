//1. Написать запрос получение всех продуктов с типом "СЫР"
select product.*, tp.name from product join type tp on product.type_id = tp.id where tp.name = 'СЫР';

//2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%мороженое%';

//3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце
select * from product where expired_data between date_trunc('month', current_date) + interval '1 month' and date_trunc('month', current_date) + interval '2 month' - interval '1 day';

//4. Написать запрос, который выводит самый дорогой продукт
select * from product order by price desc limit 1;

//5. Написать запрос, который выводит количество всех продуктов определенного типа
select count(pr.name) as Amount, tp.name from product pr join type tp on pr.type_id = tp.id group by tp.name;

//6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id in (select id from type where tp.name = 'СЫР' or tp.name = 'МОЛОКО');

//7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук
select count (product.name), tp.name from product join type tp on product.type_id = tp.id group by tp.name having count (product.name) < 10;

//8. Вывести все продукты и их тип
select product.*, tp.name from product join type tp on product.type_id = tp.id;