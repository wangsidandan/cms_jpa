package com.briup.cms_jpa.bean.ex;

import com.briup.cms_jpa.bean.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cms_category")
@ApiModel
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class CategoryVM implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "栏目id")
    private Integer id;
    @ApiModelProperty(value = "栏目编码",required = true)
    private long code;
    @ApiModelProperty(value = "栏目名称",required = true)
    private String name;
    @OneToMany(mappedBy = "category",targetEntity =Article.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Article>articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
