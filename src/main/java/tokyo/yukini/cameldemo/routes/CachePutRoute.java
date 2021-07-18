package tokyo.yukini.cameldemo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ignite.IgniteConstants;
import org.springframework.stereotype.Component;

@Component
public class CachePutRoute extends RouteBuilder {

    private static final String CACHE_NAME = "test01";

    @Override
    public void configure() throws Exception {
        from("timer:triggerPut?period=1000") // 1000ミリ秒毎に実行
                .routeId("ignite_put_route").setHeader(IgniteConstants.IGNITE_CACHE_KEY, simple("testkey01")) // キャッシュのキーをtestkey01に指定
                .setBody(simple("${date:now:yyyy-MM-dd HH:mm:ss}")) // メッセージのBODYに現在の日時を設定
                .to("ignite-cache:" + CACHE_NAME + "?operation=PUT") // キャッシュtest01へPUT
                .log("KEY=testkey01, PUT = ${body}"); // 取得したキャッシュの内容をログに表示

        // from("timer:triggerGet?period=1000") // 1000ミリ秒毎に実行
        // .routeId("ignite_get_route").setHeader(IgniteConstants.IGNITE_CACHE_KEY,
        // simple("testkey01")) // キャッシュのキーをtestkey01に指定
        // .to("ignite-cache:" + CACHE_NAME + "?operation=GET") // キャッシュtest01からGET
        // .log("KEY=testkey01, GET = ${body}"); // 取得したキャッシュの内容をログに表示
    }
}
