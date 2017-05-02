licenses(["notice"])  # Apache License 2.0

java_binary(
    name = "desugar",
    srcs = glob([
        "java/**/*.java",
    ]),
    main_class = "com.google.devtools.build.android.desugar.Desugar",
    plugins = ["auto-value-plugin"],
    visibility = ["//tools/base/build-system/builder:__subpackages__"],
    deps = [
        ":deps-neverlink",
        "//prebuilts/tools/common/m2/repository/com/google/guava/guava/20.0:jar",
        "//prebuilts/tools/common/m2/repository/org/ow2/asm/asm-commons/5.2:jar",
        "//prebuilts/tools/common/m2/repository/org/ow2/asm/asm-tree/5.2:jar",
        "//prebuilts/tools/common/m2/repository/org/ow2/asm/asm/5.2:jar",
    ],
)

# Compile-time only dependency on the auto-value and jsr305.
java_library(
    name = "deps-neverlink",
    neverlink = 1,
    visibility = ["//visibility:private"],
    exports = [
        "//prebuilts/tools/common/m2/repository/com/google/auto/value/auto-value/1.3:jar",
        "//prebuilts/tools/common/m2/repository/com/google/code/findbugs/jsr305/3.0.1:jar",
        "//prebuilts/tools/common/m2/repository/com/google/errorprone/error_prone_annotations/2.0.18:jar",
    ],
)

java_plugin(
    name = "auto-value-plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    visibility = ["//visibility:private"],
    deps = [
        "//prebuilts/tools/common/m2/repository/com/google/auto/value/auto-value/1.3:jar",
    ],
)