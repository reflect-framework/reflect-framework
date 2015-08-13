package nth.introspect.dataaccess.hibernate.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;

@MappedSuperclass
public abstract class BasicEntity {
	private int id;// TODO to superclass

	@Hidden()
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
