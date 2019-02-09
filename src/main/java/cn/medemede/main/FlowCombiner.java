package cn.medemede.main;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCombiner extends Reducer<Text, FlowBean, Text, FlowBean> {
    private FlowBean flowBean=new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        // 在map上计算总流量
        long sum_upFlow=0;
        long sum_downFlow=0;

        for (FlowBean flowBean : values) {
            sum_upFlow+=flowBean.getUpFlow();
            sum_downFlow+=flowBean.getDownFlow();
        }

        flowBean.set(sum_upFlow,sum_downFlow);

        // 输出
        context.write(key, flowBean);
    }
}
