package com.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by scaf_xs on 2017/11/16.
 *
 */
public class MatrixMain extends Configured implements Tool{

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf=getConf();
        String otherArgs[]=new GenericOptionsParser(conf,args).getRemainingArgs();

        System.out.println(otherArgs);

        Job job =new Job(conf,"myReduce");

        job.setJarByClass(MatrixMain.class);

        //set job
        job.setJobName("MyMapReduce");

        //set mapper
        job.setMapperClass(MatrixMap.class);  //指定Map类作为Map任务代码
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //set reduce
        job.setCombinerClass(MatrixReduce.class);
        job.setReducerClass(MatrixReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


//        FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
        FileInputFormat.setInputPaths(job,new Path(otherArgs[0]),new Path(otherArgs[1]));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(new Path(otherArgs[0]));
        System.out.println(new Path(otherArgs[1]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[2]));  //输出路径

        job.waitForCompletion(true);

        return job.isSuccessful()?0:1;
    }

    public static void main(String [] args) throws Exception{

        int res= ToolRunner.run(new Configuration(),new MatrixMain(),args);
        System.exit(res);

    }
}
