/**
 * 
 */
package uz.javlon.webapp.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import uz.javlon.model.LabelValue;
import uz.javlon.service.RoleManager;
import uz.javlon.webapp.listener.StartupListener;
import uz.javlon.webapp.server.services.LookupRequestService;

import java.util.*;

/**
 * @author ivangsa
 *
 */
@Component
public class LookupRequestServiceImpl extends AbstractBaseRequest implements LookupRequestService {

	@Autowired
	private RoleManager roleManager;

	/**
	 * 
	 * @return
	 */
	public LookupConstants getApplicationConstants() {
		final Locale locale = LocaleContextHolder.getLocale();
		LookupConstants applicationConstants = new LookupConstants();
		
		applicationConstants.setAvailableRoles(roleManager.getAll());
		applicationConstants.setCountries(buildCountryList(locale));
		
		return applicationConstants;
	}
	
	/**
	 * 
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	public LookupConstants reloadOptions() {
		StartupListener.setupContext(getServletContext());
		return getApplicationConstants();
	}
	
    protected List<LabelValue> buildCountryList(final Locale locale) {
        final String EMPTY = "";
        final Locale[] available = Locale.getAvailableLocales();

        List<LabelValue> countries = new ArrayList<LabelValue>();

        for (int i = 0; i < available.length; i++) {
            final String iso = available[i].getCountry();
            final String name = available[i].getDisplayCountry(locale);

            if (!EMPTY.equals(iso) && !EMPTY.equals(name)) {
                LabelValue country = new LabelValue(name, iso);

                if (!countries.contains(country)) {
                    countries.add(new LabelValue(name, iso));
                }
            }
        }

        Collections.sort(countries, new Comparator<LabelValue>() {
        	@Override
        	public int compare(LabelValue arg0, LabelValue arg1) {
        		return arg0.getLabel().compareTo(arg1.getLabel());			
        	
        	}
        });

        return countries;
    }
}