SUMMARY = "ARMOR API compatibility checker (native)"
HOMEPAGE = "https://github.com/qualcomm/armor"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=223037c4be0bfc6cf757035432adf983"

SRC_URI = "git://github.com/qualcomm/armor.git;branch=main;protocol=https \
           file://0001-cmake-drop-host-absolute-clang-include-flags.patch \
           file://0002-port-source-to-current-llvm-clang.patch \
          "
SRCREV = "85dcaf43b3cfdffc0af87a591f1e305824764fb2"

PV = "0.6.8+git${SRCPV}"

inherit cmake native pkgconfig

DEPENDS = "clang-native llvm-native"
DEPENDS += "nlohmann-json-native cli11-native"

EXTRA_OECMAKE += " \
    -DARMOR_BUILD_TESTS=OFF \
    -DLLVM_DIR=${STAGING_LIBDIR_NATIVE}/cmake/llvm \
    -DClang_DIR=${STAGING_LIBDIR_NATIVE}/cmake/clang \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/src/armor/armor ${D}${bindir}/armor
}
