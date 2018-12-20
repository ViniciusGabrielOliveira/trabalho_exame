--liquibase formatted sql

--comment: Criando a estrutura da tabela ALUNOPOS
CREATE TABLE IF NOT EXISTS `exame`.`AlunoPos` (
  `alp_alu_matricula` INT(12) NOT NULL COMMENT 'Chave extrangeira da matrÃ­cula do Aluno',
  `alp_agenciaBolsa` VARCHAR(45) NULL COMMENT 'NÃºcleo que gerencia a bolsa de estudos do aluno',
  INDEX `alp_alu_matricula_idx` (`alp_alu_matricula` ASC) VISIBLE,
  CONSTRAINT `alp_alu_matricula`
    FOREIGN KEY (`alp_alu_matricula`)
    REFERENCES `exame`.`Aluno` (`alu_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
--rollback DELETE FROM ALUNOPOS;