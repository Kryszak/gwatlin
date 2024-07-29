#!/bin/bash

if [[ -z "$GPG_SIGNING_KEY_BASE64" ]]; then
  echo "No gpg key found, aborting"
  exit 1
fi

echo "$GPG_SIGNING_KEY_BASE64" | base64 -d | gpg --import --batch

EXPIRY_DATE=$(gpg --list-keys | grep pub | grep -oE "expires: [0-9]{4}-[0-9]{2}-[0-9]{2}" | sed 's/expires: //g')
TODAY=$(date +%Y-%m-%d)

echo "GPG signing key expires on $EXPIRY_DATE"
echo "Today is $TODAY"

if [[ "$TODAY" > "$EXPIRY_DATE" ]]; then
  echo "GPG key is expired, generate and publish new one."
  exit 1
fi

echo "GPG is still valid."
