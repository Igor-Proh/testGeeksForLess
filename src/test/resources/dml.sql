SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE equation RESTART IDENTITY;
TRUNCATE TABLE root RESTART IDENTITY;
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO equation(equation_value) VALUES ('1+x=1');
INSERT INTO equation(equation_value) VALUES ('1+x=2');
INSERT INTO equation(equation_value) VALUES ('1+x=3');
INSERT INTO equation(equation_value) VALUES ('1+x=4');
INSERT INTO equation(equation_value) VALUES ('1+x=5');

INSERT INTO root(equation_id, root_value) VALUES (1, '0');