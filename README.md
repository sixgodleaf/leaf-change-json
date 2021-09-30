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

```$xslt

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
{
  "follower_count": 12,
  "keyword": ["ycc", "handsome"],
  "keywordInt": [3, 5, 7],
  "achievements": {
    "publications": [{
      "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "url": "http://www.messi.com",
      "name": "笑傲江湖",
      "publish_on": {
        "month": 7,
        "year": 2021,
        "day": 29,
        "$type": "com.linkedin.common.Date"
      },
      "publisher": "新华",
      "description": "武侠",
      "authors": [{
        "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "name": "messi-leo-17789520b",
        "last_name": "leo",
        "first_name": "messi",
        "headline": "messi club - 首席执行官",
        "image_url": "https://media.licdn.cn/dms/image/C4E03AQEY-RuKzhRO7A/profile-displayphoto-shrink_350_1400/0/1627609147468?e=1633564800&v=beta&t=QB0cW2nZlsQDbib2DR08bBnGAV0CnliWDzwOTZqEXvs"
      }]
    }, {
      "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "url": "http://www.messi.com",
      "name": "笑傲江湖",
      "publish_on": {
        "month": 7,
        "year": 2021,
        "day": 29,
        "$type": "com.linkedin.common.Date"
      },
      "publisher": "新华",
      "description": "武侠",
      "authors": [{
        "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "name": "messi-leo-17789520b",
        "last_name": "leo",
        "first_name": "messi",
        "headline": "messi club - 首席执行官",
        "image_url": "https://media.licdn.cn/dms/image/C4E03AQEY-RuKzhRO7A/profile-displayphoto-shrink_350_1400/0/1627609147468?e=1633564800&v=beta&t=QB0cW2nZlsQDbib2DR08bBnGAV0CnliWDzwOTZqEXvs"
      }]
    }],
    "test_scores": [{
      "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "description": "the best",
      "score": "the first",
      "name": "测试创新奖",
      "date_on": {
        "month": 3,
        "year": 2018,
        "$type": "com.linkedin.common.Date"
      }
    }],
    "languages": [{
      "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
      "name": "english"
    }],
    "courses": [{
      "name": "redis",
      "number": "redis02"
    }],
    "projects": [{
      "url": "http://www.baggio.com",
      "title": "mes Project",
      "description": "制造执行系统",
      "date_range": {
        "start": {
          "month": 8,
          "year": 2010,
          "$type": "com.linkedin.common.Date"
        },
        "end": {
          "month": 8,
          "year": 2010,
          "$type": "com.linkedin.common.Date"
        }
      },
      "contributors": [{
        "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "name": "messi-leo-17789520b",
        "last_name": "leo",
        "first_name": "messi",
        "headline": "messi club - 首席执行官",
        "image_url": "https://media.licdn.cn/dms/image/C4E03AQEY-RuKzhRO7A/profile-displayphoto-shrink_350_1400/0/1627609147468?e=1633564800&v=beta&t=QB0cW2nZlsQDbib2DR08bBnGAV0CnliWDzwOTZqEXvs"
      }]
    }],
    "honors": [{
      "description": "congratulation",
      "title": "最佳创新奖",
      "issuer": "梦网",
      "issued_on": {
        "month": 7,
        "year": 2010
      }
    }],
    "organizations": [{
      "name": "BC Club",
      "data_range": {},
      "description": null,
      "position_held": "足球总监"
    }],
    "patents": [{
      "patent_number": "345678900",
      "description": "just so so",
      "title": "football skills",
      "issuer": "ar",
      "url": "http://www.messi.com",
      "issued_on": {
        "month": 10,
        "year": 2020,
        "day": 18
      },
      "inventors": [{
        "this_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
        "name": "messi-leo-17789520b",
        "last_name": "leo",
        "first_name": "messi",
        "headline": "messi club - 首席执行官",
        "image_url": "https://media.licdn.cn/dms/image/C4E03AQEY-RuKzhRO7A/profile-displayphoto-shrink_350_1400/0/1627609147468?e=1633564800&v=beta&t=QB0cW2nZlsQDbib2DR08bBnGAV0CnliWDzwOTZqEXvs"
      }]
    }]
  },
  "volunteer": [{
    "company_body": {
      "name": "DongFeng Peugeot Citroen Automobile",
      "unique_entity_id": "urn:li:fsd_company:1016120",
      "this_id": "urn:li:fsd_company:1016120",
      "id": "1016120",
      "profile_type": "company",
      "image_url": "https://media.licdn.cn/dms/image/C4D0BAQEkvTPeKIudCg/company-logo_400_400/0/1519922015624?e=1635984000&v=beta&t=YC2TJkL9cnDbre5Y6AbPVU8N2_lZhujkHKyBeXPmrs0"
    },
    "title": "保修管理员",
    "cause": "HUMANITARIAN_RELIEF",
    "description": null,
    "company_name": "DongFeng Peugeot Citroen Automobile"
  }],
  "certifications": [{
    "url": "http://www.surfilter.com.com",
    "name": "高级测试工程师",
    "authority": "任子行网络技术股份有限公司",
    "license_number": "45678909807876876",
    "displaySource": "com.com"
  }],
  "education": [{
    "degree_name": "学士",
    "field_of_study": "urn:li:fsd_fieldOfStudy:100189",
    "school_name": "军事经济学院",
    "description": null,
    "time_period": null,
    "image_url": ""
  }],
  "is_verity": true,
  "id": "ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
  "this_id": "urn:li:member:894995371",
  "headline": "messi club - 首席执行官",
  "name": "messi leo",
  "unique_entity_id": "urn:li:fsd_profile:ACoAADVYi6sB1UjQhrLiy-GHve-OMNoC82RmKhk",
  "image_url": "https://media.licdn.cn/dms/image/C4E03AQEY-RuKzhRO7A/profile-displayphoto-shrink_800_800/0/1618454374323?e=1633564800&v=beta&t=s4408RszZaCaq0hn6-TON2Fv7q1d1TsUDY44x3BRbpY",
  "background_url": "https://media.licdn.cn/dms/image/C4D16AQF2hfB4QvE7yQ/profile-displaybackgroundimage-shrink_350_1400/0/1627609147468?e=1633564800&v=beta&t=QB0cW2nZlsQDbib2DR08bBnGAV0CnliWDzwOTZqEXvs",
  "location": "新加坡",
  "geo_location": "新加坡",
  "about": "the best football player",
  "treasury_media": [{
    "data": {
      "Url": "https://www.zhibo8.cc/zuqiu/2021/0729-6d89f40-svideo.htm"
    },
    "multiLocaleTitle": {
      "zh_TW": "亲子时光！梅西晒与马特奥抢圈视频-直播吧zhibo8.cc"
    },
    "description": "亲子时光！梅西晒与马特奥抢圈视频[窗口/手机/PAD观看]",
    "title": "亲子时光！梅西晒与马特奥抢圈视频-直播吧zhibo8.cc",
    "multiLocaleDescription": {
      "zh_TW": "亲子时光！梅西晒与马特奥抢圈视频[窗口/手机/PAD观看]"
    },
    "providerName": "zhibo8.cc"
  }, {
    "data": {
      "VectorImage": {
        "artifacts": [{
          "width": 571,
          "fileIdentifyingUrlPathSegment": "800_800/0/1627612378176?e=1628157600&v=beta&t=evgtn5VKuGG30F_EE4xzXaUsc51Gk9A78C5aQ2DRJb0",
          "expiresAt": 1628157600000,
          "height": 800,
          "$type": "com.linkedin.common.VectorArtifact"
        }, {
          "width": 1280,
          "fileIdentifyingUrlPathSegment": "8192_8192/0/1627612378176?e=1628157600&v=beta&t=bMhAphI8ueGi7bQ4tFa1ySVSoR0fsmoH8OTGbQpSSHQ",
          "expiresAt": 1628157600000,
          "height": 1792,
          "$type": "com.linkedin.common.VectorArtifact"
        }, {
          "width": 342,
          "fileIdentifyingUrlPathSegment": "480_480/0/1627612378176?e=1628157600&v=beta&t=bk86hkBvW86kFuaFxVaJhF_EKqg67mLOb7tFBr5piyo",
          "expiresAt": 1628157600000,
          "height": 480,
          "$type": "com.linkedin.common.VectorArtifact"
        }, {
          "width": 914,
          "fileIdentifyingUrlPathSegment": "1280_1280/0/1627612378176?e=1628157600&v=beta&t=YVYcrggZ44hdqEJa5rN4kM6YniOx593aUXUK3Hi9pKA",
          "expiresAt": 1628157600000,
          "height": 1280,
          "$type": "com.linkedin.common.VectorArtifact"
        }, {
          "width": 1280,
          "fileIdentifyingUrlPathSegment": "1920_1920/0/1627612378176?e=1628157600&v=beta&t=IXyp_lHgrhXrPZoj4YN2yiRz2Ld7McBzNsljAJVyjxQ",
          "expiresAt": 1628157600000,
          "height": 1792,
          "$type": "com.linkedin.common.VectorArtifact"
        }, {
          "width": 114,
          "fileIdentifyingUrlPathSegment": "160_160/0/1627612378176?e=1628157600&v=beta&t=Vk-7Nn26O2iyMBDmBl9WTd4Wr2c343kioko5VRrIC0I",
          "expiresAt": 1628157600000,
          "height": 160,
          "$type": "com.linkedin.common.VectorArtifact"
        }],
        "rootUrl": "https://media.licdn.cn/dms/image/C4D2DAQG8PRT1TWKXMw/profile-treasury-image-shrink_",
        "$type": "com.linkedin.common.VectorImage"
      }
    },
    "multiLocaleTitle": {
      "zh_TW": "gyy.jpg"
    },
    "title": "gyy.jpg"
  }],
  "task_info": {
    "access_token": "9c99ea17ef6f233bf515953c811d555b",
    "additional_information": {
      "start_time": 1577808000,
      "project": ["hk01"],
      "ocr": false
    },
    "priority": 4,
    "task_info": {
      "id": "mapping:1614060961070",
      "params": {
        "seed": ["https://www.linkedin.com/in/messi-leo-17789520b/"],
        "seed_type": "user",
        "timer": {
          "interval": 0,
          "schedule_time": 1614061081
        },
        "crawl_image": false,
        "task": ["profile"],
        "limit": {
          "profile": {
            "count": -1
          }
        }
      },
      "type": "linkedin"
    },
    "queue": "mapping:1614060961070|linkedin|user|24",
    "interface": "",
    "action": "add",
    "task_type": "inc_task",
    "tag": "r4570",
    "sub_seed": "messi-leo-17789520b",
    "seed": "messi-leo-17789520b"
  },
  "created_time": "2020-08-16T01:29:46",
  "channel": "user",
  "monitorType": "account"
}
```
jslt:
```
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