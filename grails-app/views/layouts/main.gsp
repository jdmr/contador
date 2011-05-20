<!DOCTYPE html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<html>
    <head>
        <meta charset="utf-8">
        <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
             Remove this if you use the .htaccess -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title><g:layoutTitle default="Grails" /></title>

        <!-- Mobile viewport optimized: j.mp/bplateviewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->

        <!-- CSS: implied media="all" -->
        <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />

        <!-- All JavaScript at the bottom, except for Modernizr and Respond.
             Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries -->
        <script src="${resource(dir:'js/libs',file:'modernizr-1.7.min.js')}"></script>
        <script src="${resource(dir:'js/libs',file:'respond.min.js')}"></script>

        <!-- JavaScript at the bottom for fast page loading -->
        <!-- Grab Google CDNs jQuery, with a protocol relative URL; fall back to local if offline -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="${resource(dir:'js/libs',file:'jquery-1.6.1.min.js')}">\x3C/script>')</script>


        <!-- scripts concatenated and minified via ant build script-->
        <script src="${resource(dir:'js',file:'plugins.js')}"></script>
        <script src="${resource(dir:'js',file:'script.js')}"></script>
        <!-- end scripts-->

        <g:layoutHead />
    </head>
    <body>
        <div id="container">
            <header>
                <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
            </header>
            <div id="main" role="main">
                <g:layoutBody />
            </div>
            <footer>
                
            </footer>
        </div>

    </body>
</html>
