package com.example.learning;

import com.example.learning.java.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-27
 * <p>
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        List<Data> list = new ArrayList<>();
        list.add(new Data(100));

        List<Data> newList = new ArrayList<>(list);
        Utils.println(list.get(0) == newList.get(0));
    }


    static class Data {
        int i;

        public Data(int i) {
            this.i = i;
        }
    }
}
