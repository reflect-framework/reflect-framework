package nth.introsepect.ui.swing;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.info.ProviderInfo;

public class AboutService {

	@GenericReturnType(ProviderInfo.class)
	public List<ProviderInfo> ports() {
		return Introspect.getInfoProvider().getProviderInfos();
	}
	
	@FormMode(FormModeType.showParameterThenClose)
	public void showMoreDetails(ProviderInfo portInfo) {
		//just displaying the method parameter
	}
}
