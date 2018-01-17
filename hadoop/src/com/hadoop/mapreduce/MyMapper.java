package com.hadoop.mapreduce;

import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by scaf_xs on 2017/11/15.
 */

public class MyMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    private Text mapOutPutKey =new Text();
    private final static  IntWritable mapOutPutValue=new IntWritable(1);

    @Override
    public void map(LongWritable key,Text value,Context context)
        throws IOException,InterruptedException{

        String line=value.toString().trim();//读取数据源
        StringTokenizer tokenizer=new StringTokenizer(line);

        while (tokenizer.hasMoreTokens()){
            String word=tokenizer.nextToken();
            mapOutPutKey.set(word);
            context.write(mapOutPutKey,mapOutPutValue);
        }

    }

}

