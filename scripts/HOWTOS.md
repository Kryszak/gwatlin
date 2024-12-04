## Release
To release new version, increment `project.version` property in [build file](../build.gradle.kts).

Next, run [release](./release_version.sh) script from root directory (`./scripts/release_version.sh`) to create new tag and push it to GitHub or create and push new tag manually. 

After new tag is pushed GitHub Actions will upload new version of artifact to Maven Central,
create new GitHub release and push new version of documentation.

## GPG key generation
Every week a cron job checking validity of GPG key used to sign Maven Central publication.

In case of key expiration, following steps must be done to create new one:

1. Generate new key pair with `gpg --gen-key` command. Key must be protected with passphrase.
2. Export secret key with command `gpg --export-secret-keys -a "test <test@test.com>" > priv.key`.
   Replace `"test <test@test.com>"` with uid output of `gpg --list-keys` for given key. 
3. Upload base64 encoded secret key to Github secret `GPG_SIGNING_KEY_BASE64` (`cat priv.key | base64`)
4. Upload key passphrase to Github secret `GPG_SIGNING_PASSPHRASE`
5. Upload public key with command `gpg --keyserver keyserver.ubuntu.com --send-keys <key id>
   `. Replace id with value taken from `gpg --list-keys` pub output value.

Full instructions on working with gpg keys and Central repository can be found [in official documentation](https://central.sonatype.org/publish/requirements/gpg/).
