/*
 * Declare variables to the page elements to make things look cleaner.
 */

/**
 * The navigation panel JQuery selector variable. This holds
 * the visual navigation panel. Use this whenever attaining
 * the dimensions of the navigation panel.
 */
var navPanel;

/**
 * The navigation panel content JQuery selector variable. This
 * is the container of all the contents inside of the navigation
 * panel.
 */
var navContent;

/**
 * The page-content JQuery selector variable. This contains all
 * of the content that is shown in the main content panel of the
 * page.
 */
var content;

/**
 * The iframe JQuery selector variable. This is the variable used
 * whenever the iframe's src needs to be edited or likewise.
 */
var iframe;

/**
 * The bottom-panel JQuery selector variable. This is the variable
 * used to separate the timeline panel (top panel) from the rest
 * of the page's content.
 */
var bottomPanel;

/**
 * This variable holds the value of the width of the navigation
 * panel. This value stays constant throughout the whole execution
 * of the program, no matter the size of the web-page.
 */
var navWidth;



/**
 * Store the old source variable for the iframe. This is used to determine
 * whether or not the src attribute of the iframe has been changed.
 */
var oldSrc;



/**
 * Perform some operations on the navigation panel's links.
 * If the link starts with the string "contentPanel:" then
 * make that link open inside of the content-panel instead
 * of its own page.
 * 
 * @param children The children of the navigation panel.
 *         i.e. the links.
 */
function hrefToPage(children)
{
	// Loop through each child. (i.e. for each <a href=...> link)
    for (var i = 0; i < children.length; i++)
    {
		//grab each individual <a href=...> link
        var child = $(children[i]);
        
        // if the child is a <div> tag
		if (child.prop("tagName").toLowerCase() == "div")
        {
			// set the child to ONLY the first <div> tag...i.e. ignore other <div>s
        	child = child.children().eq(0);
        }
        
        var href = child.attr("href");
        
        child.attr("href", "Javascript: openPage('" + href + "');");
    }
}

/**
 * Run all of the listener code and other stuff when the
 * page is ready for it.
 */
$(document).ready(function()
{
    /*
     * Assign variables to the page elements to make things look cleaner.
     */
    navPanel            = $("#nav-panel");
    navContent          = $("#nav-panel-content");
    content             = $("#page-content");
    bottomPanel         = $("#bottom-panel");
    iframe              = $("#page-content-iframe");
    
    navWidth            = navPanel.outerWidth();
    
	// .content-panel-link must be the direct parent tag id for the links
    hrefToPage(navContent.children(".content-panel-link"));
    
    // Wait for a click on the navPanel.
    //navPanel.click(panelListener);
    //navContent.click(panelListener);
    
    openNavPanel();
    
    oldSrc = iframe.attr("src");
    
    $("body", iframe.contents()).click(function()
    {
    	var newSrc = iframe.attr("src");
    	
    	if (newSrc != oldSrc)
    	{
    		validateURL(newSrc);
    		
    		oldSrc = newSrc;
    	}
    });
    
    openPage("http://www.ecusd7.org/ehs");
});