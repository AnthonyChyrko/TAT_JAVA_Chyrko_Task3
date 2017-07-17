CREATE TRIGGER `book_availability` BEFORE UPDATE ON `books`
FOR EACH ROW BEGIN	
	IF NEW.b_quantity <= 0 THEN
	SET NEW.b_available = 'N';
	ELSEIF NEW.b_quantity > 0 THEN
	SET NEW.b_available = 'Y';
	END IF;
END;
