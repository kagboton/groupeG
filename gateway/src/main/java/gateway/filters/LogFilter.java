package gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class LogFilter extends ZuulFilter {

     Logger log = LoggerFactory.getLogger(this.getClass());

     @Override
     public String filterType() {
         return "pre";
     }

     @Override
     public int filterOrder() {
         return 1;
     }

     @Override
     public boolean shouldFilter() {
         return true;
     }

     @Override
     public Object run() throws ZuulException {
         RequestContext ctx = RequestContext.getCurrentContext();
         HttpServletRequest request = ctx.getRequest();
        // log.info(String.format("%s request to %s" + request.getMethod()));
         log.info("******** Requete interceptée : " + request.getRequestURL());
         return null;
     }
}
