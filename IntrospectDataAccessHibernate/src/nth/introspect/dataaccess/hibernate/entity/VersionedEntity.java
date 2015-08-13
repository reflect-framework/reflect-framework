package nth.introspect.dataaccess.hibernate.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;

@MappedSuperclass
public class VersionedEntity extends BasicEntity {
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
