package interview;

import java.util.Arrays;

/**
 * Date 03/08/18
 * @author Benjamin Weis
 *
 * Create a data structure that can store an array of bits where the actual space of the bit is a single bit.
 *
 */
public class BitArray {
    private static final int WORD_SIZE = 64;
    private long[] data;
    private int size;

    BitArray() {
        data = new long[1];
        size = 0;
    }

    public void set(int bit, boolean value) {
        if (bit < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int requiredLength = bit / WORD_SIZE;
        while (requiredLength >= data.length) {
            resizeArray();
        }

        long word = data[requiredLength];
        int posBit = bit % WORD_SIZE;

        if (value) {
            word |= 1L << posBit;
        } else {
            word &= ~(1L << posBit);
        }

        System.out.println("Setting bit " + bit + " to " + value + "\tIndex " + requiredLength + "\tWord: " + data[requiredLength] + " -> " + word);
        data[requiredLength] = word;

        if (bit >= size) {
            size = bit + 1;
        }
    }

    public boolean get(int bit) {
        int requiredLength = bit / WORD_SIZE;
        if (requiredLength > data.length) {
            throw new IllegalArgumentException();
        }

        int index = bit % WORD_SIZE;
        return (data[requiredLength] & (1L << index)) != 0;
    }

    public void add(boolean value) {
        set(size, value);
    }

    private void resizeArray() {
        System.out.println("Resizing Array from " + data.length + " words to " + data.length * 2 + " words");
        data = Arrays.copyOf(data, data.length * 2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            boolean value = get(i);
            builder.append(i).append(" = ").append(value).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        BitArray bs = new BitArray();
        for (int i = 0; i <= 35; i++) {
            bs.add(true);
        }
        bs.add(false);
        bs.add(false);
        bs.add(false);
        bs.add(false);
        bs.add(true);
        bs.set(100, true);
        bs.set(0, false);
        bs.set(2048, true);

        System.out.println(bs);
    }
}
