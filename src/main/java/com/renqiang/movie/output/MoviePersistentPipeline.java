package com.renqiang.movie.output;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renqiang.movie.dao.MovieDao;
import com.renqiang.movie.model.Movie;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MoviePersistentPipeline implements Pipeline {
	
	@Resource
	private MovieDao movieDao;
	
	public MoviePersistentPipeline(MovieDao movieDao){
		this.movieDao = movieDao;
	}

	public void process(ResultItems resultItems, Task task) {
		for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet()){
			Movie movie = (Movie)entry.getValue();
			movieDao.add(movie);
		}
		
		
	}

}
