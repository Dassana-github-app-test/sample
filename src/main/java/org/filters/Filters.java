package org.filters;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
class Filters {

    @ServerRequestFilter(preMatching = true)
    public void preMatchingFilter(ContainerRequestContext requestContext) {
        // make sure we don't lose cheese lovers
        if("yes".equals(requestContext.getHeaderString("Cheese"))) {
            requestContext.setRequestUri(URI.create("/cheese"));
        }
    }

    @ServerRequestFilter
    public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {
        // only allow GET methods for now
        if(ctx.getMethod().equals(HttpMethod.GET)) {
            return Optional.of(RestResponse.status(Response.Status.METHOD_NOT_ALLOWED));
        }
        return Optional.empty();
    }

}