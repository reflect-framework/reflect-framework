package nth.reflect.infra.hibernate.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;

@MappedSuperclass
public class VersionedEntity extends EntityId {
	public Integer version;

	@Hidden()
	@Version 
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
