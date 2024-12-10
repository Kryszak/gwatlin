#!/bin/bash

if [[ "$CI" == "true" ]]; then
        echo "Running in CI"
    else
        echo "This recipe should not be run locally. If you know what you are doing, set \$CI variable to 'true'."
        exit 1
    fi