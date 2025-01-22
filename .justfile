default:
    @just --list

# Build project
[group('Build')]
build:
    ./gradlew :code:build

# Create html documentation with Dokka
[group('Build')]
documentation:
    ./gradlew :code:dokkaGenerate

# Trigger new release on GitHub
[group('Build')]
create-release:
    @./scripts/release_version.sh

# Check expiration date of maven central certificate
[group('Maintenance')]
check-cert:
    @./scripts/verify_gpg_key_expiration.sh

# Check if running in Github Actions
[group('CI')]
_is-run-in-ci:
    @./scripts/detect-ci.sh

# Build whole project
[group('CI')]
build-clean: _is-run-in-ci
    ./gradlew :code:clean :code:build

# Release new artifact version to maven central
[group('CI')]
release: _is-run-in-ci
    ./gradlew publishToSonatype closeAndReleaseStagingRepository