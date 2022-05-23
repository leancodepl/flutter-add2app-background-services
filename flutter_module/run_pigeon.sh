#!/bin/sh

pushd `dirname $0`

flutter pub run pigeon \
    --input pigeon/schema.dart \
    --dart_out lib/src/gen/api.dart \
    --objc_header_out ../ios/Add2AppBackgroundService/Add2AppBackgroundService/Pigeon/Api.h \
    --objc_source_out ../ios/Add2AppBackgroundService/Add2AppBackgroundService/Pigeon/Api.m \
    --objc_prefix LNCD \
    --java_out ../android/app/src/main/java/co/leancode/add2appbackgroundservice/pigeon/Api.java \
    --java_package "co.leancode.add2appbackgroundservice.pigeon"

popd