CREATE TRIGGER `add_b_quantity` AFTER DELETE ON `subscriptions`
FOR EACH ROW BEGIN	
	SET @b_count = (SELECT b_quantity FROM books WHERE b_id = OLD.b_id);
	UPDATE `books` SET `b_quantity` =  (@b_count+1) WHERE `b_id` = OLD.b_id;
END;
