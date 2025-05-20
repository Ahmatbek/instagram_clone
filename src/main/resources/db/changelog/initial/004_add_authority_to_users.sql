INSERT INTO authority (name) VALUES ('USER'),
                                    ('ADMIN');
UPDATE users u
SET authority_id = (
    SELECT id FROM authority WHERE name = 'USER'
    );
