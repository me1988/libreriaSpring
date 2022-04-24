package es.seresco.libreriaspring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="AUTOR",schema="test")
@Getter @Setter @NoArgsConstructor @ToString
public class Autor implements Serializable{

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CODIGO", nullable=false, unique=true, length=15)
	private String codigo;
	
	@Column(name="NOMBRE",   length=25)
	private String nombre;
	
	@Column(name="APELLIDOS",   length=25)
	private String apellidos;
	
	@Column(name="NACIONALIDAD",   length=25)
	private String nacinalidad;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
