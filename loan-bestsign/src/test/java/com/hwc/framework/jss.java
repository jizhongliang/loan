package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import org.junit.Test;

/**
 * Created by lxk on 2017/12/30.
 */
public class jss {

    String ret = "{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.48\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${amount${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.65\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${interest${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.81\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${total${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "}";

    String header="[\n" +
            "{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.75\",\n" +
            "\t\"y\": \"0.164\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${num}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "}\n" +
            "{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.32\",\n" +
            "\t\"y\": \"0.184\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${seq}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.62\",\n" +
            "\t\"y\": \"0.1084\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${rate}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},{\n" +
            "\t\"pageNum\": \"1\",\n" +
            "\t\"x\": \"0.12\",\n" +
            "\t\"y\": \"0.184\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${amount}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "}";

    String page2="{\n" +
            "\t\"pageNum\": \"2\",\n" +
            "\t\"x\": \"0.48\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${amount${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pageNum\": \"2\",\n" +
            "\t\"x\": \"0.65\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${interest${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pageNum\": \"2\",\n" +
            "\t\"x\": \"0.81\",\n" +
            "\t\"y\": \"${y}\",\n" +
            "\t\"type\": \"text\",\n" +
            "\t\"value\": \"${total${num}}\",\n" +
            "\t\"fontSize\": \"10\"\n" +
            "}";
    @Test
    public void clac() {
        int x = 17;
        Double p = 0.2765;
        double step=0.0379;
                StringBuffer buffer = new StringBuffer();
                buffer.append(header);
        for (int i = 0; i < x; i++) {
            Double np = FsUtils.mulDouble(step, i);
            np=FsUtils.addDouble(np,p);
            int  xx=i+1;
            buffer.append(",");
            buffer.append(ret.replace("${y}", np.toString()).replace("${num}", xx + ""));
        }

        int y=13;
        Double p1=0.1015;
        Double step1 =0.0379;
        Double step2=0.039;
        for (int i1 = 0; i1 < y; i1++) {
            Double np1 = FsUtils.mulDouble(step1, i1);
            if(i1>4){
                np1=np1+step2;
            }
            //Double np1 = FsUtils.mulDouble(step1, i1);
            np1=FsUtils.addDouble(np1,p1);
            int  xx1=i1+1+17;
            buffer.append(",");
            buffer.append(page2.replace("${y}", np1.toString()).replace("${num}", xx1 + ""));
        }
        buffer.append("]");
        System.out.println(buffer.toString());

    }
}
