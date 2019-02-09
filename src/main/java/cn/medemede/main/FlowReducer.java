package cn.medemede.main;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
	FlowBean flowBean=new FlowBean();
	@Override
	protected void reduce(Text arg0, Iterable<FlowBean> arg1, Reducer<Text, FlowBean, Text, FlowBean>.Context arg2)
			throws IOException, InterruptedException {
		// 计算总流量
		long sum_upFlow=0;
		long sum_downFlow=0;

		for (FlowBean flowBean : arg1) {
			sum_upFlow+=flowBean.getUpFlow();
			sum_downFlow+=flowBean.getDownFlow();
		}

		flowBean.set(sum_upFlow,sum_downFlow);

		// 输出
		arg2.write(arg0, flowBean);
	}
}
