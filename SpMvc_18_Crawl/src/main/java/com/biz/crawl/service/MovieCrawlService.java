package com.biz.crawl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.biz.crawl.domain.NaverMovieVO;
import com.biz.crawl.persistence.NaverDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieCrawlService {
	
	/*
	 * 네이버 현재 상영작 리스트에서 영화 제목, 이미지, 순위를 가져오기 위해
	 * URL,
	 * 제목이 들어있는 html tag,
	 * 이미지가 들어있는 html tag,
	 * 순위를 가져오기 위한 html tag 묶음 정보를 변수로 선언 
	 */
	private final String naverMovieURL = "https://movie.naver.com/movie/running/current.nhn"; // 긁어올 주소
	private final String mTitleTag = "dt.tit a"; // dt의 tit클래스의 a태그. 브라우저에서 f12 소스로 필요한 자료가 있는 부분을 정확히 체크
	private final String mImageTag = "div.thumb a img"; // div thumb클래스의 a태그의 img 태그를 지정
	private final String rankListTag = "dl.lst_dsc";
	
	private final NaverDao nDao;
	
	public List<NaverMovieVO> selectAll() { // DB에서 가져온 리스트. 컨트롤러에서 사용
		return nDao.selectAll();
	}
	
	@Scheduled(fixedDelay = 100000) // 서버가 시작한 뒤 100초마다(100000ms) 실행. 
	public void naverMovieGet() {
		List<NaverMovieVO> naverList = this.movieRankGet(); // 크롤링해오고
		nDao.deleteAll(); // DB에 있는 데이터 다 지우고
		for(NaverMovieVO vo : naverList) {
			nDao.insert(vo); // 크롤링해온 데이터를 DB에 insert하기
		}
	}
	
	
	public List<NaverMovieVO> movieRankGet() {
		// URL에 해당하는 html 페이지 코드를 가져오기
		// Document라는 jsoup의 클래스에 담기
		// DOM 형식의 document를 만든다
		Document naverMovieDoc = null;
		try {
			naverMovieDoc = Jsoup.connect(naverMovieURL).get(); // connect로 DOM을 만들고 get으로 document로 변환
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// DOM에서 태그 문자열을 기준으로 리스트 추출
		Elements scoreList = naverMovieDoc.select(rankListTag);
		Elements imgList = naverMovieDoc.select(mImageTag);
		Elements titleList = naverMovieDoc.select(mTitleTag);
		
		// JDK 1.7 이상에서 생성자 Generic인 <NaverMovieVO>은 생략 가능
		List<NaverMovieVO> naverList = new ArrayList();
		
		// rankList box 중 상위 1~10까지만 가져오기. 순서대로 1~10까지 나열이 되어 있어야
		for(int i = 0; i < 10; i ++) {
			
			
			// dt.tit a tag에 담긴 텍스트를 추출 = 영화제목
			String title = titleList.get(i).text();
			// dt.tit a tag에 있는 href 속성값을 추출 = 영화정보 보기
			// https://movie.naver.com/movie/bi/mi/basic.nhn?code=181925
			String detailUrl = "https://movie.naver.com" + titleList.get(i).attr("href");
			
			// div.thumb a img tag의 src 속성값을 추출 = 영화 이미지 URL
			String imgUrl = imgList.get(i).attr("src");
			
			naverList.add(
					NaverMovieVO.builder()
					.m_rank(i + 1)
					.m_title(title)
					.m_detail_url(detailUrl)
					.m_img_url(imgUrl)
					.build()
			);
			
			
			log.debug(title);
		} // for end
		return naverList;
		
	}
}
