--liquibase formatted sql

--comment: Criando a estrutura da tabela PROFESSOR
CREATE TABLE IF NOT EXISTS `exame`.`Professor` (
  `pro_matricula` INT(12) NOT NULL COMMENT 'MatrÃ­cula do Professor',
  `pro_nome` VARCHAR(45) NULL COMMENT 'Nome do professor',
  PRIMARY KEY (`pro_matricula`),
  UNIQUE INDEX `matricula_UNIQUE` (`pro_matricula` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`pro_nome` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
--rollback DELETE FROM PROFESSOR;