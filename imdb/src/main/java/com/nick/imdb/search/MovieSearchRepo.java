package com.nick.imdb.search;

import com.nick.imdb.pojo.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nick Yuan
 * @date 2019/4/6
 * @mood shitty
 */
@Repository
public interface MovieSearchRepo extends ElasticsearchRepository<Movie,Long> {
}
