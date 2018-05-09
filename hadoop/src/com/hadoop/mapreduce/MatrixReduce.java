package com.hadoop.mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * Created by scaf_xs on 2017/11/16.
 */
public class MatrixReduce extends Reducer<Text,Text,Text,Text> {

    private Text outKey=new Text();
    private Text outValue=new Text();


    public void reduce(Text key,Iterable<Text> values,Context context)
            throws IOException,InterruptedException{

//        Map<String, String> mapA = new HashMap<String, String>();
//        Map<String, String> mapB = new HashMap<String, String>();
//
//        for (Text value : values) {
//            String[] val = value.toString().split(",");
//            if ("a".equals(val[0])) {
//                mapA.put(val[1], val[2]);
//            } else if ("b".equals(val[0])) {
//                mapB.put(val[1], val[2]);
//            }
//        }
//
//        int result = 0;
//        Iterator<String> mKeys = mapA.keySet().iterator();
//        while (mKeys.hasNext()) {
//            String mkey = mKeys.next();
//            if (mapB.get(mkey) == null) {// 因为mkey取的是mapA的key集合，所以只需要判断mapB是否存在即可。
//                continue;
//            }
//            result += Integer.parseInt(mapA.get(mkey))
//                    * Integer.parseInt(mapB.get(mkey));
//        }
//        context.write(key, new IntWritable(result));

        StringBuilder sb=new StringBuilder();
        for(Text value:values){
            sb.append(value+",");
        }
        String res=null;
        if(sb.toString().endsWith(",")){
            res=sb.substring(0,sb.length()-1);
        }
        outKey.set(key);
        outValue.set(res);
        context.write(outKey,outValue);
    }
}