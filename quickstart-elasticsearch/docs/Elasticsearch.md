## Elasticsearch

Elasticsearch是一个基于Lucene的分布式全文搜索引擎，基于RESTFul web接口。
结合Kibana、Logstash、Beats，即Elastic stack(ELK)，被广泛的应用在日志数据分析、
实时监控等领域。

Elasticsearch是elastic stack的核心，负责存储、搜索、分析数据

- Kibana：数据可视化
- Elasticsearch：存储、计算、搜索数据
- Logstash、Beats：数据抓取

**基本概念**

- 文档(document)：每一条数据就是一个文档
- 词条(term)：对文档中的内容进行分词，得到的词语就是词条
- 索引(index)：相同类型文档的集合
- 映射(mapping)：索引中文档的字段约束信息，类似表的结构约束

**MySQL与Elasticsearch概念对比**

| MySQL  | Elasticsearch | 说明                                                |
|--------|---------------|---------------------------------------------------|
| Table  | Index         | 索引就是文档的集合，类似数据库中的表                                |
| Row    | Document      | 每一个文档就是一条数据，类似数据库中的行，文档都是JSON格式                   |
| Column | Field         | 字段就是文档中的字段，类似数据库中的列                               |
| Schema | Mapping       | 映射就是索引中文档的约束，例如字段类型的约束，类似数据库中的表结构                 |
| SQL    | DSL           | DSL是Elasticsearch提供的JSON风格的请求语句，用来操作Elasticsearch |



### 1.什么是正向索引?

基于文档ID创建索引，查询词条时必须先找到文档，再判断是否包含词条

### 2.什么是倒排索引?

对文档进行分词，对词条创建索引，并记录词条所在文档的信息。查询时先根据词条查到文档ID，再获取文档

