# FlowCount

MapReduce Demo

## MapReduce编程规范

### Mapper阶段

1. 用户自定义的Mapper要继承自己的父类
2. Mapper的输入数据是KV对的形式（KV的类型可自定义）
3. Mapper中的业务逻辑写在map()方法中
4. Mapper的输出数据是KV对的形式（KV的类型可自定义）
5. map()方法（maptask进程）对每一个<K,V>调用一次

### Reducer阶段

1. 用户自定义的Reducer要继承自己的父类
2. Reducer的输入数据类型对应Mapper的输出数据类型，也是KV
3. Reducer的业务逻辑写在reduce()方法中
4. Reducetask进程对每一组相同k的<k,v>组调用一次reduce()方法
### Driver阶段
整个程序需要一个Drvier来进行提交，提交的是一个描述了各种必要信息的job对象

## WordCount

官方给出的一个统计单词个数实例，类似于HelloWorld

- Bean
- Mapper
- Reducer
- Driver

## FlowCount

统计移动用户手机流量，也是基础的对ＴＸＴ的处理

- Combiner
- 自定义Partitioner

## FlowSort

基于FlowCount,是对FlowCount结果的再次处理，对FlowCount的结果排序

- WritableComparable

## OrderSort

利用排序求每个订单中最贵的商品

- GroupingComparator

## Table

订单处理，将订单order.txt中的商品ID用pd.txt中的商品名替换

- 多表合并
- reduce端表合并（数据倾斜）

## DistributedCache

订单处理，将订单order.txt中的商品ID用pd.txt中的商品名替换

- 多表合并
- reduce端表合并（数据倾斜）
- DistributedCache

## WholeFile

小文件处理

- 自定义InputFormat
- SequenceFileOutPutFormat
- SequenceFile

## Filter

过滤日志及自定义日志输出路径

- 自定义OutputFormat

## WebLog

简单的日志处理

- Counter计数器

## Index

有大量的文本（文档、网页），需要建立搜索索引

- 多job串联
- 倒排索引