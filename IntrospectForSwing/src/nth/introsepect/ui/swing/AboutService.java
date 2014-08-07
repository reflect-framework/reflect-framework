package nth.introsepect.ui.swing;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.version.ProviderInfo;

public class AboutService {

	@GenericReturnType(ProviderInfo.class)
	public List<ProviderInfo> ports() {
		return Introspect.getVersionProvider().getProviderInfos();
	}
	
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public ProviderInfo showMoreDetails(ProviderInfo portInfo) {
		return portInfo;
	}
}
