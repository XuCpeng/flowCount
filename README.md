# FlowCount

Mapreduce Demo

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