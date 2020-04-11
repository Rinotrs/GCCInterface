package util;

public class SOEN6751_OptionsModel {
    public static String[] compiler = {
            "-fabi-version=n","-fno-access-control","-faligned-new=n",
            "-fargs-in-order=n","-fchar8_t","-fcheck-new","-fconstexpr-depth=n",
            "-fconstexpr-loop-limit=n","-fconstexpr-ops-limit=n",
            "-fno-elide-constructors","-fno-enforce-eh-specs","-fno-gnu-keywords",
            "-fno-implicit-templates","-fno-implicit-inline-templates",
            "-fno-implement-inlines","-fms-extensions","-fnew-inheriting-ctors",
            "-fnew-ttp-matching","-fno-nonansi-builtins","-fnothrow-opt",
            "-fno-operator-names","-fno-optional-diags","-fpermissive",
            "-fno-pretty-templates","-frepo","-fno-rtti","-fsized-deallocation",
            "-ftemplate-backtrace-limit=n","-ftemplate-depth=n",
            "-fno-threadsafe-statics","-fuse-cxa-atexit","-fno-weak","-nostdinc++",
            "-fvisibility-inlines-hidden","-fvisibility-ms-compat",
            "-fext-numeric-literals","-Wabi=n","-Wabi-tag","-Wconversion-null",
            "-Wctor-dtor-privacy","-Wdelete-non-virtual-dtor","-Wdeprecated-copy",
            "-Wdeprecated-copy-dtor","-Wliteral-suffix","-Wmultiple-inheritance",
            "-Wno-init-list-lifetime","-Wnamespaces","-Wnarrowing",
            "-Wpessimizing-move","-Wredundant-move","-Wnoexcept","-Wnoexcept-type",
            "-Wclass-memaccess","-Wnon-virtual-dtor","-Wreorder","-Wregister",
            "-Weffc++","-Wstrict-null-sentinel","-Wtemplates",
            "-Wno-non-template-friend","-Wold-style-cast","-Woverloaded-virtual",
            "-Wno-pmf-conversions","-Wno-class-conversion","-Wno-terminate",
            "-Wsign-promo","-Wvirtual-inheritance"
    };

    public static String[] linking = {
            "object-file-name","-fuse-ld=linker","-llibrary","-nostartfiles",
            "-nodefaultlibs","-nolibc","-nostdlib","-eentry", "--entry=entry","-pie",
            "-pthread","-r","-rdynamic","-s","-static","-static-pie","-static-libgcc",
            "-static-libstdc++","-static-libasan","-static-libtsan",
            "-static-liblsan","-static-libubsan","-shared","-shared-libgcc",
            "-symbolic","-Tscript","-Wloption","-Xlinkeroption","-usymbol","-z",
            "keyword"
    };

    public static String[] execute = {
            "-c","-S","-E","-ofile","-x language","-v","-###","--help[=class[,...]]",
            "--target-help","--version","-pass-exit-codes","-pipe","-specs=file",
            "-wrapper@file","-ffile-prefix-map=old=new","-fplugin=file",
            "-fplugin-arg-name=arg","-fdump-ada-spec[-slim]",
            "-fada-spec-parent=unit","-fdump-go-spec=file"
    };

    public static String[] debugging = {
            "-g","-glevel","-gdwarf","-gdwarf-version","-ggdb",
            "-grecord-gcc-switches","-gno-record-gcc-switches","-gstabs","-gstabs+",
            "-gstrict-dwarf","-gno-strict-dwarf","-gas-loc-support",
            "-gno-as-loc-support","-gas-locview-support","-gno-as-locview-support",
            "-gcolumn-info","-gno-column-info","-gstatement-frontiers",
            "-gno-statement-frontiers","-gvariable-location-views",
            "-gno-variable-location-views","-ginternal-reset-location-views",
            "-gno-internal-reset-location-views","-ginline-points",
            "-gno-inline-points","-gvms","-gxcoff","-gxcoff+","-gz[=type]",
            "-gsplit-dwarf","-gdescribe-dies","-gno-describe-dies",
            "-fdebug-prefix-map=old=new","-fdebug-types-section",
            "-fno-eliminate-unused-debug-types","-femit-struct-debug-baseonly",
            "-femit-struct-debug-reduced","-femit-struct-debug-detailed[=spec-list]",
            "-feliminate-unused-debug-symbols","-femit-class-debug-always",
            "-fno-merge-debug-strings","-fno-dwarf2-cfi-asm","-fvar-tracking",
            "-fvar-tracking-assignments"
    };

