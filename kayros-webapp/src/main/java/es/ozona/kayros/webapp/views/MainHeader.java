package es.ozona.kayros.webapp.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.vaadin.flow.helper.AsyncManager;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainHeader extends HorizontalLayout {

	/**
	 * Serial ID 
	 */
	private static final long serialVersionUID = 8403928240220691287L;
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	private static final String CHECK_IN = "Registrar entrada";
	private static final String CHECK_OUT = "Registar salida";

	protected final Button checkinButton;
	protected final Div usernameText;
	protected final Icon profileIcon;
	protected final Div clock;
	protected UserDetailVO userDetail;

	public MainHeader() {
		this(new UserDetailVO());
	}

	public MainHeader(UserDetailVO userDetailVO) {
		
		this.userDetail = userDetailVO;
		userDetail.setCheckStatus(CheckStatus.CHECKOUT);
		this.checkinButton = buildCheckinButton();
		this.usernameText = buildUsernameText();
		this.profileIcon = buildProfile();
		this.clock = buildClock();
		
		this.add(checkinButton);
		this.add(clock);
		this.add(usernameText);
		this.add(profileIcon);
		this.setWidthFull();
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.END);
		//checkinButton.setAutofocus(true);
	}

	private void updateClock() {
		clock.setText(ZonedDateTime.now().format(DATE_TIME_FORMATTER));
	}

	public UserDetailVO getUserState() {
		return userDetail;
	}

	public void setUserState(UserDetailVO userDetail) {
		this.userDetail = userDetail;
		usernameText.setText(userDetail.getLogin());

	}

	private Button buildCheckinButton() {
		final Button button = new Button(
				userDetail.getCheckStatus().equals(CheckStatus.CHECKIN) ? CHECK_OUT : CHECK_IN);
		button.setMinWidth("160px");
		button.addClickListener(e -> {
			switch (userDetail.getCheckStatus()) {
			case CHECKIN: {
				button.setText(CHECK_IN);
				userDetail.setCheckStatus(CheckStatus.CHECKOUT);
				break;
			}
			case CHECKOUT: {
				button.setText(CHECK_OUT);
				userDetail.setCheckStatus(CheckStatus.CHECKIN);
				break;
			}
			default:
				break;
			}
		});
		return button;
	}

	private Div buildUsernameText() {
		final Div text = new Div();
		text.setText(userDetail.getLogin());
		//text.setMinWidth("90px");
		return text;
	}

	private Icon buildProfile() {
		Icon logoV = new Icon(VaadinIcon.USER);
		logoV.getStyle().set("cursor", "pointer");
		logoV.getStyle().set("margin-right", "5px");
		
		ContextMenu context = new ContextMenu(logoV);
		context.setOpenOnClick(true);
		Stream.of("Salir").forEach(context::addItem);
		context.getItems().get(0).addClickListener(e -> UI.getCurrent().getPage().executeJs("window.location.href=$0", "logout"));

		return logoV;
	}

	private Div buildClock() {
		final Div clock = new Div();
		clock.setText(ZonedDateTime.now().format(DATE_TIME_FORMATTER));
		clock.getStyle().set("margin", "5px");
		clock.setMinWidth("30px");
		AsyncManager.register(clock, a -> {
			while (true) {
				a.push(() -> updateClock());
				Thread.sleep(60000);
			}
		});
		return clock;
	}
}
