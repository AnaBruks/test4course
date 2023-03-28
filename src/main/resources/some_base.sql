INSERT INTO equation (equation_string, correct)
VALUES ('2*x+5=17', true);

INSERT INTO equation (equation_string, correct)
VALUES ('-1.3*5/x=1.2', true);

INSERT INTO equation (equation_string, correct)
VALUES ('3*x+7=12', false);

INSERT INTO equation (equation_string, correct)
VALUES ('2.5+x=7', false);

INSERT INTO equation (equation_string, correct)
VALUES ('4*x-12=0', true);

INSERT INTO equation (equation_string, correct)
VALUES ('x^2+2x+1=0', false);

INSERT INTO root (equation_id, root_value)
VALUES (1, 6);

INSERT INTO root (equation_id, root_value)
VALUES (2, 3.25);

INSERT INTO root (equation_id, root_value)
VALUES (5, 3);
