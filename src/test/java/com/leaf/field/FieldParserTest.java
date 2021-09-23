package com.leaf.field;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.leaf.BaseTest;
import com.leaf.function.ReplaceFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @created by ycc
 * @since 2021-09-20
 */
@Slf4j
public class FieldParserTest {
    private String data;
    private String jslt;

    @Before
    public void before() {
        data = BaseTest.getFileStr("/data.json");
        jslt = BaseTest.getFileStr("/jslt.json");
    }

    @Test
    public void test1() {
        JSONArray jsonArray = (JSONArray) JSONPath.read(data, ".achievements.publications.id");
        System.out.println(jsonArray);
    }

    @Test
    public void parseTest() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setJslt(jslt);
        dataEntity.setJSONString(data);
        JSONObject jsonObject = dataEntity.changeJSONObject();
        System.out.println(jsonObject);
    }

    @Test
    public void containTest() {
        JSONObject jsonObject = JSONObject.parseObject("{\"a\":\"3\"}");

        System.out.println(jsonObject.getInteger("a"));
    }

    @Test
    public void test() {
        log.error("test");
        log.error("test");
    }
}