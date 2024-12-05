default:
    @just --list

# Build project
[group('Build')]
build:
    ./gradlew build

# Create html documentation with Dokka
[group('Build')]
documentation:
    ./gradlew build dokkaHtml

# Trigger new release on GitHub
[group('Build')]
create-release:
    ./scripts/release_version.sh

# Check expiration date of maven central certificate
[group('Maintenance')]
check-cert:
    ./scripts/verify_gpg_key_expiration.sh

# Build whole project
[group('CI')]
build-clean:
    ./gradlew clean build

# Release new artifact version to maven central
[group('CI')]
release:
    ./gradlew clean build publish closeAndReleaseStagingRepository