INSERT INTO users (id, username, password_hash, created_at)
VALUES
('c1a7f0d2-6c3e-4b91-9f3a-2d9a6a7b8c11', 'andi', '$2a$10$aYPotzkbJm5OnQO/dFKTuewjy/E33MUnGl6fYlQbXpXghVsmHCvqa', NOW()),
('f3b92e91-8c5d-4d8e-9a77-1a2c3b4d5e66', 'budi', '$2a$10$aYPotzkbJm5OnQO/dFKTuewjy/E33MUnGl6fYlQbXpXghVsmHCvqa', NOW());

-- ACCOUNTS
INSERT INTO accounts (id, user_id, account_number, balance, status, created_at)
VALUES
('9a1c2b3d-4e5f-6789-aaaa-bbccddeeff01', 'c1a7f0d2-6c3e-4b91-9f3a-2d9a6a7b8c11', '10001', 2000000.00, 'ACTIVE', NOW()),
('7b2d3c4e-5f6a-7890-bbbb-ccddeeff0022', 'f3b92e91-8c5d-4d8e-9a77-1a2c3b4d5e66', '10002', 1500000.00, 'ACTIVE', NOW());