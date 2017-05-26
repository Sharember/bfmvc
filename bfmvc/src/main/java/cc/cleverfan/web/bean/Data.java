package cc.cleverfan.web.bean;

/**
 * 返回数据
 */
public class Data<T> {

    //数据
    private T data;

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
