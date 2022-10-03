package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
	public static final String TITLE_TEXTBOX ="css=h1.wp-block-post-title";
	public static final String BODY_BUTTON ="css=p[class*=block-editor-default]";
	public static final String BODY_TEXTBOX ="css=p.block-editor-rich-text__editable";
	public static final String PUBLISH_OR_UPDATE_BUTTON ="xpath=//div[@aria-label='Editor top bar']//button[contains(@class,'editor-post-publish-button')]";
	public static final String PRE_PUBLISH_BUTTON ="xpath=//div[@aria-label='Editor publish']//button[contains(@class,'editor-post-publish-button')]";
	public static final String PUBLISHED_OR_UPDATED_MESSAGE ="xpath=//div[@class='components-snackbar__content' and text()='%s']";
	
}
