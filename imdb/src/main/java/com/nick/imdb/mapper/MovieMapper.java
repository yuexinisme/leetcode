package com.nick.imdb.mapper;

import com.nick.imdb.pojo.Movie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Nick Yuan
 * @date 2019/4/4
 * @mood shitty
 */

public interface MovieMapper extends Mapper<Movie> {
//    @Insert("insert into movie (name,image,year,rating,runtime,genres,all_rating," +
//            "metascore,plot,director,stars,votes,gross,open) values (#{name},#{image},#{year},#{rating}," +
//            "#{runtime},#{genres},#{all_rating},#{metascore},#{plot},#{director},#{stars},#{votes},#{gross},#{open})")
//    void insert(Movie movie);
//    @Select("select * from movie where id=#{id}")
//    Movie showImage(Integer id);
}
