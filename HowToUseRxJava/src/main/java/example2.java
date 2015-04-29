import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class example2 {
    public static void main(String[] args){

        Integer[] numbers = {0,1,2,3,4,5,6,7};
        numberFlow(numbers);
    }

    private static void numberFlow(Integer[] nums){
        Observable numberObservable = Observable.from(nums);

        numberObservable.subscribe(
                new Action1<Integer>(){

                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Error in synchronous observable");
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("This observable is finished");
                    }
                }
        );
    }

}
