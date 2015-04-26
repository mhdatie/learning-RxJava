import rx.Observable;
import rx.Subscription;

/**
 * Subscriptions example
 *
 * To make this example have more sense, run the Observable on a different thread, and unsubscribe.
 * Otherwise, the Subscriver/Observer unsubscribes BY DEFAULT as soon as the OnComplete() is called, in this case,
 * printing the string.
 *
 * Created by matie on 26/04/15.
 */
public class example13 {
    public static void main(String[] args){

        Subscription subscription = Observable.just("Sample subscription").subscribe(s -> System.out.println(s));
        subscription.unsubscribe();
        if(subscription.isUnsubscribed())
            System.out.println("Un-subscribed!");
        /**
         * What's nice about how RxJava handles unsubscribing is that it stops the chain.
         * If you've got a complex chain of operators, using unsubscribe will terminate wherever
         * it is currently executing code.
         */
    }

}
