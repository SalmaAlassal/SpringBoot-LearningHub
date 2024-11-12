-- Insert sample data into the Student table
INSERT INTO student (first_name, last_name, email) VALUES
('John', 'Doe', 'john.doe@example.com'),
('Jane', 'Smith', 'jane.smith@example.com'),
('Alice', 'Johnson', 'alice.johnson@example.com');

-- Insert sample data into the Course table
INSERT INTO course (name, description) VALUES
('Math', 'Introduction to Algebra'),
('Science', 'Basics of Science'),
('History', 'World History Overview');

-- Insert enrollment data to link students and courses
INSERT INTO enrollment (student_id, course_id) VALUES
(1, 1), (1, 2),   -- John Doe enrolled in Math and Science
(2, 1),           -- Jane Smith enrolled in Math
(3, 3);           -- Alice Johnson enrolled in History
