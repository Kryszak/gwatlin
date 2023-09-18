# Module gwatlin

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

