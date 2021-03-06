package me.escoffier.reactive_summit.demo1;

import net.redpipe.engine.resteasy.FileResource;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@ApplicationScoped
@Path("/")
public class MyWebResource extends FileResource {

  @Inject
  private Neo neo;

  @Produces(MediaType.SERVER_SENT_EVENTS)
  @GET
  @Path("/neo")
  public Publisher<String> stream() {
    return neo.state();
  }

  @Path("webroot{path:(/.*)?}")
  @GET
  public Response serveStaticFiles(@PathParam("path") String path) throws IOException {
    return super.getFile(path);
  }

}
