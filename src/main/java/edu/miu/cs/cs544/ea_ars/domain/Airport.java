package edu.miu.cs.cs544.ea_ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @NoArgsConstructor
@Getter
@Setter @ToString
//@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"airportCode"})})
public class Airport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 3, nullable = false)
	private String airportCode;

	private String name;

	@Embedded
	private Address address;

	public Airport(String airportCode, String name, Address address) {
		this.airportCode = airportCode;
		this.name = name;
		this.address = address;
	}
}
