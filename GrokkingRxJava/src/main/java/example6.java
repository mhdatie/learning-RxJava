import rx.Observable;
import rx.functions.Func1;

/**
 * Created by matie on 23/04/15.
 */
public class example6 {
    public static void main(String[] args){

        Observable.just("Hello World!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(i -> System.out.println(Integer.toString(i)));


        Observable.just("Hello World!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));

        //double map it!
        Observable.just("Hello World!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(s -> s + " -Mo")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));

    }
}
