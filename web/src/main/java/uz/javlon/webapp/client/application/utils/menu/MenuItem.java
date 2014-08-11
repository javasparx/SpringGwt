package uz.javlon.webapp.client.application.utils.menu;

import com.google.gwt.dom.client.Element;
import com.google.gwt.place.shared.Place;
import uz.javlon.webapp.client.proxies.RoleProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MenuItem implements Iterable<MenuItem> {

    private final String title;
    private final Place place;
    private final List<String> roles = new ArrayList<String>();
    private MenuItem parent;
    private final List<MenuItem> children = new ArrayList<MenuItem>();
    private Element element;

    /**
     * @param title
     * @param roles
     */
    public MenuItem(final String title, final String... roles) {
        this(title, null, roles);
    }

    public MenuItem(final String title, final Place place, final String... roles) {
        super();
        this.title = title;
        this.place = place;
        this.roles.addAll(Arrays.asList(roles));
        if (this.roles.isEmpty()) {
            this.roles.add(RoleProxy.ANONYMOUS);
        }
    }

    public String getTitle() {
        return title;
    }

    public Place getPlace() {
        return place;
    }

    public List<String> getRoles() {
        return roles;
    }

    public MenuItem getParent() {
        return parent;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(final Element widget) {
        this.element = widget;
    }

    public void add(final MenuItem item) {
        item.parent = this;
        children.add(item);
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return children.iterator();
    }

    public boolean isLeafMenuItem() {
        return children.isEmpty();
    }
}
