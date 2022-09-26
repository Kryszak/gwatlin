#!/bin/bash

VERSION=$1

if [[ -z $VERSION ]]
then
    echo "No version provided"
    exit 1
fi

git tag $VERSION
git push origin --tags