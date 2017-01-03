package com.renqiang.movie.dao;

import org.apache.ibatis.annotations.Insert;

import com.renqiang.movie.model.Movie;

/**
 * 
 * @author renqiang
 *
 */
public interface MovieDao {
	
	@Insert("insert into yangguang_movie (`title`,`ftp_url`,`description`,`thunder_pid`) values(#{title},#{ftp_url},#{description},#{thunder_pid})")
	public void add(Movie movie);
}
