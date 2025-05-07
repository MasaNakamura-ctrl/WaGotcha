# 結合テスト仕様書
## 実施項目
| No   | メソッド | テスト処理 | 前提条件 | 操作手順 | 期待結果 | 実施結果 |
| --- | ----------- | ------- | ------- | ------- | ------- | ------- |
| 3 | GET | 異常ケース | DBにインポートできるデータが存在する | APIとDBを立ち上げた後DBを停止しPostmanにてGETリクエストとしてhttp://localhost:8080/api/tsutsumekisを送る | エラーとしてInternal server errorが返されること |OK|

## テストデータ
| id   | tsutsumeki |
| --- | ----------- |
| 1 | '六段の調べやりたい人いない？' |
| 2 | '来週演奏会やります！' |
| 3 | '○市でもくもく会やってませんか？' |
## テスト実施
### DB立ち上げ
```
docker comopose up -d
```
### API立ち上げ
```
./mvnw spring-boot:run // macOs
```
### DB停止
```
docker comopose down
```
### 実施結果
![実施結果](./img/no3.png)