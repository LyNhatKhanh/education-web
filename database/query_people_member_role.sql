use `web-education`;

SELECT p.first_name, p.last_name, p.id, m.user_id, m.pw
FROM `web-education`.people as p
INNER JOIN `web-education`.members as m on m.people_id=p.id
INNER JOIN `web-education`.roles as r on r.user_id = m.user_id