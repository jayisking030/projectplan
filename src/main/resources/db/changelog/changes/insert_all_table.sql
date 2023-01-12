INSERT INTO project_plan (name)VALUES
('Project Plan 1'),
('Project Plan 2'),
('Project Plan 3'),
('Project Plan 4'),
('Project Plan 5');


INSERT INTO task (name, start_date, end_date, status, project_plan_id)VALUES
('Design Project Plan 1','2023-01-11', '2023-01-18', 'Complete', 1),
('Coding (Implem  1)','2023-01-01', '2023-01-11', 'Complete', 1),
('Coding (Implem  2)','2023-01-02', '2023-01-11', 'Complete', 1),
('Coding (Implem  3)','2023-01-08', '2023-01-16', 'In Progress', 1),
('Coding (Implem  4)','2023-01-07', '2023-01-16', 'In Progress', 1),
('Coding (Implem  5)','2023-01-19', '2023-01-18', 'In Progress', 1),
('Coding (Implem  6)','2023-01-01', '2023-01-11', 'Complete', 1),
('Testing (Module  1)','2023-01-11', '2023-01-18', 'In Progress', 1),
('Testing (Module  2)','2023-01-11', '2023-01-18', 'In Progress', 1),
('Testing (Module  3)','2023-01-11', '2023-01-18', 'In Progress', 1);




INSERT INTO TASK_DEPENDENCY(TASK_ID ,DEPENDENCY_ID )  VALUES
(8, 1),
(8, 2),
(8, 3),
(8, 4),
(9, 5),
(9, 6),
(9, 4),
(10,7);
