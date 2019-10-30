package com.example.util;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :)
 *
 * @author MiaoOne
 * @since 2019/8/14 17:01
 */
public class LongUUID {
    private boolean wrapping;
    private long count = 0L;

    public LongUUID(boolean wrap, long initialValue) {
        this.wrapping = wrap;
        this.count = initialValue;
    }

    public long maxValue() {
        return 9223372036854775807L;
    }

    public long minValue() {
        return 0L;
    }

    public boolean isWrap() {
        return this.wrapping;
    }

    public void setWrap(boolean wrap) {
        this.wrapping = wrap;
    }

    public Long nextLongIdentifier() {
        long value = 0L;
        if (this.wrapping) {
            synchronized(this) {
                value = this.count++;
            }
        } else {
            synchronized(this) {
                if (this.count == 9223372036854775807L) {
                    throw new IllegalStateException("The maximum number of identifiers has been reached");
                }

                value = this.count++;
            }
        }

        return value;
    }
}
