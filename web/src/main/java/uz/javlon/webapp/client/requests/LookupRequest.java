/**
 * 
 */
package uz.javlon.webapp.client.requests;

import uz.javlon.webapp.client.proxies.LookupConstantsProxy;
import uz.javlon.webapp.server.GwtServiceLocator;
import uz.javlon.webapp.server.services.LookupRequestService;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author ivangsa
 *
 */
@Service(value = LookupRequestService.class, locator = GwtServiceLocator.class)
public interface LookupRequest extends RequestContext {

	public Request<LookupConstantsProxy> getApplicationConstants();

	public Request<LookupConstantsProxy> reloadOptions();
}
