### param
* path：路径，默认使用JSONPath抽取规则进行抽取，需要学习和了解JSONPath
* type：数据类型，该字段对应返回的数据类型
* default：默认值, 处理不了，或者json不存在数据时，返回的默认值
* function：处理这个字段对应的自定义函数，如果没有设置，使用JSONPath抽取对应path路径的数据
* interField: 如果数据是个对象，在这里定义对象里面的数据结构，每个字段构成如上说明一致

### field

* string：字符串类型，返回数据类型，对应配置param中的type
* integer：int类型，返回数据类型，对应配置param中的type
* float：float类型，返回数据类型，对应配置param中的type
* long: long类型，返回数据类型，对应配置param中的type
* short: short类型，返回数据类型，对应配置param中的type
* double: double类型，返回数据类型，对应配置param中的type
* array: array类型，返回数据类型，对应配置param中的type
* arrayobject: 数组实体类型，返回数据类型，对应配置param中的type，arrayobject类型有interField字段，配置实例里面的属性
* object: 实体类型，返回数据类型，对应配置param中的type，object类型有interField字段，配置实例里面的属性

### function
##### add 
* explain：拼接函数，在字符串前面和后面加上字符串。
* param：add函数中包含两个参数，第一个参数加在字符串开头，第二个参数加在字符串结尾。
* demo: add(start, end)
##### contain
* explain：包含函数，判断一个字符串是否包含另外一个字符串。
* param：contain函数中包含一个参数，判断指定的字符串是否包含该参数，或者数组是否包含该参数。
* demo: contain(fb#RHINO#)
##### larger：
* explain：大于函数，判断json中的数据是否大于指定的数值。
* param：larger函数中包含一个参数。
* demo: larger(3)
##### max：
##### min：
##### replace：
* explain：替换函数，替换old为new值。
* param：replace函数中包含两个参数，第一个参数是替换值，第二参数是被替换值。
* demo: replace(new, old)
### operator
##### and：
* explain：与操作，支撑多个函数，全部函数正确返回true，否则false。
* param：与操作包含多个函数。
* demo: and(contain(fb#RHINO#),contain(link))

##### for：
* explain：for操作，使用指定函数对数组中的每一个值处理。
* param：与操作包含一个函数。
* demo: for(contain(ycc))
##### or：
* explain：or操作，支撑多个函数，只要有一个函数结果为true则为true，否则false。
* param：与操作包含多个函数。
* demo: or(contain(fb#RHINO#),contain(link))
##### pipeline：
* explain：pipeline操作，支持多个函数，按函数顺序处理。
* param：与操作包含多个函数。
* demo: pipeline(replace(new,old)|add(start, end))
##### switch：
* explain：switch操作，支持多个函数，函数正确返回指定值。
* param：与操作包含多个函数。
* demo: switch(contain(fb#RHINO#)->3, contain(link)->6)
### demo

```

  @Test
    public void test() {
        String jslt = "....";
        String data = "....";
        JSONObject jsonObject = JSONObject.parseObject(data);
        DataPharser dataPharser = new DataPharser(jslt);
        JSONObject results = dataPharser.changeJSONObject(jsonObject);
    }
    
 数据请看test/resources

```



data:
```
test/resources/data.json
```
jslt:
```
test/resources/jslt.json
{
  "array": {
    "path": ".keyword",
    "type": "array"
  },
  "arrayobject": {
    "path": "$.achievements.publications",
    "default": "",
    "type": "arrayobject",
    "interFields": {
      "title": {
        "path": "$.id",
        "default": "",
        "type": "string"
      },
      "name": {
        "path": "$.name",
        "default": "",
        "type": "string"
      },
      "root_this_id": {
        "path": "^.this_id",
        "default": "",
        "type": "string"
      }
    }
  },
  "boolean": {
    "path": ".is_verity",
    "type": "boolean"
  }
}
```