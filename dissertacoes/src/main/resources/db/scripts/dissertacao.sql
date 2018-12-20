--liquibase formatted sql

--comment: Criando a estrutura da tabela DISSERTACAO
CREATE TABLE IF NOT EXISTS `exame`.`Dissertacao` (
  `id` INT(12) NOT NULL COMMENT 'Identificação da dissertaÃ§Ã£o',
  `tipo` VARCHAR(45) NULL COMMENT 'Tipo de dissertaÃ§Ã£o',
  `ano` DATE NULL COMMENT 'Ano da DissertaÃ§Ã£o',
  `titulo` VARCHAR(45) NULL COMMENT 'TÃ­tulo da DissertaÃ§Ã£o',
  `dir_alp_alu_matricula` INT(12) NULL COMMENT 'Chave extrangeira da matricula do aluno',
  `dir_pro_matricula` INT(12) NULL COMMENT 'Chave extrangeira da matrÃ­cula do professor',
  PRIMARY KEY (`id`),
  INDEX `dir_alp_alu_matricula_idx` (`dir_alp_alu_matricula` ASC) VISIBLE,
  INDEX `dir_pro_matricula_idx` (`dir_pro_matricula` ASC) VISIBLE,
  CONSTRAINT `dir_alp_alu_matricula`
    FOREIGN KEY (`dir_alp_alu_matricula`)
    REFERENCES `exame`.`AlunoPos` (`alp_alu_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dir_pro_matricula`
    FOREIGN KEY (`dir_pro_matricula`)
    REFERENCES `exame`.`Professor` (`pro_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
--rollback DELETE FROM DISSERTACAO;