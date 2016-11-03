package io.vertace.http;

import io.hackable.Hackable;
import io.vertace.core.VertaceVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.ArrayList;
import java.util.List;

public abstract class VertaceHttpServer extends VertaceVerticle<VertaceHttpServer> {

    private Integer port;
    private Router router;
    private HttpServer httpServer;
    private List<HttpRestRouter> listOfHttpRestRouters = new ArrayList<>();

    public VertaceHttpServer() {
        this.port = port();
    }

    public abstract Integer port();

    @Override
    public void start() {
        trigger("beforeDeployVerticle", this.getClass(), vertx);
        router = Router.router(vertx);
        httpServer = vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(port);
        System.out.println("Server running in port: " + port);
    }

    public HttpServer getVertxHttpServer() {
        return httpServer;
    }

}
