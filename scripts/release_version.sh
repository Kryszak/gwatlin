#!/bin/bash

BOLD="\033[1m"
NC="\033[0m"

VERSION=v$(grep "version =" build.gradle.kts | grep -Eo "[0-9].[0-9](.[0-9])?")
LAST_VERSION=$(git describe --tags --abbrev=0)

echo -e "${BOLD}Current version:${NC} $VERSION"
echo -e "${BOLD}Last released version:${NC} $LAST_VERSION"

if [[ "$VERSION" == "$LAST_VERSION" ]]; then
  echo "Parsed project version is equal to last tagged version. Please increment project.version in build.gradle.kts first."
  exit 1
fi

echo ""

echo "Updating documentation"
just documentation
git add docs/ && git commit -m "Documentation update"
git push

echo ""

echo "Pushing new tag"
git tag "$VERSION"
git push origin "$VERSION"
