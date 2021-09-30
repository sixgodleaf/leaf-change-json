package com.leaf.field;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.leaf.BaseTest;
import com.leaf.DataPharser;
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

    public JSONObject test(String path) {
        jslt = BaseTest.getFileStr(path);
        DataEntity dataEntity = new DataEntity();
        dataEntity.setJslt(jslt);
        dataEntity.setJSONString(data);
        JSONObject jsonObject = dataEntity.changeJSONObject();
        System.out.println(jsonObject);
        return jsonObject;
    }

    @Test
    public void test() {
        String jslt = "....";
        String data = "....";
        JSONObject jsonObject = JSONObject.parseObject(data);
        DataPharser dataPharser = new DataPharser(jslt);
        JSONObject results = dataPharser.changeJSONObject(jsonObject);
    }

    @Test
    public void integerTest() {
        test("/stringOr.json");
        test("/switch.json");
        test("/stringAnd.json");
        test("/stringFor.json");
        test("/pipeline.json");
        test("/integer.json");
        test("/string.json");
        test("/boolean.json");
        test("/double.json");
        test("/long.json");
        test("/float.json");
        test("/short.json");
        test("/array.json");
        test("/object.json");
        test("/arrayObject.json");
        test("/stringReplace.json");
        test("/stringAdd.json");
        test("/contain.json");
        test("/larger.json");
    }
}