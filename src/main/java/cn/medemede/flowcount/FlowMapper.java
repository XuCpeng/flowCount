package cn.medemede.flowcount;


import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * KEYIN:输入数据的key 文件行号
 * VALUEIN： 每行的输入数据
 * KEYOUT： 输出数据的key
 * VALUEOUT： 输出数据的value类型
 * @author Peng
 *
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean > {
	FlowBean bean=new FlowBean();
	Text k=new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
			throws IOException, InterruptedException {
		// 1 获取一行数据
		String line = value.toString();

		// 2 截取字段
		String[] fields = line.split("\t");

		// 3 封装bean以及获取电话号码
		String phoneNum=fields[1];

		long upFlow=Long.parseLong(fields[fields.length-3]);
		long downFlow=Long.parseLong(fields[fields.length-2]);
		bean.set(upFlow, downFlow);
		k.set(phoneNum);

		// 4 写出
		context.write(k, bean);
	}
}
