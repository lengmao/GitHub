package com.hadoop.mapreduce;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaf_xs on 2017/11/16.
 */

public  class MatrixMap extends Mapper<LongWritable,Text,Text,Text> {
//        private String flag=null; //数据集名称
//        private int rowNum;//矩阵A的行数
//        private int colNum;//矩阵B的烈数
//        private int rowIndexA;//矩阵A，当前第几行
//        private int rowIndexB;//矩阵B，当前第几行


        private Text outKey=new Text();
        private Text outValue=new Text();
        private List<String> cacheList=new ArrayList<>();

        @Override
        public void setup(Context context) throws IOException,InterruptedException{
            super.setup(context);

            URI fileURI = ((FileSplit) context.getInputSplit()).getPath().toUri();// 获取文件名称
            if(fileURI.getPath().toString().endsWith("mb.txt")){

                //通过全局缓存将右侧矩阵读入List<String>
                FileReader fr = new FileReader(new File(fileURI));
                BufferedReader br=new BufferedReader(fr);
                String line=null;
                while ((line=br.readLine())!=null){
                    cacheList.add(line);
                }
                fr.close();
                br.close();
            }
        }

        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException,InterruptedException {
//            String[] tokens=value.toString().split(",");
//            if("ma.txt".equals(flag)){
//                for (int i = 1; i <= colNum; i++) {
//                    Text k = new Text(rowIndexA + "," + i);
//                    for (int j = 0; j < tokens.length; j++) {
//                        Text v = new Text("a," + (j + 1) + "," + tokens[j]);
//                        context.write(k, v);
//                    }
//                }
//                rowIndexA++;// 每执行一次map方法，矩阵向下移动一行
//            }else if("mb.txt".equals(flag)) {
//                for (int i = 1; i <= rowNum; i++) {
//                    for (int j = 0; j < tokens.length; j++) {
//                        Text k = new Text(i + "," + (j + 1));
//                        Text v = new Text("b," + rowIndexB + "," + tokens[j]);
//                        context.write(k, v);
//                    }
//                }
//                rowIndexB++;// 每执行一次map方法，矩阵向下移动一行
//            }

            //行
            String row_martix1=value.toString().split("\\s")[0];
            //列，数组
            String[] col_martix1_array=value.toString().split("\\s")[1].split(",");
            System.out.println(col_martix1_array);
            for(String line:cacheList){
                //右侧矩阵行row
                String row_martix2=line.toString().split("\\s")[0];
                String [] col_martix2_array=line.toString().split("\\s")[1].split(",");
                int res=0;

                //遍历左侧矩阵第一行每一列
                for(String col_martix1_value:col_martix1_array){
                    String col_martix1=col_martix1_value.toString().split("_")[0];
                    String value_martix1=col_martix1_value.toString().split("_")[1];

                    //遍历右侧矩阵第一行的每一列
                    for(String col_martix2_value:col_martix2_array){
                        if(col_martix2_value.startsWith(col_martix1+"_")){
                            String value_martix2=col_martix2_value.split("_")[1];

                            //将两列的值相乘，并累加
                            res+=Integer.valueOf(value_martix1)*Integer.valueOf(value_martix2);
                        }

                    }
                }

                //res 就是结果矩阵中的某元素
                outKey.set(row_martix1);
                outValue.set(row_martix2+"_"+res);
                context.write(outKey,outValue);
            }
        }
    }



