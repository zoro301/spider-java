package com.renqiang.movie;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.renqiang.movie.dao.MovieDao;
import com.renqiang.movie.model.Movie;
import com.renqiang.movie.output.MoviePersistentPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


/**
 * 
 * @author renqiang
 *
 */
@Component
public class YangguangMoviePageProcessor implements PageProcessor
{
//	@Qualifier("MoviePersistentPipeline")
//  @Autowired
//	private MoviePersistentPipeline moviePipeline;
	
	private static MovieDao movieDao;
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		List<String> resultUrlList = page.getHtml().xpath("//select[@name='sldd']/option[@value]").css("option","value").all();
		List<String> urlList = new ArrayList<String>();
		for(String url: resultUrlList){
			url = "http://www.ygdy8.com/html/gndy/dyzz/"+ url;
			urlList.add(url);
		}
		
		page.addTargetRequests(urlList);
		List<String> moviePartUrlList = page.getHtml().xpath("//table[@class='tbspan']/tbody/tr/td/b/a/@href").all();
		List<String> movieUrlList = new ArrayList<String>();
		for(String url: moviePartUrlList){
			movieUrlList.add(url);
		}
		page.addTargetRequests(movieUrlList);
		
		String movieName = page.getHtml().xpath("//h1/font/text()").toString();
		String movieFtpUrl = page.getHtml().xpath("//td[@style='WORD-WRAP: break-word']/a/text()").toString();
		String movieDescription = page.getHtml().xpath("//div[@id='Zoom']/span/p/allText()").toString();
		if(!StringUtils.isEmpty(movieFtpUrl)){
			String[] splitUrls = page.getUrl().toString().split("\\.html")[0].split("/");
			String movie_pid = splitUrls[splitUrls.length-1];
			Movie movie = new Movie();
			movie.setTitle(movieName);
			movie.setFtp_url(movieFtpUrl);
			movie.setDescription(movieDescription);
			movie.setThunder_pid(movie_pid);
			page.putField("movie", movie);
		}
	}
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final YangguangMoviePageProcessor pageProcessor = applicationContext.getBean(YangguangMoviePageProcessor.class);
        //在myBatis中已配置MapperScannerConfigurer，但movieDao没有被自动装配，why?
        movieDao = applicationContext.getBean(MovieDao.class);
        pageProcessor.crawl();
	}

	private void crawl() {
		Spider.create(new YangguangMoviePageProcessor())
		.addUrl("http://www.ygdy8.com/html/gndy/dyzz/index.html")
		.addPipeline(new MoviePersistentPipeline(movieDao))
		.thread(5)
		.run();
	}
}
