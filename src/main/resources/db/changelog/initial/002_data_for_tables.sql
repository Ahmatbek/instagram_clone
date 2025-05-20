-- USERS (10)
INSERT INTO users (username, password, avatar, user_info) VALUES
('alice', 'hashed_pw_1', 'alice.jpg', 'Photographer'),
('bob', 'hashed_pw_2', 'bob.png', 'Techie'),
('carol', 'hashed_pw_3', 'carol.jpg', 'Chef'),
('dave', 'hashed_pw_4', 'dave.png', 'Traveler'),
('eve', 'hashed_pw_5', 'eve.jpg', 'Dancer'),
('frank', 'hashed_pw_6', 'frank.jpg', 'Musician'),
('grace', 'hashed_pw_7', 'grace.png', 'Writer'),
('heidi', 'hashed_pw_8', 'heidi.jpg', 'Artist'),
('ivan', 'hashed_pw_9', 'ivan.png', 'Developer'),
('judy', 'hashed_pw_10', 'judy.jpg', 'Reader');

-- FOLLOWERS (10)
INSERT INTO followers (follower_id, receiver_id) VALUES
((SELECT id FROM users WHERE username='bob'), (SELECT id FROM users WHERE username='alice')),
((SELECT id FROM users WHERE username='carol'), (SELECT id FROM users WHERE username='bob')),
((SELECT id FROM users WHERE username='dave'), (SELECT id FROM users WHERE username='carol')),
((SELECT id FROM users WHERE username='eve'), (SELECT id FROM users WHERE username='dave')),
((SELECT id FROM users WHERE username='frank'), (SELECT id FROM users WHERE username='eve')),
((SELECT id FROM users WHERE username='grace'), (SELECT id FROM users WHERE username='frank')),
((SELECT id FROM users WHERE username='heidi'), (SELECT id FROM users WHERE username='grace')),
((SELECT id FROM users WHERE username='ivan'), (SELECT id FROM users WHERE username='heidi')),
((SELECT id FROM users WHERE username='judy'), (SELECT id FROM users WHERE username='ivan')),
((SELECT id FROM users WHERE username='alice'), (SELECT id FROM users WHERE username='judy'));

-- POSTS (10)
INSERT INTO posts (user_id, description, photo) VALUES
((SELECT id FROM users WHERE username='alice'), 'Bali sunset', 'bali.jpg'),           -- id 1
((SELECT id FROM users WHERE username='bob'), 'Unboxing laptop', 'laptop.jpg'),       -- id 2
((SELECT id FROM users WHERE username='carol'), 'Pasta recipe', 'pasta.jpg'),         -- id 3
((SELECT id FROM users WHERE username='dave'), 'Mountain hiking', 'mountain.jpg'),    -- id 4
((SELECT id FROM users WHERE username='eve'), 'Dance video', 'dance.jpg'),            -- id 5
((SELECT id FROM users WHERE username='frank'), 'New guitar', 'guitar.jpg'),          -- id 6
((SELECT id FROM users WHERE username='grace'), 'Poem of the day', 'poem.jpg'),       -- id 7
((SELECT id FROM users WHERE username='heidi'), 'Portrait sketch', 'sketch.jpg'),     -- id 8
((SELECT id FROM users WHERE username='ivan'), 'Code snippets', 'code.jpg'),          -- id 9
((SELECT id FROM users WHERE username='judy'), 'Book review', 'book.jpg');            -- id 10

-- LIKES (10)
INSERT INTO likes (post_id, user_id) VALUES
(1, (SELECT id FROM users WHERE username='bob')),
(1, (SELECT id FROM users WHERE username='carol')),
(2, (SELECT id FROM users WHERE username='alice')),
(3, (SELECT id FROM users WHERE username='dave')),
(4, (SELECT id FROM users WHERE username='eve')),
(5, (SELECT id FROM users WHERE username='frank')),
(6, (SELECT id FROM users WHERE username='grace')),
(7, (SELECT id FROM users WHERE username='heidi')),
(8, (SELECT id FROM users WHERE username='ivan')),
(9, (SELECT id FROM users WHERE username='judy'));

-- COMMENTS (10)
INSERT INTO comments (post_id, message, writer_id) VALUES
(1, 'Amazing shot!', (SELECT id FROM users WHERE username='bob')),
(1, 'Wow!', (SELECT id FROM users WHERE username='carol')),
(2, 'Cool device!', (SELECT id FROM users WHERE username='alice')),
(3, 'Yummy!', (SELECT id FROM users WHERE username='dave')),
(4, 'Nature is beautiful!', (SELECT id FROM users WHERE username='eve')),
(5, 'Incredible move!', (SELECT id FROM users WHERE username='frank')),
(6, 'I love that guitar!', (SELECT id FROM users WHERE username='grace')),
(7, 'Great lines.', (SELECT id FROM users WHERE username='heidi')),
(8, 'Nice shading!', (SELECT id FROM users WHERE username='ivan')),
(9, 'Clean code!', (SELECT id FROM users WHERE username='judy'));
