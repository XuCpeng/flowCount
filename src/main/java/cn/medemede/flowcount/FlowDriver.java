package cn.medemede.flowcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 相当于一个yarn集群的客户端，
 * 需要在此封装我们的MapperReducer程序相关运行参数，指定jar包
 * 最后提交给yarn
 */

public class FlowDriver {
    public static void main(String[] args) throws Exception {
        // 1 获取配置信息，或者job对象实例
        Configuration configuration=new Configuration();
        Job job = Job.getInstance(configuration,"Job1_Sum");

        // 2 指定本程序的jar包所在的本地路径
        job.setJarByClass(FlowDriver.class);

        // 3 指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        // 4 指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 5 指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 8 处理小文件，默认为TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job,4194304); //最大切片4MB
        CombineTextInputFormat.setMinInputSplitSize(job,2097152); //最小切片2MB

        // 9 设置自定义partitioner
        job.setPartitionerClass(FlowPartitioner.class);
        //自定义partition后，要根据自定义partitioner的逻辑设置相应数量的reduce task
        job.setNumReduceTasks(5);

        // 10 关联Combiner
        job.setCombinerClass(FlowCombiner.class);

        // 6 指定job的输入输出目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 将job中配置的相关参数，以及job所用的java类所在的jar包， 提交给yarn去运行
        job.submit(); //直接提交
        boolean result = job.waitForCompletion(true);  //等待所有事情完成再提交
        //System.exit(result?0:1);
    }

}
