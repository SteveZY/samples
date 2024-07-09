package zyz.steve.array;


//TODO
public class BitSet {

    byte[] data;

    int size;

    int numOfOne;

    public BitSet(int size) {
        this.size = size;
        data = new byte[size];

    }

    public void fix(int idx) {
        if (numOfOne < size && data[idx] != 1) {
            numOfOne++;
            data[idx] = 1;
        }

    }

    public void unfix(int idx) {
        if (numOfOne > 0 && data[idx]!=0) {
            numOfOne--;
            data[idx] = 0;
        }
    }

    public void flip() {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((data[i] + 1) & 1);
        }
        numOfOne = size - numOfOne;
    }

    public boolean all() {
        return numOfOne == data.length;
    }

    public boolean one() {
        return numOfOne > 0;
    }

    public static void main(String[] args) {
        BitSet bs = new BitSet(5);
        bs.fix(3);
        bs.fix(1);
        bs.flip();
    }

}
