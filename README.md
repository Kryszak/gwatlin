![Build Status](https://github.com/Kryszak/gwatlin/actions/workflows/build.yml/badge.svg)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/6888080bc19640b4ad89efd0fd84ad9e)](https://app.codacy.com/gh/Kryszak/gwatlin/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6888080bc19640b4ad89efd0fd84ad9e)](https://app.codacy.com/gh/Kryszak/gwatlin/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kryszak/gwatlin.svg)](https://search.maven.org/artifact/io.github.kryszak/gwatlin)

# Gwatlin
**G**uild **W**ars 2 **A**PI client written in Ko**tlin**

## About
This repository contains code for Guild Wars 2 API client based on [documentation](https://wiki.guildwars2.com/wiki/API:Main).

Client operates on API version v2.

## JDK versions compatibility
| JDK   |     Gwatlin     |
|-------|:---------------:|
| 21    |      1.9.2      |
| 17    | 1.9.1 and below |


### Important
It seems that not all api features are documented on wiki page, so feel free to create issues for missing features.
Issue should contain example json response for given endpoint.

## Features
| Category          | Finished           |
| ----------------- | :----------------: |
| Account           | :heavy_check_mark: |
| Achievements      | :heavy_check_mark: |
| Characters        | :heavy_check_mark: |
| Commerce          | :heavy_check_mark: |
| Daily Rewards     | :heavy_check_mark: |
| Game Mechanics    | :heavy_check_mark: |
| Guild             | :heavy_check_mark: |
| Home Instance     | :heavy_check_mark: |
| Items             | :heavy_check_mark: |
| Map Information   | :heavy_check_mark: |
| Miscellaneous     | :heavy_check_mark: |
| Story             | :heavy_check_mark: |
| Structured PvP    | :heavy_check_mark: |
| Trading Post      | :heavy_check_mark: |
| World vs. World   | :heavy_check_mark: |

## Example usage
Artifact is available on Maven Central repository.

### Documentation
For full code documentation visit [documentation page](https://kryszak.github.io/gwatlin-docs/)

### Code
```kotlin
// Client without API KEY
val wvwClient = GWWvwClient()
val wvwRank = wvwClient.getRanks(listOf(1))
println(wvwRank)
// WvwRank(id=1, minRank=1, title=Invader)

// Client with API KEY
val tokenInfoClient = GWTokenClient("API KEY here")
val tokenInfo = tokenInfoClient.getTokenInfo()
println(tokenInfo)
// Token(id=ABCDE02B-8888-FEBA-1234-DE98765C7DEF, name=My API Key, permissions=[account, characters, tradingpost, unlocks, build], type=null, expiresAt=null, issuedAt=null, urls=null)
```
## Release
To release new version, increment `project.version` property in [build file](./build.gradle.kts). Next, run [release](./release_version.sh) script to create new tag and push it to GitHub or create and push new tag manually. New package version will be deployed to Maven repository by GitHub Actions.
