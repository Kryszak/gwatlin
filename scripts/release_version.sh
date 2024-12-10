#!/bin/bash

VERSION=v$(grep "project.version" build.gradle.kts | grep -Eo "[0-9].[0-9](.[0-9])?")
LAST_VERSION=$(git describe --tags --abbrev=0)

echo "Current version: $VERSION"
echo "Last released version: $LAST_VERSION"

if [[ "$VERSION" == "$LAST_VERSION" ]]; then
  echo "Parsed project version is equal to last tagged version. Please increment project.version in build.gradle.kts first."
  exit 1
fi

git tag "$VERSION"
git push origin --tags
