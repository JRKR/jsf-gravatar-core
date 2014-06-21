=================
JSF Gravatar Core Jar
=================

jsf-component for gravatar with own renderer.
Extension of graphicImage jsf component.

no value is required in the component (enhancement is to allow this ot be an alternative image url).

Declared namespace http://jrkr.me/tags (to be updated to http://jrkr.me/tags/gravatar) this is because I'm going to likely introduce a common module with other tags I create later on, but this one needs to be available in silo.

Usage: (assuming you use jg as the namespace identifier)
  <jg:gravatar email="blah@blah.com" size="50" rating="g" defaultImgType="identicon" />
  
  email - required
  size - optional (default is 80, limit between 1 and 512px)
  rating - optional (default is g, values are "g, pg, r, x")
  defaultImgType - optional (default is none, blank gravatar logo, values are "wavatar, monsterid, identicon, 404")
  
  ---------
  http://asaph.org/gravatar/  - used for settings and details provided for generating the urls.
  ---------
  Default Icon URL - Not implemented yet - need to investigate how this url is located.
  
  
  Some source code has been taken from project: https://github.com/ralfebert/jgravatar however has mostly been modified for component re-use.
  
  You can use it the same way as the above by creating a UIGravatar java object as well - however 'download file' hasn't been implemented, maybe in a later release.
  
