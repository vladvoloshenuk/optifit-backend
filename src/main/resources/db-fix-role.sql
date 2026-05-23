-- Виконайте в pgAdmin / DBeaver на базі optifit_db

-- 1. Перевірка колонок
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_name = 'users';

-- 2. Якщо колонки role немає — додати
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS role VARCHAR(20) NOT NULL DEFAULT 'USER';

-- 3. Якщо role є, але NULL у старих записах
UPDATE users SET role = 'USER' WHERE role IS NULL;

-- 4. Перевірка даних
SELECT id, username, email, role FROM users;
