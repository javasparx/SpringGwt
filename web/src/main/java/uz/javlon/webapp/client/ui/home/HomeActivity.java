/**
 *
 */
package uz.javlon.webapp.client.ui.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import uz.javlon.webapp.client.application.Application;
import uz.javlon.webapp.client.application.base.activity.AbstractBaseActivity;

/**
 * @author ivangsa
 */
public class HomeActivity extends AbstractBaseActivity {


    private final HomeView view;

    @Inject
    public HomeActivity(final Application application, final HomeView view) {
        super(application);
        this.view = view;
        setTitle(i18n.home_title());
        setBodyId("home");
        setBodyClassname("home");
    }

    /* (non-Javadoc)
     * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        panel.setWidget(view);
        setDocumentTitleAndBodyAttributtes();
    }

}
