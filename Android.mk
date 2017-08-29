LOCAL_PATH := $(call my-dir)

desugar_src_files := $(call all-java-files-under, java)

# Remove com.google.devtools.common.options.testing classes, they are
# extensions to the Truth library that we are missing dependencies for
# and don't need.
# Also remove com.google.devtools.common.options.InvocationPolicy*,
# which depend on protobuf and are not used in desugar.
desugar_src_files := $(filter-out \
    $(call all-java-files-under, java/com/google/devtools/common/options/testing) \
    java/com/google/devtools/common/options/InvocationPolicyEnforcer.java \
    java/com/google/devtools/common/options/InvocationPolicyParser.java \
    , $(desugar_src_files))

include $(CLEAR_VARS)
LOCAL_MODULE := desugar
LOCAL_SRC_FILES := $(desugar_src_files)

LOCAL_JAR_MANIFEST := manifest.txt
LOCAL_STATIC_JAVA_LIBRARIES := \
    asm-6.0_BETA \
    asm-commons-6.0_BETA \
    asm-tree-6.0_BETA \
    error_prone_annotations-2.0.18 \
    guava-21.0 \
    jsr305-3.0.1 \
    dagger2-auto-value-host \

LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_IS_HOST_MODULE := true
# Required for use of javax.annotation.Generated per http://b/62050818
LOCAL_JAVACFLAGS := $(if $(EXPERIMENTAL_USE_OPENJDK9),-J--add-modules=java.xml.ws.annotation,)

# Use Dagger2 annotation processor
# b/25860419: annotation processors must be explicitly specified for grok
LOCAL_ANNOTATION_PROCESSORS := dagger2-auto-value-host
LOCAL_ANNOTATION_PROCESSOR_CLASSES := com.google.auto.value.processor.AutoValueProcessor

include $(BUILD_HOST_JAVA_LIBRARY)

desugar_src_files :=
