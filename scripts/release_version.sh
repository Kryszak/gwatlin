#!/bin/bash

VERSION=v$(grep "project.version" build.gradle.kts | grep -Eo "[0-9].[0-9](.[0-9])?")

git tag "$VERSION"
git push origin --tags
