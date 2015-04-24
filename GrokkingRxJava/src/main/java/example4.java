import rx.Observable;

/**
 * Created by matie on 23/04/15.
 *
 * JAVA 8
 */
public class example4 {
    public static void main(String[] args){
        Observable.just("Hello World using JUST + CHAINING + LAMBDAS")
                .subscribe(s -> System.out.println(s));
    }
}
