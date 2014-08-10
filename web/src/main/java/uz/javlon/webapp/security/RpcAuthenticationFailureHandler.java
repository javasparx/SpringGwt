package uz.javlon.webapp.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author ivangsa
 *
 */
public class RpcAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private RequestMatcher requestMatcher = new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");
	
	public void setRequestMatcher(RequestMatcher requestMatcher) {
		this.requestMatcher = requestMatcher;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if(isRpcRequest(request, response)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
	
	protected boolean isRpcRequest(HttpServletRequest request, HttpServletResponse response) {
		return requestMatcher.matches(request);
	}

}
