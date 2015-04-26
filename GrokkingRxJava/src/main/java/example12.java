import rx.Observable;
import rx.Subscriber;

/**
 * onCompleted() and onError()
 * Created by matie on 26/04/15.
 */
public class example12 {
    public static void main(String[] args){
        Observable.just("Hello World")
                .map(s -> (String)potentialException(s))
                .map(s -> (String)anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Error");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static Object anotherPotentialException(String s) {
        //no need to throw errors here. Point made
       return s;
    }

    private static Object potentialException(String s) {
        if(s.contains("World")){
            try {
                throw new Throwable();
            } catch (Throwable throwable) {
                return throwable;
            }
        }else{
            return s;
        }
    }

}
