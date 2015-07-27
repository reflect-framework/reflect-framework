package nth.introspect.dataaccess.hibernate.entity;

import javax.persistence.MappedSuperclass;

import nth.introspect.layer5provider.domain.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.layer5provider.domain.info.valuemodel.annotations.VisibleInTable;

@MappedSuperclass
public class DeletableEntity extends VersionedEntity {
	private boolean deleted;

	@VisibleInForm(false)
	@VisibleInTable(false)
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
