/**
 *
 */
package uz.javlon.webapp.client.ui.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.Composite;
import uz.javlon.webapp.client.application.Application;
import uz.javlon.webapp.client.application.ApplicationMenu;
import uz.javlon.webapp.client.application.ApplicationResources;
import uz.javlon.webapp.client.application.base.place.EntityProxyPlace;
import uz.javlon.webapp.client.application.base.place.EntitySearchPlace;
import uz.javlon.webapp.client.application.utils.menu.MenuItem;

/**
 * @author ivangsa
 */
public abstract class BaseNavigationBar extends Composite implements PlaceChangeEvent.Handler {

    protected static final ApplicationResources i18n = GWT.create(ApplicationResources.class);

    protected Application application;
    protected ApplicationMenu menu;

    /**
     *
     */
    public BaseNavigationBar() {
        super();
    }

    public void setApplication(final Application application) {
        this.application = application;
        this.menu = application.getMenu();
        this.application.getEventBus().addHandler(PlaceChangeEvent.TYPE, this);
    }

    protected boolean isSamePlace(final Place newPlace, final Place menuPlace) {
        if (newPlace == null || menuPlace == null) {
            return false;
        }

        if (newPlace instanceof EntityProxyPlace && menuPlace instanceof EntityProxyPlace) {
            final EntityProxyPlace newEntityPlace = (EntityProxyPlace) newPlace;
            final EntityProxyPlace menuEntityPlace = (EntityProxyPlace) menuPlace;
            return newEntityPlace.getProxyClass().equals(menuEntityPlace.getProxyClass());
        } else if (newPlace instanceof EntitySearchPlace && menuPlace instanceof EntitySearchPlace) {
            final EntitySearchPlace newEntityPlace = (EntitySearchPlace) newPlace;
            final EntitySearchPlace menuEntityPlace = (EntitySearchPlace) menuPlace;
            return newEntityPlace.getProxyClass().equals(menuEntityPlace.getProxyClass());
        }
        return newPlace.getClass().equals(menuPlace.getClass());
    }

    protected boolean isMenuItemAllowed(final MenuItem menuItem) {
        return application.hasAnyRole(menuItem.getRoles().toArray(new String[menuItem.getRoles().size()]));
    }

    protected MenuItem findCurrentMenuItem(final Place currentPlace) {
        for (final MenuItem menuItem : menu.asList()) {
            if (isSamePlace(currentPlace, menuItem.getPlace())) {
                return menuItem;
            }
        }
        return null;
    }

}
