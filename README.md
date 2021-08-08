[![Build Status](https://travis-ci.com/Kryszak/gwatlin.svg?branch=master)](https://travis-ci.com/Kryszak/gwatlin)
[![codecov](https://codecov.io/gh/Kryszak/gwatlin/branch/master/graph/badge.svg)](https://codecov.io/gh/Kryszak/gwatlin)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6888080bc19640b4ad89efd0fd84ad9e)](https://www.codacy.com/gh/Kryszak/gwatlin/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Kryszak/gwatlin&amp;utm_campaign=Badge_Grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Gwatlin
**G**uild **W**ars 2 **A**PI client written in Ko**tlin**

## About
This repository contains code for Guild Wars 2 API client based on [documentation](https://wiki.guildwars2.com/wiki/API:Main).

Client operates on API version v2.

### Important
It seems that not all api features are documented on wiki page, so feel free to create issues for missing features.
Issue should contain example json response for given endpoint.

## Features
| Category          | Finished           |
| ----------------- | :----------------: |
| Account           | :heavy_check_mark: |
| Achievements      | :heavy_check_mark: |
| Characters        | :x:                |
| Commerce          | :heavy_check_mark: |
| Daily Rewards     | :heavy_check_mark: |
| Game Mechanics    | :heavy_check_mark: |
| Guild             | :heavy_check_mark: |
| Home Instance     | :heavy_check_mark: |
| Items             | :heavy_check_mark: |
| Map Information   | :x:                |
| Miscellaneous     | :heavy_check_mark: |
| Story             | :heavy_check_mark: |
| Structured PvP    | :heavy_check_mark: |
| Trading Post      | :heavy_check_mark: |
| World vs. World   | :heavy_check_mark: |

## Example usage
Follow instructions from Bintray on how to add repository to your pom.xml or build.gradle file.

Include dependency:
### Maven
```xml
<dependency>
  <groupId>com.kryszak</groupId>
  <artifactId>gwatlin</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```
### Gradle
```groovy
compile 'com.kryszak:gwatlin:1.1'
```

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
