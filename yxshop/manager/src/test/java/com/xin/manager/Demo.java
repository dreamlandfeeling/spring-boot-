package com.xin.manager;

import com.xin.manager.controller.ItemController;
import com.xin.manager.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.lang.reflect.Field;

public class Demo {
    @Test
    public void fun1(){
        System.out.println(2>>1);
    }
    @Test
    public void fun() throws IllegalAccessException {
        Object result = MD5Utils.md5("common");
        System.out.println(result);
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
