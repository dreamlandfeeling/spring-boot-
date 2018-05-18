package com.xin.top;

import com.xin.top.mapper.TbContentMapper;
import com.xin.top.model.TbContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TopApplicationTests <T>{

	private TbContentMapper tbContentMapper;
    public static TopApplicationTests t;


	static class OOMObject{

    }
	@Test
	public void contextLoads() throws InterruptedException {
        Random random = new Random();
        int i =0;
        int a = 0;
        while (true){
            if(i==10000){
                break;
            }
            i++;
            a = random.nextInt(1);
            if(a==1){
                System.out.println(a);
            }
        }
    }

    public void isAlive(){
        System.out.println("ya,i'm still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        TopApplicationTests.t = this;
    }

    public <T> T show(T... a){
        for (T t : a) {
            System.out.println(t.toString());
        }
        return a[0];
    }

    @Test
    public void feef() {
        String[] s = {"aa","bb","cc"};
        List<String> strlist = Arrays.asList(s);
        for(String str:strlist){
            System.out.println(str);
        }
        System.out.println("------------------------");
        //基本数据类型结果打印为一个元素
        int[] i ={11,22,33};
        List<int[]> intlist = Arrays.asList(i);
        for (int[] ints : intlist) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
        System.out.println("------------------------");
        Integer[] ob = {11,22,33};
        List<Integer> oblist = Arrays.asList(ob);
        for(int a:oblist){
            System.out.println(a);
        }
        System.out.println("------------------------");
    }

}
