CREATE TABLE `TAGS`(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `TAG` VARCHAR(100) NOT NULL,
  UNIQUE (`TAG`)
) ENGINE = InnoDB;

CREATE TABLE DEVELOPERS(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `RATING` DECIMAL(2, 1) DEFAULT 0,
  `SKILL_LEVEL` TINYINT(2) NOT NULL,
  `USER_ID` INT NOT NULL COMMENT '',
  INDEX `fk_DEVELOPERS_TO_USERS_idx` (`USER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_DEVELOPERS_TO_USERS`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE CLIENTS(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `RATING` DECIMAL(2, 1) DEFAULT 0,
  `USER_ID` INT NOT NULL COMMENT '',
  INDEX `fk_CLIENTS_TO_USERS_idx` (`USER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_CLIENTS_TO_USERS`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE PROJECTS(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `DEVELOPER_ID` INT NOT NULL COMMENT '',
  `CLIENT_ID` INT NOT NULL COMMENT '',
  `STATUS` VARCHAR(100) NOT NULL,
  `MIN_SKILL_LEVEL` TINYINT(2) NOT NULL,
  `NAME` VARCHAR(250) NOT NULL,
  `DEVELOPER_RATING` DECIMAL(2, 1) DEFAULT 0,
  `CLIENT_RATING` DECIMAL(2, 1) DEFAULT 0,
  INDEX `fk_PROJECTS_TO_CLIENTS_idx` (`CLIENT_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_PROJECTS_TO_CLIENTS`
  FOREIGN KEY (`CLIENT_ID`)
  REFERENCES `CLIENTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_PROJECTS_TO_DEVELOPERS_idx` (`DEVELOPER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_PROJECTS_TO_DEVELOPERS`
  FOREIGN KEY (`DEVELOPER_ID`)
  REFERENCES `DEVELOPERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE TAGS_TO_DEVELOPERS(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `DEVELOPER_ID` INT NOT NULL COMMENT '',
  `TAG_ID` INT NOT NULL COMMENT '',
  INDEX `fk_TAGS_TO_TAGS_TO_DEVELOPERS_DEVELOPER_idx` (`DEVELOPER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_TAGS_TO_TAGS_TO_DEVELOPERS_DEVELOPER`
  FOREIGN KEY (`DEVELOPER_ID`)
  REFERENCES `DEVELOPERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_TAGS_TO_TAGS_TO_DEVELOPERS_TAG_idx` (`TAG_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_TAGS_TO_TAGS_TO_DEVELOPERS_TAG`
  FOREIGN KEY (`TAG_ID`)
  REFERENCES `TAGS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE (`DEVELOPER_ID`, `TAG_ID`)
) ENGINE = InnoDB;

CREATE TABLE TAGS_TO_PROJECTS(
  `ID` INT PRIMARY KEY AUTO_INCREMENT,
  `PROJECT_ID` INT NOT NULL COMMENT '',
  `TAG_ID` INT NOT NULL COMMENT '',
  INDEX `fk_TAGS_TO_TAGS_TO_PROJECTS_PROJECT_idx` (`PROJECT_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_TAGS_TO_TAGS_TO_PROJECTS_PROJECT`
  FOREIGN KEY (`PROJECT_ID`)
  REFERENCES `PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_TAGS_TO_TAGS_TO_PROJECTS_TAG_idx` (`TAG_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_TAGS_TO_TAGS_TO_PROJECTS_TAG`
  FOREIGN KEY (`TAG_ID`)
  REFERENCES `TAGS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE (`PROJECT_ID`, `TAG_ID`)
) ENGINE = InnoDB;