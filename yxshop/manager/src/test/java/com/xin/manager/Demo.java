package com.xin.manager;

import com.xin.manager.controller.ItemController;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;

public class Demo {
    @Test
    public void fun1(){
        System.out.println(2>>1);
    }
    @Test
    public void fun() throws IllegalAccessException {
        po p = new po(1);
        Field[] fields = p.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.get(p)==null){
                System.out.println("是空的");
            }
        }
        System.out.println();
    }

    class po{
        private int a;
        private Boolean b;

        public po(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "po{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public Boolean getB() {
            return b;
        }

        public void setB(Boolean b) {
            this.b = b;
        }
    }
}
