package sun.focusblog.admin.components.pagination;

/**
 * Created by root on 2015/12/13.
 * <p/>
 * pagination entity
 */
public class Pagination {
    private int num = 1;    // page start index
    private int size = 10;  // page size
    private int count = 0;  // total count

    public Pagination() {
    }

    public Pagination(int count) {
        this.count = count;
    }

    public Pagination(int size, int count) {
        this(count);
        this.size = size;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
