licenses(["notice"])  # Apache License 2.0

java_library(
    name = "options",
    srcs = glob(
        [
            "**/*.java",
        ],
        exclude = [
            "testing/**/*.java",
            "InvocationPolicyEnforcer.java",
            "InvocationPolicyParser.java",
        ],
    ),
    visibility = ["//tools/external/desugar:__subpackages__"],
    deps = [
        "//prebuilts/tools/common/m2/repository/com/google/code/findbugs/jsr305/3.0.1:jar",
        "//prebuilts/tools/common/m2/repository/com/google/guava/guava/20.0:jar",
    ],
)
