package com.briup.cms_jpa.web.controller;

import com.briup.cms_jpa.bean.Article;
import com.briup.cms_jpa.service.IArticleService;
import com.briup.cms_jpa.util.ExcelUtils;
import com.briup.cms_jpa.util.Message;
import com.briup.cms_jpa.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "文章相关接口")
public class ArticleController {
    @Autowired
    private IArticleService service;
    @GetMapping("article/findAll")
    @ApiOperation("查询所有文章")
    public Message<List<Article>> findAll(){
        return MessageUtil.success(service.findAll(),"查找成功");
    }
    @ApiOperation("根据id查找文章")
    @GetMapping("article/findById")
    @ApiImplicitParam(name = "id",value = "文章id",paramType = "query",dataType = "int",required = true)
    public Message<Article>findById(Integer id){
        return MessageUtil.success(service.findById(id),"查询成功");
    }
    @ApiOperation(value = "新增文章",notes = "category.name,category.code不需要输入")
    @PostMapping("article/saveOrUpdate")
    public Message saveOrUpdate(Article article){
        String message="";
        if (article.getId()==null){
            message="添加成功";
        }else {
            message="更新成功";
        }
        service.saveOrUpdate(article);
        return MessageUtil.success(message);
    }
    @GetMapping("article/deleteById")
    @ApiOperation("根据id删除文章")
    @ApiImplicitParam(name = "id",value = "文章id",paramType = "query",dataType = "int",required = true)
    public Message deleteById(Integer id){
        service.deleteById(id);
        return MessageUtil.success("删除成功");
    }
    //将数据库信息导入到表格，自定义需要导出的内容
            @ApiOperation(value="将文章导入到Excel中",notes="注意！测试的时候请将地址粘贴到浏览器地址栏测试",produces="application/octet-stream")
            @GetMapping("/article/download")
            public void download(HttpServletResponse response) throws Exception{
                // 查询出所有文章信息
                String excelName = "article_list";
                String[] headList = new String[]{"编号","标题","内容","目录","作者"};
                String[] fieldList = new String[]{"id","title","content","category","author"};
                List<Map<String, Object>> dataList = new ArrayList<>();
                List<Article> list =service.findAll();
                for(Article a : list){
                    Map<String, Object> map = new HashMap<>();
                    map.put("id",a.getId());
                    map.put("title",a.getTitle());
                    map.put("content",a.getContent());
                    map.put("category",a.getCategory().getName());
                    map.put("author",a.getAuthor());
                    dataList.add(map);
                }
        //调用工具类导出excel
        ExcelUtils.createExcel(response,excelName,headList,fieldList,dataList);
    }
}
