package com.leaf;

import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.jayway.jsonpath.JsonPath.parse;

/**
 * @created by ycc
 * @since 2021-09-20
 */
public class JSONPathTest {

    @Test
    public void pathTest() {
        String json = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        Filter cheapFictionFilter = filter(
                where("category").is("fiction").and("price").lte(10)
        );

        List<Map<String, Object>> books = JsonPath.parse(json).read("$.store.book[?]", cheapFictionFilter);
        System.out.println(books);
    }
    @Test
    public void test() {
        String json = "{ \"store\": {\n" +
                "    \"book\": [\n" +
                "      { \"category\": \"reference\",\n" +
                "        \"author\": \"Nigel Rees\",\n" +
                "        \"title\": \"Sayings of the Century\",\n" +
                "        \"price\": 8\n" +
                "      },\n" +
                "      { \"category\": \"fiction\",\n" +
                "        \"author\": \"Evelyn Waugh\",\n" +
                "        \"title\": \"Sword of Honour\",\n" +
                "        \"price\": 12,\n" +
                "        \"isbn\": \"0-553-21311-3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19\n" +
                "    }\n" +
                "  }\n" +
                "}";
        //输出book[0]的author值

        String author = (String) JSONPath.read(json, "$.store.book[0].autho");
        System.out.println("author\t" + author);
        Object dou = JSONPath.read(json, "$.store.book[0].price");
        Double d = (Double) dou;
//输出全部author的值，使用Iterator迭代
        List<String> authors = (List<String>) JSONPath.read(json, "$.store.book[*].autho");
        System.out.println("authors\t" + authors);
//输出book[*]中category == 'reference'的book
        List<Object> books = (List<Object>) JSONPath.read(json, "$.store.book[?(@.category == 'reference')]");
        System.out.println("books\t" + books);
//输出book[*]中category == 'reference'的book或者
        List<Object> books2 = (List<Object>) JSONPath.read(json, "$.store.book[?(@.category == 'reference' || @.price>10)]");
        System.out.println("books2\t" + books2);
//输出book[*]中category == 'reference'的book的author
        List<Object> books1 = (List<Object>) JSONPath.read(json, "$.store.book[?(@.category == 'reference')].author");
        System.out.println("books1\t" + books1);
//输出book[*]中price>10的book
        List<Object> b1 = (List<Object>) JSONPath.read(json, "$.store.book[?(@.price>10)]");
        System.out.println("b1" + b1);
//输出book[*]中含有isbn元素的book
        List<Object> b2 = (List<Object>) JSONPath.read(json, "$.store.book[?(@.isbn)]");
        System.out.println("b2" + b2);
//输出该json中所有price的值
        List<Integer> prices = (List<Integer>) JSONPath.read(json, "$..price");
        System.out.println("prices" + prices);
//输出该json中所有title的值
        List<Double> title = (List<Double>) JSONPath.read(json, "$..title");
        System.out.println("title" + title);
//输出该json中book 0,1的值
        List<Double> book01 = (List<Double>) JSONPath.read(json, "$..book[0,1]");
        System.out.println("book01" + book01);
/* //输出该json中book 0,1的值
List<Double> book012 = JSONPath.read(json, "$..book[-2:]");
System.out.println("book012"+book012);*/
//可以提前编辑一个路径，并多次使用它
    }
}
