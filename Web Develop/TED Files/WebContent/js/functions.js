var includeBackButton = false;

/**
 * Open the webpage at the specified URL inside of the content panel
 * on the webpage.
 * 
 * @param url The url of the page to load.
 * @param cache Whether or not to allow the page to be saved in the cache.
 */
function openPage(url, cache)
{
    if (cache === undefined)
    {
        cache = true;
    }
    
    // Whether or not to allow the page to be saved in the cache.
    if (!cache)
    {
        var date = new Date();
        
        var cacheParamValue = date.getTime();
        url = url + "?cache=" + cacheParamValue;
    }
	if (typeof iframe === 'undefined')
	{
		window.location.replace(url);
	}
    
    $("#page-content-error").css({ "display" : "none" });
    iframe.css({ "display" : "inline" });
    
    iframe.attr("src", url);
    
    // Focus the iframe right off the bat.
    iframe.focus();
}

function goBack()
{
	window.history.back();
}

function goForward()
{
	window.history.forward();
}

function checkPage()
{

}

function addEvent(element, event, fn)
{
    if (element.addEventListener)
        element.addEventListener(event, fn, false);
    else if (element.attachEvent)
        element.attachEvent('on' + event, fn);
}

addEvent(window, 'load', checkPage);