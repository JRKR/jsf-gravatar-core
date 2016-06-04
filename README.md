=================
JSF Gravatar Core Jar
=================
Release 1.0 21/06/2014
=================
<a href='http://home.jrkr.me/jenkins/job/jsf-gravatar'><img src='http://home.jrkr.me/jenkins/buildStatus/icon?job=jsf-gravatar&style=plastic'></a>


jsf-component for gravatar with own renderer.
Extension of graphicImage jsf component.

no value is required in the component (enhancement is to allow this ot be an alternative image url).

Declared namespace http://jrkr.me/tags (to be updated to http://jrkr.me/tags/gravatar) this is because I'm going to likely introduce a common module with other tags I create later on, but this one needs to be available in silo.

Usage: (assuming you use jg as the namespace identifier)
  <jg:gravatar email="blah@blah.com" size="50" rating="g" defaultImg="identicon" />
  
  email - required
  size - optional (default is 80, limit between 1 and 512px - if invalid or not in fields sets to 80)
  rating - optional (default is g, values are "g, pg, r, x")
  defaultImg - optional (default is none, blank gravatar logo, values are "wavatar, monsterid, identicon, 404")
  defaultImg - optional can also be used to 'set' a direct 'url of a source image' - if this is used, size/rating etc are all ignored and its what ever the size of that image is.

  ---------
  http://asaph.org/gravatar/  - used for settings and details provided for generating the urls.
  ---------
  
  
  https://github.com/ralfebert/jgravatar - used for inspiration to build this project, some functions re-used from it but mostly has been altered.
  
  
  
