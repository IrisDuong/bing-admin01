package com.bh.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@ConfigurationProperties(prefix = "app")
public class AppProperties {
	private final Auth auth = new Auth();
	private final OAuth2  oauth2  = new OAuth2();
	
	public static class Auth{
		private Map<String, String> token;

		public Map<String, String> getToken() {
			return token;
		}

		public void setToken(Map<String, String> token) {
			this.token = token;
		}
		
	}

	public static final class OAuth2{
		private List<String> authorizedRedirectUris = new ArrayList<>();

		public List<String> getAuthorizedRedirectUris() {
			return authorizedRedirectUris;
		}

		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
		
	}
	public Auth getAuth() {
		return auth;
	}
	public OAuth2 getOauth2() {
		return oauth2;
	}
	
}
