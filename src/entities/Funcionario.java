package entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Funcionario {
	private UUID id;
	private String nome;
	private String cpf;
	private String matricula;
	private BigDecimal salario;
	private List<Endereco> endereco;
}
