CREATE TRIGGER `create_date` BEFORE INSERT ON `subscriptions`
FOR EACH ROW BEGIN
   set new.sb_start = now();
   set new.sb_finish =DATE_ADD(NOW(), INTERVAL 30 DAY);
END;