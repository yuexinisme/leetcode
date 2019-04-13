package com.nick.imdb.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author Nick Yuan
 * @date 2019/4/4
 * @mood shitty
 */
@Data
@Document(indexName = "movies",type = "movie")
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String name;
    private String image;
    private Integer year;
    private String rating;
    private Integer runtime;
    private String genres;
    private Double all_rating;
    private Short metascore;
    private String plot;
    @Field(type = FieldType.Text)
    private String director;
    @Field(type = FieldType.Text)
    private String stars;
    private Integer votes;
    private Double gross;
    private Boolean open;
    @Transient
    private String all;
}
