## oops-orm

:sob:  orm 简单实现

### 思路

接口和 XML 文件之间约定好对应关系，然后通过接口信息找到对应XML中具体方法执行 sql

```java

SqlHandler handler = new SimpleSqlHandler();
Method selectAll = UserDao.class.getDeclaredMethod("selectByName", String.class);
Map<String, Object> map = new HashMap<>();
map.put("ename", "JONES");
handler.excuteSql(selectAll, map);

```

此示例只是做了个简单的查询，不进行复杂处理。




