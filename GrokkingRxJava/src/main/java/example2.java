import rx.Observable;
import rx.functions.Action1;

/**
 * Created by matie on 23/04/15.
 */
public class example2 {
    public static void main(String[] args){
        Observable<String> myObservable = Observable.just("Hello World using JUST!");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable.subscribe(onNextAction);
    }
}
