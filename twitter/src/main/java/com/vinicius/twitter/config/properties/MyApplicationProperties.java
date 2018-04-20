package com.vinicius.twitter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.vinicius.twitter")
public class MyApplicationProperties
{

	private String applicationName;
	private Info info;

	public String getApplicationName()
	{
		return applicationName;
	}

	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public Info getInfo()
	{

		return info;
	}

	public void setInfo(Info info)
	{

		this.info = info;
	}

	/**
	 * Info class
	 */
	public static class Info
	{

		private String projectName;

		private String version;

		private String timestamp;

		public String getProjectName()
		{

			return projectName;
		}

		public void setProjectName(String projectName)
		{

			this.projectName = projectName;
		}

		public String getVersion()
		{

			return version;
		}

		public void setVersion(String version)
		{

			this.version = version;
		}

		public String getTimestamp()
		{

			return timestamp;
		}

		public void setTimestamp(String timestamp)
		{

			this.timestamp = timestamp;
		}
	}
}
