package com.renqiang.movie.model;

/**
 * 数据模型
 * @author renqiang
 *
 */
public class Movie {

	private String title;
	private String description;
	private String ftp_url;
	private String thunder_pid;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFtp_url() {
		return ftp_url;
	}
	public void setFtp_url(String ftp_url) {
		this.ftp_url = ftp_url;
	}
	public String getThunder_pid() {
		return thunder_pid;
	}
	public void setThunder_pid(String thunder_pid) {
		this.thunder_pid = thunder_pid;
	}
	
}
