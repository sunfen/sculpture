CREATE TABLE `document` (
	`id` BIGINT(20) NOT NULL,
	`min_path` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`object_id` BIGINT(20) NULL DEFAULT NULL,
	`path_name` VARCHAR(255) NULL DEFAULT NULL,
	`suffix` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)COLLATE='utf8_general_ci' ENGINE=InnoDB;
