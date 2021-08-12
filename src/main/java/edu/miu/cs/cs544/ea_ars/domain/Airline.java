package edu.miu.cs.cs544.ea_ars.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@SecondaryTable(name = "AirlineHistory")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String code;

	@NotNull
	private String name;

	@Lob
	@Column(table = "AirlineHistory", length = 2000)
	private String history;

	@JsonManagedReference
	@OneToMany(mappedBy = "airline")
	private Set<Flight> flights = new HashSet<>();

	public Airline(String code, String name, String history) {
		this.code = code;
		this.name = name;
		this.history = history;
	}
}
