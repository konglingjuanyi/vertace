package io.vertace.http;

import io.vertace.VertaceWeb;
import io.vertace.core.Component;
import io.vertace.http.handler.ParamHandler;
import io.vertace.http.handler.HttpRouteHandler;
import io.vertace.http.handler.RequestResponseHandler;
import io.vertx.core.http.HttpMethod;

import java.util.LinkedList;
import java.util.List;

public abstract class HttpRestRouter implements Component {

    private String mountPoint;
    private List<HttpRoute> listOfHttpRoutes = new LinkedList<>();

    public final void initialize(VertaceWeb vertaceWeb) {
        listOfHttpRoutes.forEach(route -> route.register(vertaceWeb));
    }

    protected HttpRestRouter subRouteOf(String mountPoint) {
        this.mountPoint = mountPoint;
        return this;
    }

    protected HttpRestRouter any(String path, RequestResponseHandler handler) {
        //TODO
        return this;
    }

    protected HttpRestRouter get(String path, RequestResponseHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return this;
    }

    protected HttpRestRouter get(String path, ParamHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return this;
    }

    protected HttpRestRouter route(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        registerHttpRoute(path, httpMethod, handler);
        return this;
    }

    private void registerHttpRoute(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        listOfHttpRoutes
                .add(new HttpRoute(
                        mountPoint == null ? path : mountPoint + path,
                        httpMethod,
                        handler));
    }

}
