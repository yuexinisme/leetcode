package com.nick.imdb.util;

import com.nick.imdb.mapper.MovieMapper;
import com.nick.imdb.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Nick Yuan
 * @date 2019/4/5
 * @mood shitty
 */
@Component
public class Selector implements Select{

    @Override
    public Movie get(Integer id) {
        Movie movie=new Movie();
        movie.setName("Old Man");
        return movie;
    }
}
