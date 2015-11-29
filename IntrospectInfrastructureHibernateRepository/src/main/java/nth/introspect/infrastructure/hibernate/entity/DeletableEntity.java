package nth.introspect.infrastructure.hibernate.entity;

import javax.persistence.MappedSuperclass;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;

@MappedSuperclass
public class DeletableEntity extends VersionedEntity {
	private boolean deleted;

	@Hidden()
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
