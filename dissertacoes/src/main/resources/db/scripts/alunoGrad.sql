--liquibase formatted sql

--comment: Criando a estrutura da tabela ALUNOGRAD
CREATE TABLE IF NOT EXISTS `exame`.`AlunoGrad` (
  `alg_alu_matricula` INT(12) NOT NULL COMMENT 'Chave extrangeira matricula do aluno',
  INDEX `alg_alu_matricula_idx` (`alg_alu_matricula` ASC) VISIBLE,
  CONSTRAINT `alg_alu_matricula`
    FOREIGN KEY (`alg_alu_matricula`)
    REFERENCES `exame`.`Aluno` (`alu_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
--rollback DELETE FROM ALUNOGRAD;