TODO remove this file when done. 
TODO add tests for these changes as well :)

* <https://api.guildwars2.com/v2/maps/1021> doesn't have fields `region_id, region_name, continent_id`.
* <https://api.guildwars2.com/v2/continents/1/floors/0/regions/1/maps/1161> doesn't have fields `type, floors`.
* <https://api.guildwars2.com/v2/continents/1/floors/0/regions/10>, on `maps['1066'].sectors['1299']`, sector doesn't have field `name`.
* <https://api.guildwars2.com/v2/continents/1/floors/0/regions/10>, on `maps['1149']`, `ContinentMap` doesn't have field `label_coord`.
* <https://api.guildwars2.com/v2/continents/1/floors/0/regions/37>, on `maps['1422'].skill_challenges[0]`, `SkillChallenge` doesn't have field `id`.
* 