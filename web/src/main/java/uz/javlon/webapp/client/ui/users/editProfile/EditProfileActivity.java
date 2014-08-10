/**
 * 
 */
package uz.javlon.webapp.client.ui.users.editProfile;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import uz.javlon.webapp.client.application.Application;
import uz.javlon.webapp.client.application.base.activity.AbstractProxyEditActivity;
import uz.javlon.webapp.client.proxies.RoleProxy;
import uz.javlon.webapp.client.proxies.UserProxy;
import uz.javlon.webapp.client.requests.UserRequest;
import uz.javlon.webapp.client.ui.home.HomePlace;
import uz.javlon.webapp.client.ui.users.editUser.EditUserView;
import uz.javlon.webapp.client.ui.users.updatePassword.UpdatePasswordPlace;

/**
 * @author ivangsa
 *
 */
public class EditProfileActivity extends AbstractProxyEditActivity<UserProxy> implements EditUserView.Delegate {

    private final EditProfileView editProfileView;

    @Inject
    public EditProfileActivity(final Application application, final EditProfileView editUserView) {
        super(application, editUserView);
        editProfileView = editUserView;
        setTitle(i18n.userProfile_title());
        setDeleteConfirmation(i18n.delete_confirm(i18n.userList_user()));
    }


    @Override
    public String getSavedMessage() {
        return application.getI18n().user_saved();
    }

    @Override
    public String getDeletedMessage() {
        return application.getI18n().user_deleted(entityProxy.getUsername());
    }

    @Override
    public void start(final AcceptsOneWidget display, final EventBus eventBus) {
        final boolean isFullyAuthenticated = application.isUserInRole(RoleProxy.FULLY_AUTHENTICATED);
        if (!isFullyAuthenticated) {
            shell.addMessage(i18n.userProfile_cookieLogin(), AlertType.WARNING);
        }

        if (editProfileView != null) {
            editProfileView.setAvailableRoles(application.getLookupConstants().getAvailableRoles());
            editProfileView.setCountries(application.getLookupConstants().getCountries());
        }

        super.start(display, eventBus);
    }


    @Override
    protected String getEntityId() {
        //return a not null entityId so super does not try to create a new profile
        return "x";
    }

    @Override
    protected RequestContext createProxyRequest() {
        return requests.userRequest();
    }

    @Override
    protected Request<UserProxy> loadProxyRequest(final RequestContext requestContext, final String proxyId) {
        return ((UserRequest) requestContext).editProfile();
    }


    @Override
    protected RequestContext saveOrUpdateRequest(final RequestContext requestContext, final UserProxy proxy) {
        ((UserRequest) requestContext).editProfile(proxy);
        return requestContext;
    }

    @Override
    protected RequestContext deleteRequest(final RequestContext requestContext, final UserProxy proxy) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Place previousPlace() {
        return new HomePlace();
    }

    @Override
    protected Place nextPlace(final boolean saved) {
        return new EditProfilePlace();
    }

    @Override public void updatePasswordClicked() {
        placeController.goTo(new UpdatePasswordPlace(entityProxy.getUsername()));
    }

}
