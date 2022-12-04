-- book_management_api.book definition

CREATE TABLE `book` (
  `id` bigint NOT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKamuv2talo6ag1h7eokosx9hp6` (`author_id`),
  CONSTRAINT `FKamuv2talo6ag1h7eokosx9hp6` FOREIGN KEY (`author_id`) REFERENCES `userr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- book_management_api.book_seq definition

CREATE TABLE `book_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- book_management_api.comment definition

CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `points` int NOT NULL,
  `author_id` bigint DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcs0ry5eur3asns67j6555b2g0` (`author_id`),
  KEY `FKkko96rdq8d82wm91vh2jsfak7` (`book_id`),
  CONSTRAINT `FKcs0ry5eur3asns67j6555b2g0` FOREIGN KEY (`author_id`) REFERENCES `userr` (`id`),
  CONSTRAINT `FKkko96rdq8d82wm91vh2jsfak7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- book_management_api.comment_seq definition

CREATE TABLE `comment_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- book_management_api.userr definition

CREATE TABLE `userr` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- book_management_api.userr_seq definition

CREATE TABLE `userr_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;