# Copyright (C) 2016 Joe MacDonald <joe_macdonald@mentor.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "iSNS daemon and utility programs"
DESCRIPTION = "This is a partial implementation of RFC4171, the Internet \
Storage Name Service (iSNS).  The distribution includes the iSNS server, \
supporting persisten storage of registrations, isnsadm, a command line \
utility for managing nodes, and isnsdd, a corresponding discovery daemon."
HOMEPAGE = "http://github.com/gonzoleeman/open-isns/"
LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=321bf41f280cf805086dd5a720b37785"
SECTION = "net"

DEPENDS = "openssl systemd"

SRC_URI = " \
    git://github.com/open-iscsi/open-isns \
"

SRCREV ?= "09954404e948e41eb0fce8e28836018b4ce3d20d"

S = "${WORKDIR}/git"

inherit systemd autotools-brokensep distro_features_check
# depends on systemd
REQUIRED_DISTRO_FEATURES = "systemd"

EXTRA_OECONF = " --prefix=${prefix} --enable-shared"

do_install_append () {
    oe_runmake -C ${B} INCDIR=${STAGING_INCDIR}/libisns/ install_hdrs
    oe_runmake -C ${B} LIBDIR=${D}${libdir} install_lib
}

FILES_${PN} += "${libdir}"