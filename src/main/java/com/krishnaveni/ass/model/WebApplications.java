package com.krishnaveni.ass.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@Data
@NoArgsConstructor
@AllArgsConstructor
*/
@Document(collection="webapps")
public class WebApplications {

	@Id
	private int id;
	@NotNull(message="Please provide the App name")
	private String appName;
	@NotNull(message="Please provide the App Developer field")
	private String appDeveloper;
	@Max(value=4, message="The value should contain 4 digits only")
	private int introYear;
	@NotNull(message="Please mention the Programming Language")
	private String pl; // programming language
	@NotNull(message="Please provide the App Type")
	private String appType;
	
	public WebApplications() {
		super();
	}

	public WebApplications(int id, String appName, String appDeveloper, int introYear, String pl, String appType) {
		super();
		this.id = id;
		this.appName = appName;
		this.appDeveloper = appDeveloper;
		this.introYear = introYear;
		this.pl = pl;
		this.appType = appType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setApp_name(String appName) {
		this.appName = appName;
	}

	public String getAppDeveloper() {
		return appDeveloper;
	}

	public void setAppDeveloper(String appDeveloper) {
		this.appDeveloper = appDeveloper;
	}

	public int getIntroYear() {
		return introYear;
	}

	public void setIntroYear(int introYear) {
		this.introYear = introYear;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Override
	public String toString() {
		return "WebApplications [id=" + id + ", app_name=" + appName + ", app_developer=" + appDeveloper
				+ ", intro_year=" + introYear + ", pl=" + pl + ", app_type=" + appType + "]";
	}
	
	
}
