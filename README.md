# upnplibmobile
Java/Android library for port mapping on UPnP routers.

## Description
This library allows to discover UPnP-compatible routers on the local network,
get some basic router information, including model and version number, external and internal interfaces addresses,
forwarded ports list. Library allows to add new and delete existing port mappings.

Other names: SBBI UPnP lib: org.chris.portmapper.router.sbbi.SBBIRouterFactory, UPNPLib-mobile, UPNPLib

## Others
- weupnp: org.chris.portmapper.router.weupnp.WeUPnPRouterFactory - http://bitletorg.github.io/weupnp/ https://github.com/bitletorg/weupnp
- PortMapper: https://github.com/kaklakariada/portmapper - PortMapper includes three third party UPnP libraries. If the default does not work for your device, try using a different library.
- Cling: org.chris.portmapper.router.cling.ClingRouterFactory (default)
- jupnp - https://github.com/jupnp/jupnp
- org.chris.portmapper.router.dummy.DummyRouterFactory (for testing)

## Evaluation
- *Weupnp* is the simplest to be used and in best shape.
- Cling looks like an overdesigned API but maybe is needed to have much more features. Shuting down the upnp service after a simple port mapping doesn't work properly: https://github.com/4thline/cling/issues/170
- jupnp - a fork of cling that keeps the OSGI features so has more bloatware. There are no maven artifacts released to be downloaded directly.
- PortMapper is an aggregator.

# Ready made tools
- PortMapper - http://www.howtogeek.com/122227/how-to-quickly-forward-ports-on-your-router-from-a-desktop-application/
- Cling Workbench - http://4thline.org/projects/cling/workbench/screenshots.html

# History
## Migrated from UPNPLib-mobile by Suggam
https://sourceforge.net/projects/upnplibmobile/
UPNPLib-mobile is the port of the abandoned UPNPLib project developed by "sbbi". Unlike the original, UPNPLib-mobile intended to be used for Android software development.
Migrated using https://github.com/raisercostin/scm-migration scripts

## Original UPNPLib code
For more details about original UPNPLib library go here:
https://freecode.com/projects/upnplib 

### History 
Taken from https://web.archive.org/web/20150909185517/http://freecode.com/projects/upnplib
UPNPLib is a Java library for the UPNP protocol. It features an easy programming interface for developers as well as an HTTP-based console for end users or developers, so that they can control the UPNP devices residing on their network. It implements all UPNP features like discovery and eventing. The library also provides integration with JMX management (by exposing UPNP devices as MBeans) and RMI through transparent NAT/firewall traversal.

Tags:	Software Development Libraries Java Libraries Networking
Licenses:	Apache
Operating Systems:	OS Independent
Implementation:	Java

Releases:

1.0.4 20 Nov 2006 18:59
Release Notes: This release is mainly a bugfix one, which should improve compatibility with UPNP Internet Gateway devices used for NAT port mapping on XDSL devices.

1.0.3 14 Feb 2006 12:55
Release Notes: Various bugs were fixed and a new UPNP JMX connector was introduced, which allows you to expose MBeans interfaces as UPNP devices. A new UPNPDiscoveryMBean was also included...(more)

1.0.2 29 Aug 2005 18:19
Release Notes: This is an intermediate drop before 1.1, which will contain a JMX-based API to create UPNP devices. This API, which is still in beta stage, is provided within this release...(more)

1.0.1 01 Mar 2005 17:24
Release Notes: This release contains some workarounds to support non-compliant UPNP devices. One minor library bug was fixed. This should greatly improve UPNP device compatibility. The...(more)

1.0 01 Dec 2004 04:40
No changes have been submitted for this release.

Big Thanks goes to the original developers of UPNPLib library! Whoever they are.
