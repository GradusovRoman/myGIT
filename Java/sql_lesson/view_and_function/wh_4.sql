#1. Создать VIEW на основе запросов, которые вы сделали в ДЗ к уроку 3.
USE `personal`;
CREATE  OR REPLACE VIEW `dep_average_salary` AS
    SELECT d.dept_name, AVG(s.salary) AS dep_avg_salary FROM (
        SELECT emp_no, dept_no, to_date FROM dept_manager
        UNION
        SELECT emp_no, dept_no, to_date FROM dept_emp
        ) AS emp_all
    INNER JOIN salaries AS s 
        ON s.emp_no = emp_all.emp_no 
        AND emp_all.to_date > (SELECT MAX(from_date) FROM salaries)
    LEFT JOIN departments AS d ON d.dept_no = emp_all.dept_no
    GROUP BY emp_all.dept_no;

#2. Создать функцию, которая найдет менеджера по имени и фамилии.
USE `personal`;
DROP function IF EXISTS `get_emp_id_by_full_name`;

DELIMITER $$
USE `personal`$$
CREATE FUNCTION `get_emp_id_by_full_name` (first_name VARCHAR(14), last_name VARCHAR(16))
RETURNS INTEGER READS SQL DATA
BEGIN
    DECLARE result INTEGER;
    SET result = NULL;
    IF first_name IS NOT NULL AND last_name IS NOT NULL THEN
    SET result = (
                    SELECT emp.emp_no FROM dept_manager AS dept_m
                    INNER JOIN employees AS emp
                        ON dept_m.emp_no = emp.emp_no
                        AND emp.first_name = first_name
                        AND emp.last_name = last_name
                    );
    END IF;
RETURN result;
END$$

DELIMITER ;

#3. Создать триггер, который при добавлении нового сотрудника будет выплачивать ему вступительный бонус, занося запись об этом в таблицу salary.
DROP TRIGGER IF EXISTS `personal`.`employees_AFTER_INSERT`;

DELIMITER $$
USE `personal`$$
CREATE DEFINER = CURRENT_USER TRIGGER `personal`.`employees_AFTER_INSERT` AFTER INSERT ON `employees` FOR EACH ROW
BEGIN
    INSERT INTO salaries (`emp_no`, `salary`, `from_date`, `to_date`) VALUES (NEW.emp_no, '10000', curdate(), curdate());
END$$
DELIMITER ;