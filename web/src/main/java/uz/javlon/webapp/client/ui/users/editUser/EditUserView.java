package uz.javlon.webapp.client.ui.users.editUser;

import java.util.List;

import uz.javlon.webapp.client.application.base.view.ProxyEditView;
import uz.javlon.webapp.client.proxies.LabelValueProxy;
import uz.javlon.webapp.client.proxies.RoleProxy;
import uz.javlon.webapp.client.proxies.UserProxy;

public interface EditUserView extends ProxyEditView<UserProxy, EditUserView> {

	void setCountries(List<LabelValueProxy> countries);
	void setAvailableRoles(List<RoleProxy> roles);
}