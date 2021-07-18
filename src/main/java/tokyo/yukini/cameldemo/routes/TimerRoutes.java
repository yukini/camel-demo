package tokyo.yukini.cameldemo.routes;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TimerRoutes extends RouteBuilder {
    @Qualifier("yukinin")
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure() throws Exception {
        from("timer:foo").to("log:bar").to("sql:select id, name from weather?dataSource=yukinin").to("log:foo");
    }

}
