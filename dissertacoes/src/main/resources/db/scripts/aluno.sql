--liquibase formatted sql

--comment: Criando a estrutura da tabela ALUNO
CREATE TABLE IF NOT EXISTS `exame`.`Aluno` (
  `alu_matricula` INT(12) NOT NULL COMMENT 'MatrÃ­cula do Aluno',
  `alu_nome` VARCHAR(45) NOT NULL COMMENT 'Nome completo do aluno',
  PRIMARY KEY (`alu_matricula`),
  UNIQUE INDEX `matricula_UNIQUE` (`alu_matricula` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`alu_nome` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
--rollback DELETE FROM ALUNO;