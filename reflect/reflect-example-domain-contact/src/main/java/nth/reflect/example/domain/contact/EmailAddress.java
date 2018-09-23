package nth.reflect.example.domain.contact;

import org.apache.bval.constraints.Email;
import org.apache.bval.constraints.NotEmpty;

public class EmailAddress {
private String eMailAddress;

@NotEmpty
@Email
public String geteMailAddress() {
	return eMailAddress;
}

public void seteMailAddress(String eMailAddress) {
	this.eMailAddress = eMailAddress;
}


}
