## oops-orm

:sob:  orm 简单实现

### 思路

接口和 XML 文件之间约定好对应关系，然后通过接口信息找到对应XML中具体方法执行 sql

```java

Method selectByName = UserDao.class.getDeclaredMethod("selectByName", String.class);
Map<String, Object> map = new HashMap<>();
map.put("ename", "JONES");
new SimpleSqlHandler().excuteSql(selectByName, map);


```

此示例只是做了个简单的查询，不进行复杂处理。




