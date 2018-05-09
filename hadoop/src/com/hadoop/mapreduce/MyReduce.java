package com.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by scaf_xs on 2017/11/15.
 */

public class MyReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

    private final IntWritable reduceOutputValue=new IntWritable();

    @Override
    public void reduce(Text key,Iterable<IntWritable> values,Context context)
            throws IOException,InterruptedException{

        int sum = 0;
        for(IntWritable value : values){
            sum+=value.get();
        }
        reduceOutputValue.set(sum);
        context.write(key,reduceOutputValue);
    }
}
