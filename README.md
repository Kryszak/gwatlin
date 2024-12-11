![Build Status](https://github.com/Kryszak/gwatlin/actions/workflows/build.yml/badge.svg)
![E2E Status](https://github.com/Kryszak/gwatlin-e2e/actions/workflows/e2e.yml/badge.svg)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/6888080bc19640b4ad89efd0fd84ad9e)](https://app.codacy.com/gh/Kryszak/gwatlin/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6888080bc19640b4ad89efd0fd84ad9e)](https://app.codacy.com/gh/Kryszak/gwatlin/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kryszak/gwatlin.svg)](https://search.maven.org/artifact/io.github.kryszak/gwatlin)

# Gwatlin
**G**uild **W**ars 2 **A**PI client written in Ko**tlin**

Client operates on API version v2.

### Important
It seems that not all api features are documented on wiki page, so feel free to create issues for missing features.
Issue should contain example json response for given endpoint.

## Example usage
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

### Documentation
For full code documentation visit [documentation page](https://kryszak.github.io/gwatlin-docs/)

## JDK versions compatibility
| JDK   |     Gwatlin     |
|-------|:---------------:|
| 21    | 1.9.2 and above |
| 17    | 1.9.1 and below |
