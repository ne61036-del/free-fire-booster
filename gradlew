#!/bin/sh
exec java -Xmx512m -jar "$(dirname "$0")/gradle/wrapper/gradle-wrapper.jar" "$@"
