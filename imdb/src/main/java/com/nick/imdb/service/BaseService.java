package com.nick.imdb.service;

import com.nick.imdb.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Nick Yuan
 * @date 2019/4/11
 * @mood shitty
 */
public class BaseService<T> {
    @Autowired
    Mapper<T> mapper;
    public void insert (T t){
        mapper.insert(t);
    }
    public T get(Long id){
        return  mapper.selectByPrimaryKey(id);
    }
}
