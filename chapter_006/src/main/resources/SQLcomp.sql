-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
SELECT person.name as person, company.name as company
FROM person
         inner join company on person.company_id = company.id
WHERE company.id <> 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT company.name, count(person.name)
FROM company
         inner join person on company.id = person.company_id
group by company.name
having count(person.name) in
       (SELECT count(name) from person group by company_id order by count(name) desc limit 1)