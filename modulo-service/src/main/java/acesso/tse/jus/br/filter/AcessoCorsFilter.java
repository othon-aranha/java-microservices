package acesso.tse.jus.br.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.FilterDefinition;
// import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class AcessoCorsFilter implements Filter {

	public AcessoCorsFilter() {
		super();
	}
	
	public final void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
	    final HttpServletResponse response = (HttpServletResponse) res;
	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

	    // without this header jquery.ajax calls returns 401 even after successful login and SSESSIONID being succesfully stored.
	    response.setHeader("Access-Control-Allow-Credentials", "true");

	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Origin, Content-Type, Version");
	    response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");

	    final HttpServletRequest request = (HttpServletRequest) req;
	    if (!request.getMethod().equals("OPTIONS")) {
	        chain.doFilter(req, res);
	    } else {
	        // do not continue with filter chain for options requests
	    }
	}

	public void destroy() {

	} 

	public void init(FilterConfig filterConfig) throws ServletException {       
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterDefinition getFilterDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameter(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameterList(String name, Collection values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter setParameterList(String name, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate() throws HibernateException {
		// TODO Auto-generated method stub
		
	}	
}
