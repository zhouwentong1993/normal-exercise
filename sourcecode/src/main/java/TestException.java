/**
 * Created by zhouwentong on 2018/6/7.
 * 继承 Exception 的是受限异常，
 * 继承 RuntimeException 的是运行时异常
 */
public class TestException {

    public void test() {
//        throw new MyException();
        throw new MyRuntimeException();
    }

    class MyException extends Exception {

    }

    class MyRuntimeException extends RuntimeException {

    }

}
