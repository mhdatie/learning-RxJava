import rx.Observable;
import rx.Subscriber;

import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;

/**
 * Created by matie on 23/04/15.
 */
public class example1 {
    public static void main(String[] args){

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello world!");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //do nothing
            }

            @Override
            public void onError(Throwable throwable) {
                //do nothing
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);


    }
}
