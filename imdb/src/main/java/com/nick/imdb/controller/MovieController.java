package com.nick.imdb.controller;

import com.nick.imdb.annotation.Log;
import com.nick.imdb.mapper.MovieMapper;
import com.nick.imdb.pojo.Movie;
import com.nick.imdb.search.MovieSearchRepo;
import com.nick.imdb.service.MovieService;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Nick Yuan
 * @date 2019/4/4
 * @mood shitty
 */
@Controller
public class MovieController {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    StringRedisTemplate template;
    @Autowired
    MovieSearchRepo repo;
    @Autowired
    MovieService movieService;
    @GetMapping("/get/{id}")
    @ResponseBody
    @Log
    public String add(@PathVariable Long id){
        return movieService.get(id).toString();
    }
    @RequestMapping("/")
    public ModelAndView show(ModelAndView model){
        List<Movie> movies=new ArrayList<>();
        Random random=new Random();

        for(int i=0;i<13;i++){
            Movie movie=new Movie();
            Integer id=random.nextInt(1000);
           String name = (String) template.opsForHash().get("movie:" + id, "title");
            String image = (String) template.opsForHash().get("movie:" + id, "image");
          movie.setName(name);
          movie.setImage(image);
            movies.add(movie);
        }
        model.addObject("movies",movies);
        model.setViewName("index");
        return model;
    }
    @GetMapping("/search/{keyword}")
    public ModelAndView search(@PathVariable String keyword,ModelAndView model){
        NativeSearchQueryBuilder builder=new NativeSearchQueryBuilder();
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("all", keyword);
        builder.withQuery(matchPhraseQueryBuilder);
        Page<Movie> page = repo.search(builder.build());
        model.addObject("movies",page.getContent());
        model.setViewName("index");
        return model;
    }
}
