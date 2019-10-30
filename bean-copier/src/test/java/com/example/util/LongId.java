package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :)
 *
 * @author MiaoOne
 * @since 2019/8/14 17:10
 */
public class LongId {
    private static long tmpID = 0;

    private static boolean tmpIDlocked = false;

    public static long getId()
    {
        long ltime = 0;
        while (true)
        {
            if(!tmpIDlocked)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.parseLong(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date())) * 10000;
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}
