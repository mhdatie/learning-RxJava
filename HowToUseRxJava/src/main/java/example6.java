import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func3;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * This is a bit complex compared to the previous examples. In this one we
 * have 3 asynchronous Observables that are emitting item(s). One of them
 * uses flatMap() to transform the emitted item(s) from another into an Observable
 * of its own.
 *
 * Then, uses zip() operator to combine all emitted Maps into one, and chains it
 * with map() operator to format and obtain the needed data only.
 *
 * Remember to keep this kind of filtering to the operators and not in the
 * subscriber's body as it's only responsible for listening/displaying/forwarding
 * the data.
 *
 */
public class example6 {
    public static void main(String[] args){

        getVideoForUser(47,27).subscribe(new Action1<Map>() {
            @Override
            public void call(Map map) {
                System.out.println(map.get("movie-title"));
                System.out.println(map.get("movie-language"));
                System.out.println(map.get("movie-bookmark"));
            }
        });

    }

    private static Observable<Map> getVideoForUser(final int userId, final int videoId) {

        Observable<Map> userObservable = getUserData(userId).map(new Func1<Map, Map>() {
            @Override
            public Map call(Map map) {
                return map;
            }
        });

        Observable<Map> bookmarkObservable = getVideoBookmark(userId, videoId).map(new Func1<Map, Map>() {
            @Override
            public Map call(Map map) {
                return map;
            }
        });

        Observable<Map> metaObservable = userObservable.flatMap(new Func1<Map, Observable<Map>>() {
            @Override
            public Observable<Map> call(Map map) {
               return getVideoMetaData(videoId, (String) map.get("language"));
            }
        });

        Observable<Map> finalObservable = Observable.zip(userObservable, bookmarkObservable, metaObservable, new Func3<Map, Map, Map, Map>() {
            @Override
            public Map<String, Map> call(Map userMap, Map bookmarkMap, Map metaMap) {
                Map<String, Map> zipData = new HashMap<String, Map>();
                zipData.put("user-map",userMap);
                zipData.put("bookmark-map",bookmarkMap);
                zipData.put("meta-map",metaMap);
                return zipData;
            }
        }).map(data -> returnFinalDataForm(data));

        return finalObservable;

    }

    private static Map returnFinalDataForm(Map<String,Map> data) {
        Map<String, String> finalMap = new HashMap<String,String>();
        finalMap.put("movie-title", (String)data.get("meta-map").get("videoTitle"));
        finalMap.put("movie-language", (String)data.get("user-map").get("language"));
        finalMap.put("movie-bookmark", (String)data.get("bookmark-map").get("position"));
        return finalMap;
    }


    private static Observable<Map> getUserData(final int userId){
        return Observable.create(new Observable.OnSubscribe<Map>() {
            @Override
            public void call(Subscriber<? super Map> subscriber) {
                Map<String,String> userData = new HashMap<String, String>();
                userData.put("userName","name");
                userData.put("language","preferred language");
                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(userData);
                    subscriber.onCompleted();
                }
            }
        });
    }

    private static Observable<Map> getVideoBookmark(final int userId, final int videoId){
        return Observable.create(new Observable.OnSubscribe<Map>() {
            @Override
            public void call(Subscriber<? super Map> subscriber) {
                Map<String,String> videoData = new HashMap<String, String>();
                videoData.put("position","0");
                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(videoData);
                    subscriber.onCompleted();
                }
            }
        });
    }

    private static Observable<Map> getVideoMetaData(final int videoId, final String language){
        return Observable.create(new Observable.OnSubscribe<Map>() {
            @Override
            public void call(Subscriber<? super Map> subscriber) {
                Map<String,String> videoMeta = new HashMap<String, String>();
                videoMeta.put("videoId", videoId+"");
                videoMeta.put("videoTitle","title");
                videoMeta.put("videoDirector","director");
                videoMeta.put("videoDuration","duration");
                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(videoMeta);
                    subscriber.onCompleted();
                }
            }
        });
    }

}
