package tests.model;

import lombok.Data;

@Data
public class TestConfig {

	private String uri;
	private String loginPage;
	private int siteId;
	private int port;
	private String email;
	private String password;
}