    public static String[] optimization = {
            "-faggressive-loop-optimizations",
            "-falign-functions[=n[:m:[n2[:m2]]]]",
            "-falign-jumps[=n[:m:[n2[:m2]]]]","-falign-labels[=n[:m:[n2[:m2]]]]",
            "-falign-loops[=n[:m:[n2[:m2]]]]","-fassociative-math",
            "-fauto-profile","-fauto-profile[=path]","-fauto-inc-dec",
            "-fbranch-probabilities","-fbranch-target-load-optimize",
            "-fbranch-target-load-optimize2","-fbtr-bb-exclusive","-fcaller-saves",
            "-fcombine-stack-adjustments","-fconserve-stack","-fcompare-elim",
            "-fcprop-registers","-fcrossjumping","-fcse-follow-jumps",
            "-fcse-skip-blocks","-fcx-fortran-rules","-fcx-limited-range",
            "-fdata-sections","-fdce","-fdelayed-branch",
            "-fdelete-null-pointer-checks","-fdevirtualize",
            "-fdevirtualize-speculatively","-fdevirtualize-at-ltrans","-fdse",
            "-fearly-inlining","-fipa-sra","-fexpensive-optimizations",
            "-ffat-lto-objects","-ffast-math","-ffinite-math-only","-ffloat-store",
            "-fexcess-precision=style","-fforward-propagate","-ffp-contract=style",
            "-ffunction-sections","-fgcse","-fgcse-after-reload","-fgcse-las",
            "-fgcse-lm","-fgraphite-identity","-fgcse-sm","-fhoist-adjacent-loads",
            "-fif-conversion","-fif-conversion2","-findirect-inlining",
            "-finline-functions","-finline-functions-called-once",
            "-finline-limit=n","-finline-small-functions","-fipa-cp",
            "-fipa-cp-clone","-fipa-bit-cp","-fipa-vrp","-fipa-pta","-fipa-profile",
            "-fipa-pure-const","-fipa-reference","-fipa-reference-addressable"
    };

    public static String[] generation = {
            "-fcall-saved-reg","-fcall-used-reg","-ffixed-reg","-fexceptions",
            "-fnon-call-exceptions","-fdelete-dead-exceptions","-funwind-tables",
            "-fasynchronous-unwind-tables","-fno-gnu-unique",
            "-finhibit-size-directive","-fno-common","-fno-ident",
            "-fpcc-struct-return","-fpic","-fPIC","-fpie","-fPIE","-fno-plt",
            "-fno-jump-tables","-frecord-gcc-switches","-freg-struct-return",
            "-fshort-enums","-fshort-wchar","-fverbose-asm","-fpack-struct[=n]",
            "-fleading-underscore","-ftls-model=model","-fstack-reuse=reuse_level",
            "-ftrampolines","-ftrapv","-fwrapv",
            "-fvisibility=[default|internal|hidden|protected]",
            "-fstrict-volatile-bitfields","-fsync-libcalls"
    };

    public static String[] developer = {
            "-dletters","-dumpspecs","-dumpmachine","-dumpversion",
            "-dumpfullversion","-fchecking","-fchecking=n","-fdbg-cnt-list",
            "-fdbg-cnt=counter-value-list","-fdisable-ipa-pass_name",
            "-fdisable-rtl-pass_name","-fdisable-rtl-pass-name=range-list",
            "-fdisable-tree-pass_name","-fdisable-tree-pass-name=range-list",
            "-fdump-debug","-fdump-earlydebug","-fdump-noaddr","-fdump-unnumbered",
            "-fdump-unnumbered-links","-fdump-final-insns[=file]","-fdump-ipa-all",
            "-fdump-ipa-cgraph","-fdump-ipa-inline","-fdump-lang-all",
            "-fdump-lang-switch","-fdump-lang-switch-options",
            "-fdump-lang-switch-options=filename","-fdump-passes","-fdump-rtl-pass",
            "-fdump-rtl-pass=filename","-fdump-statistics","-fdump-tree-all",
            "-fdump-tree-switch","-fdump-tree-switch-options",
            "-fdump-tree-switch-options=filename","-fcompare-debug[=opts]",
            "-fcompare-debug-second","-fenable-kind-pass",
            "-fenable-kind-pass=range-list","-fira-verbose=n","-flto-report",
            "-flto-report-wpa","-fmem-report-wpa","-fmem-report",
            "-fpre-ipa-mem-report","-fpost-ipa-mem-report","-fopt-info",
            "-fopt-info-options[=file]","-fprofile-report","-frandom-seed=string",
            "-fsched-verbose=n","-fsel-sched-verbose","-fsel-sched-dump-cfg",
            "-fsel-sched-pipelining-verbose","-fstats","-fstack-usage",
            "-ftime-report","-ftime-report-details",
            "-fvar-tracking-assignments-toggle","-gtoggle",
            "-print-file-name=library","-print-libgcc-file-name",
            "-print-multi-directory","-print-multi-lib",
            "-print-multi-os-directory","-print-prog-name=program",
            "-print-search-dirs","-Q","-print-sysroot",
            "-print-sysroot-headers-suffix","-save-temps","-save-temps=cwd",
            "-save-temps=obj","-time[=file]"
    };

}
