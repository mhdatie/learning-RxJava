import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Filtering an asynchronous {@link Observable} using the built-in skip() method.
 *
 * Asynchronous simply means executing it on a separate thread.
 */
public class example4 {

    public static void main(String[] args){

        Observable asyncObservable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object subscriber) {
                final Subscriber mySubscriber = (Subscriber)subscriber;

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ; i < 10 ; i++){
                            if(!mySubscriber.isUnsubscribed()){
                                mySubscriber.onNext("Pushing value from async thread" + i);
                            }
                        }
                    }
                });

                thread.start();
            }
        });

        //Skip the first 5 emitted items.
        asyncObservable.skip(5).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }
}
