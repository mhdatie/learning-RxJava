import rx.Observable;
import rx.functions.Action1;

/**
 * Created by matie on 23/04/15.
 */
public class example3 {
    public static void main(String[] args){
        Observable.just("Hello World using JUST + CHAINING!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }
}
