package com.example.springboot.common;

import java.util.Calendar;
import java.util.TimeZone;

public class DateStringInt {
        private static String mYear;
        private static String mMonth;
        private static String mDay;

        public static String StringData(){
            final Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
            return mYear + "-" + mMonth + "-" + mDay;
        }
        public static int time(String time){
            String[] split=time.split("-");
            return 365*Integer.parseInt(split[0])+30*Integer.parseInt(split[1])+Integer.parseInt(split[2]);
        }
        public static int picker(String time){
            String[] split=time.split("-");
            return 24*60*(365*Integer.parseInt(split[0])+30*Integer.parseInt(split[1])+Integer.parseInt(split[2]))+60*Integer.parseInt(split[3])+Integer.parseInt(split[4]);
        }
        public static boolean deadline(String now, String deadline){

            return time(now) >=time(deadline);
        }
        public static int datenum(String y,String m){
            int year=Integer.parseInt(y);
            int month=Integer.parseInt(m);
            int n = 0;
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> {
                    n = 31;
                    return 31;
                }
                case 4, 6, 9, 11 -> {
                    n = 30;
                    return 30;
                }
                case 2 -> {
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        n = 29;
                        return 29;

                    } else {
                        n = 28;
                        return 28;
                    }
                }
            }
            return n;
        }
}
