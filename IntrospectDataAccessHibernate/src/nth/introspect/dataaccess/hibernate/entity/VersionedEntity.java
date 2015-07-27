package nth.introspect.dataaccess.hibernate.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import nth.introspect.layer5provider.reflection.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.layer5provider.reflection.info.valuemodel.annotations.VisibleInTable;

@MappedSuperclass
public class VersionedEntity extends BasicEntity {
	public Integer version;

	@VisibleInForm(false)
	@VisibleInTable(false)
	@Version 
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
