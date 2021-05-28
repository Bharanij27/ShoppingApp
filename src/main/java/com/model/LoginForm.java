package com.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginForm {
	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 50)
	private String uname;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 50)
	private String upass;

	public final String getUname() {
		return uname;
	}

	public final void setUname(String uname) {
		this.uname = uname;
	}

	public final String getUpass() {
		return upass;
	}

	public final void setUpass(String upass) {
		this.upass = upass;
	}
}
